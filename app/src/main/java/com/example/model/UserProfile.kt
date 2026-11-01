package com.example.model

data class UserProfile(
    val studentName: String = "Geleceğin Kaşifi",
    val schoolType: SchoolType = SchoolType.DEVLET,
    val grade: Int = 5,
    val xp: Int = 340,
    val level: Int = 3,
    val levelTitle: String = "Meraklı Kaşif",
    val streakDays: Int = 4,
    val lastActiveDate: String = "",
    val totalQuizzesCompleted: Int = 6,
    val totalQuestionsAnswered: Int = 36,
    val totalCorrectAnswers: Int = 31,
    val placementExamDone: Boolean = false,
    val placementLevel: String = "Gelişmekte",
    val placementScore: Int = 0,
    val isOnline: Boolean = true,
    val isSyncing: Boolean = false,
    val lastSyncTime: String = "Az önce",
    val pendingSyncCount: Int = 0
) {
    val overallAccuracy: Int
        get() = if (totalQuestionsAnswered > 0) (totalCorrectAnswers * 100) / totalQuestionsAnswered else 85

    val nextLevelXp: Int
        get() = level * 250

    val currentLevelProgress: Float
        get() {
            val baseLevelXp = (level - 1) * 250
            val neededInThisLevel = 250
            val earnedInThisLevel = (xp - baseLevelXp).coerceAtLeast(0)
            return (earnedInThisLevel.toFloat() / neededInThisLevel).coerceIn(0f, 1f)
        }
}

data class SubjectMastery(
    val subjectId: String,
    val subjectName: String,
    val masteryPercentage: Int, // 0 - 100
    val totalAnswered: Int,
    val correctCount: Int,
    val colorHex: String,
    val statusText: String
)

data class WeeklyStudyStat(
    val dayName: String, // Pzt, Sal, Çar, Per, Cum, Cmt, Paz
    val questionsAnswered: Int,
    val minutesStudied: Int
)

data class AnalyticsReport(
    val overallAccuracy: Int,
    val totalStudyHours: Float,
    val solvedQuestionCount: Int,
    val correctRate: Int,
    val subjectMasteries: List<SubjectMastery>,
    val weeklyStats: List<WeeklyStudyStat>,
    val strongPoints: List<String>,
    val improvementPoints: List<String>,
    val maarifPedagogicalAdvice: String
)
