package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizAttemptDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttempt(attempt: QuizAttemptEntity): Long

    @Query("SELECT * FROM quiz_attempts ORDER BY timestamp DESC")
    fun getAllAttempts(): Flow<List<QuizAttemptEntity>>

    @Query("SELECT * FROM quiz_attempts ORDER BY timestamp DESC")
    suspend fun getAllAttemptsList(): List<QuizAttemptEntity>

    @Query("SELECT * FROM quiz_attempts WHERE isSynced = 0")
    suspend fun getUnsyncedAttempts(): List<QuizAttemptEntity>

    @Query("UPDATE quiz_attempts SET isSynced = 1 WHERE isSynced = 0")
    suspend fun markAllAsSynced()

    @Query("SELECT COUNT(*) FROM quiz_attempts")
    suspend fun getAttemptCount(): Int
}

@Dao
interface DuelHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDuel(duel: DuelHistoryEntity): Long

    @Query("SELECT * FROM duel_history ORDER BY timestamp DESC")
    fun getAllDuels(): Flow<List<DuelHistoryEntity>>

    @Query("SELECT * FROM duel_history WHERE isSynced = 0")
    suspend fun getUnsyncedDuels(): List<DuelHistoryEntity>

    @Query("UPDATE duel_history SET isSynced = 1 WHERE isSynced = 0")
    suspend fun markAllAsSynced()
}

@Dao
interface SyncQueueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun enqueue(item: OfflineSyncQueueEntity): Long

    @Query("SELECT * FROM sync_queue WHERE isSynced = 0 ORDER BY createdAt ASC")
    suspend fun getPendingItems(): List<OfflineSyncQueueEntity>

    @Query("DELETE FROM sync_queue WHERE isSynced = 1")
    suspend fun cleanSyncedItems()

    @Query("UPDATE sync_queue SET isSynced = 1")
    suspend fun markAllSynced()

    @Query("SELECT COUNT(*) FROM sync_queue WHERE isSynced = 0")
    fun getPendingCountFlow(): Flow<Int>
}
