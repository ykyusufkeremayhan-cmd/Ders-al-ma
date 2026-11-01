package com.example.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.model.GradeLevel
import com.example.model.SchoolType

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("maarif_akademi_prefs", Context.MODE_PRIVATE)

    var isOnboardingCompleted: Boolean
        get() = prefs.getBoolean("is_onboarded", false)
        set(value) = prefs.edit().putBoolean("is_onboarded", value).apply()

    var schoolType: SchoolType
        get() = SchoolType.fromId(prefs.getString("school_type", SchoolType.DEVLET.id))
        set(value) = prefs.edit().putString("school_type", value.id).apply()

    var gradeLevel: Int
        get() = prefs.getInt("grade_level", 5)
        set(value) = prefs.edit().putInt("grade_level", value).apply()

    var studentName: String
        get() = prefs.getString("student_name", "Geleceğin Kaşifi") ?: "Geleceğin Kaşifi"
        set(value) = prefs.edit().putString("student_name", value).apply()

    var userXp: Int
        get() = prefs.getInt("user_xp", 340)
        set(value) = prefs.edit().putInt("user_xp", value).apply()

    var streakDays: Int
        get() = prefs.getInt("streak_days", 4)
        set(value) = prefs.edit().putInt("streak_days", value).apply()

    var placementExamDone: Boolean
        get() = prefs.getBoolean("placement_done", false)
        set(value) = prefs.edit().putBoolean("placement_done", value).apply()

    var placementScore: Int
        get() = prefs.getInt("placement_score", 0)
        set(value) = prefs.edit().putInt("placement_score", value).apply()

    var placementLevel: String
        get() = prefs.getString("placement_level", "Henüz Çözülmedi") ?: "Henüz Çözülmedi"
        set(value) = prefs.edit().putString("placement_level", value).apply()

    var lastSyncTimestamp: Long
        get() = prefs.getLong("last_sync_time", System.currentTimeMillis())
        set(value) = prefs.edit().putLong("last_sync_time", value).apply()

    fun resetAll() {
        prefs.edit().clear().apply()
    }
}
