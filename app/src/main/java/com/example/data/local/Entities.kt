package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_attempts")
data class QuizAttemptEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val topicId: String,
    val topicTitle: String,
    val subjectName: String,
    val grade: Int,
    val totalQuestions: Int,
    val correctCount: Int,
    val wrongCount: Int,
    val xpEarned: Int,
    val timestamp: Long = System.currentTimeMillis(),
    val isPlacement: Boolean = false,
    val isSynced: Boolean = false
)

@Entity(tableName = "duel_history")
data class DuelHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val opponentName: String,
    val opponentGrade: Int,
    val userScore: Int,
    val opponentScore: Int,
    val isVictory: Boolean,
    val subjectName: String,
    val xpGained: Int,
    val timestamp: Long = System.currentTimeMillis(),
    val isSynced: Boolean = false
)

@Entity(tableName = "sync_queue")
data class OfflineSyncQueueEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val actionType: String, // e.g. "QUIZ_RESULT", "DUEL_RESULT", "XP_UPDATE"
    val payloadJson: String,
    val createdAt: Long = System.currentTimeMillis(),
    val isSynced: Boolean = false
)
