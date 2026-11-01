package com.example.data.local

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OfflineSyncManager(
    private val context: Context,
    private val database: AppDatabase,
    private val preferences: UserPreferences
) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    private val _isOnline = MutableStateFlow(checkInitialConnectivity())
    val isOnline: StateFlow<Boolean> = _isOnline.asStateFlow()

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing.asStateFlow()

    private val _lastSyncDisplay = MutableStateFlow(getLastSyncText())
    val lastSyncDisplay: StateFlow<String> = _lastSyncDisplay.asStateFlow()

    private val _pendingSyncCount = MutableStateFlow(0)
    val pendingSyncCount: StateFlow<Int> = _pendingSyncCount.asStateFlow()

    init {
        registerNetworkCallback()
        observePendingCount()
    }

    private fun checkInitialConnectivity(): Boolean {
        val cm = connectivityManager ?: return false
        val activeNetwork = cm.activeNetwork ?: return false
        val caps = cm.getNetworkCapabilities(activeNetwork) ?: return false
        return caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private fun registerNetworkCallback() {
        val cm = connectivityManager ?: return
        val builder = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        cm.registerNetworkCallback(builder, object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                _isOnline.value = true
                // Auto-sync when internet becomes available
                triggerAutoSync()
            }

            override fun onLost(network: Network) {
                _isOnline.value = false
            }
        })
    }

    private fun observePendingCount() {
        scope.launch {
            database.syncQueueDao().getPendingCountFlow().collect { count ->
                _pendingSyncCount.value = count
            }
        }
    }

    fun triggerAutoSync() {
        if (_isSyncing.value) return
        scope.launch {
            _isSyncing.value = true
            // Simulate cloud server handshake & payload processing
            delay(1200)

            database.quizAttemptDao().markAllAsSynced()
            database.duelHistoryDao().markAllAsSynced()
            database.syncQueueDao().markAllSynced()
            database.syncQueueDao().cleanSyncedItems()

            val now = System.currentTimeMillis()
            preferences.lastSyncTimestamp = now
            val timeStr = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(now))
            _lastSyncDisplay.value = "Bugün $timeStr"
            _pendingSyncCount.value = 0
            _isSyncing.value = false
        }
    }

    private fun getLastSyncText(): String {
        val timestamp = preferences.lastSyncTimestamp
        if (timestamp == 0L) return "Henüz senkronize edilmedi"
        val timeStr = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(timestamp))
        return "Bugün $timeStr"
    }
}
