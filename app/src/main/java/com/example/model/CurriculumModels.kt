package com.example.model

data class Subject(
    val id: String,
    val name: String,
    val shortName: String,
    val iconKey: String,
    val colorHex: String,
    val isImamHatipOnly: Boolean = false,
    val grades: List<Int> = listOf(5, 6, 7, 8),
    val description: String,
    val unitCount: Int = 4
)

data class Topic(
    val id: String,
    val subjectId: String,
    val grade: Int,
    val unitName: String,
    val title: String,
    val subtitle: String,
    val summaryBullets: List<String>,
    val fullContent: String,
    val maarifNote: String,
    val keyFormulasOrRules: List<String> = emptyList(),
    val estimatedReadMinutes: Int = 6,
    val xpReward: Int = 50
)

data class QuizQuestion(
    val id: String,
    val topicId: String,
    val subjectId: String,
    val grade: Int,
    val questionText: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val hint: String = "",
    val difficulty: String = "Orta",
    val isPlacement: Boolean = false
)

data class BadgeItem(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val isUnlocked: Boolean = false,
    val unlockedDate: String? = null
)

data class DailyQuest(
    val id: String,
    val title: String,
    val targetCount: Int,
    val currentCount: Int,
    val xpReward: Int,
    val isCompleted: Boolean = false
)

data class QuizResult(
    val id: String,
    val topicId: String,
    val topicTitle: String,
    val subjectName: String,
    val grade: Int,
    val totalQuestions: Int,
    val correctCount: Int,
    val wrongCount: Int,
    val xpEarned: Int,
    val timestamp: Long = System.currentTimeMillis(),
    val isPlacement: Boolean = false
) {
    val accuracyPercentage: Int
        get() = if (totalQuestions > 0) (correctCount * 100) / totalQuestions else 0
}

data class LeaderboardUser(
    val rank: Int,
    val name: String,
    val schoolType: SchoolType,
    val grade: Int,
    val xp: Int,
    val streak: Int,
    val isCurrentUser: Boolean = false,
    val avatarEmoji: String = "🎓",
    val title: String = "Öğrenci"
)

data class DuelQuestion(
    val question: QuizQuestion,
    val timeLimitSeconds: Int = 12
)

data class DuelMatchResult(
    val opponentName: String,
    val opponentGrade: Int,
    val userScore: Int,
    val opponentScore: Int,
    val isVictory: Boolean,
    val subjectName: String,
    val xpGained: Int,
    val timestamp: Long = System.currentTimeMillis()
)
