package com.example.data

import android.content.Context
import com.example.data.local.AppDatabase
import com.example.data.local.DuelHistoryEntity
import com.example.data.local.OfflineSyncManager
import com.example.data.local.OfflineSyncQueueEntity
import com.example.data.local.QuizAttemptEntity
import com.example.data.local.UserPreferences
import com.example.model.AnalyticsReport
import com.example.model.BadgeItem
import com.example.model.DailyQuest
import com.example.model.DuelMatchResult
import com.example.model.GradeLevel
import com.example.model.LeaderboardUser
import com.example.model.QuizQuestion
import com.example.model.QuizResult
import com.example.model.SchoolType
import com.example.model.Subject
import com.example.model.SubjectMastery
import com.example.model.Topic
import com.example.model.UserProfile
import com.example.model.WeeklyStudyStat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MaarifRepository(context: Context) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val database = AppDatabase.getDatabase(context)
    val preferences = UserPreferences(context)
    val syncManager = OfflineSyncManager(context, database, preferences)

    private val _userProfile = MutableStateFlow(buildUserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _dailyQuests = MutableStateFlow(MaarifCurriculumData.dailyQuests)
    val dailyQuests: StateFlow<List<DailyQuest>> = _dailyQuests.asStateFlow()

    private val _badges = MutableStateFlow(MaarifCurriculumData.badges)
    val badges: StateFlow<List<BadgeItem>> = _badges.asStateFlow()

    init {
        scope.launch {
            // Seed initial sample attempts if database is fresh to provide rich initial analytics
            if (database.quizAttemptDao().getAttemptCount() == 0) {
                seedInitialData()
            }
            refreshProfile()
        }
    }

    private suspend fun seedInitialData() {
        val now = System.currentTimeMillis()
        database.quizAttemptDao().insertAttempt(
            QuizAttemptEntity(
                topicId = "turkce_g5_t1",
                topicTitle = "Sözcükte Anlam (Gerçek, Mecaz)",
                subjectName = "Türkçe",
                grade = preferences.gradeLevel,
                totalQuestions = 6,
                correctCount = 5,
                wrongCount = 1,
                xpEarned = 75,
                timestamp = now - 86400000L * 2,
                isSynced = true
            )
        )
        database.quizAttemptDao().insertAttempt(
            QuizAttemptEntity(
                topicId = "matematik_g5_t1",
                topicTitle = "Milyonlu Sayılar ve Bölükler",
                subjectName = "Matematik",
                grade = preferences.gradeLevel,
                totalQuestions = 6,
                correctCount = 6,
                wrongCount = 0,
                xpEarned = 100,
                timestamp = now - 86400000L,
                isSynced = true
            )
        )
        database.quizAttemptDao().insertAttempt(
            QuizAttemptEntity(
                topicId = "fen_g5_t1",
                topicTitle = "Güneş, Dünya ve Ay",
                subjectName = "Fen Bilimleri",
                grade = preferences.gradeLevel,
                totalQuestions = 6,
                correctCount = 5,
                wrongCount = 1,
                xpEarned = 80,
                timestamp = now - 3600000L * 4,
                isSynced = true
            )
        )
    }

    private fun buildUserProfile(): UserProfile {
        val xp = preferences.userXp
        val level = (xp / 250) + 1
        val levelTitle = when {
            level >= 6 -> "Maarif Üstadı 🌟"
            level >= 4 -> "Bilgin Kâşif 🚀"
            level >= 2 -> "Meraklı Öğrenci 📚"
            else -> "Yeni Başlayan 🌱"
        }
        return UserProfile(
            studentName = preferences.studentName,
            schoolType = preferences.schoolType,
            grade = preferences.gradeLevel,
            xp = xp,
            level = level,
            levelTitle = levelTitle,
            streakDays = preferences.streakDays,
            placementExamDone = preferences.placementExamDone,
            placementLevel = preferences.placementLevel,
            placementScore = preferences.placementScore,
            isOnline = syncManager.isOnline.value,
            isSyncing = syncManager.isSyncing.value,
            lastSyncTime = syncManager.lastSyncDisplay.value,
            pendingSyncCount = syncManager.pendingSyncCount.value
        )
    }

    fun refreshProfile() {
        _userProfile.value = buildUserProfile()
    }

    fun setSchoolTypeAndGrade(schoolType: SchoolType, grade: Int) {
        preferences.schoolType = schoolType
        preferences.gradeLevel = grade
        preferences.isOnboardingCompleted = true
        refreshProfile()
    }

    fun setStudentName(name: String) {
        preferences.studentName = name
        refreshProfile()
    }

    /**
     * Filters subjects according to the Maarif model rules:
     * - Devlet: only standard middle school subjects (exclude İmam Hatip only subjects).
     * - İmam Hatip: both standard subjects and İmam Hatip specific subjects!
     */
    fun getSubjectsForCurrentConfig(): List<Subject> {
        val currentType = preferences.schoolType
        val currentGrade = preferences.gradeLevel
        return MaarifCurriculumData.allSubjects.filter { subject ->
            val matchesSchool = if (currentType == SchoolType.DEVLET) {
                !subject.isImamHatipOnly
            } else {
                true // İmam Hatip gets all standard + İmam Hatip specific courses
            }
            matchesSchool && subject.grades.contains(currentGrade)
        }
    }

    fun getSubjectById(subjectId: String): Subject? {
        return MaarifCurriculumData.allSubjects.find { it.id == subjectId }
    }

    fun getTopicsForSubject(subjectId: String): List<Topic> {
        val grade = preferences.gradeLevel
        return MaarifCurriculumData.sampleTopics.filter {
            it.subjectId == subjectId && (it.grade == grade || it.grade == 5)
        }.ifEmpty {
            // Provide at least one comprehensive topic if specific grade sample is falling back
            listOf(
                Topic(
                    id = "${subjectId}_g${grade}_t_general",
                    subjectId = subjectId,
                    grade = grade,
                    unitName = "1. Ünite: Maarif Modeli Temel Konuları",
                    title = "${getSubjectById(subjectId)?.name ?: "Ders"} - Temel Kavramlar ve İlkeler",
                    subtitle = "$grade. sınıf MEB ve EBA müfredatına tam uyumlu kazanımlar",
                    summaryBullets = listOf(
                        "Türkiye Yüzyılı Maarif Modeli kapsamında beceri temelli öğrenme yaklaşımı.",
                        "Kritik düşünme, analiz ve problem çözme basamakları.",
                        "Kazanımların günlük hayat ve disiplinlerarası ilişkileri."
                    ),
                    fullContent = """
                        ### $grade. Sınıf ${getSubjectById(subjectId)?.name} Maarif Kazanımları
                        Bu ünitede MEB ve EBA müfredatına uygun temel kavramlar, görsel modeller ve örnek sorular ele alınmaktadır.

                        * **Kavramsal Çerçeve:** Öğrenilen bilgilerin derinlemesine kavranması ve akılda kalıcılığı hedeflenir.
                        * **Uygulama:** Teori ile pratik hayat arasında bağ kurulur.
                        * **Değerlendirme:** Etkileşimli testlerle kazanım düzeyi ölçülür.
                    """.trimIndent(),
                    maarifNote = "Maarif Modeli: Bilgiyi ezberlemek yerine anlamlandırma ve hayata değer katma becerisi.",
                    keyFormulasOrRules = listOf(
                        "Konu sonu özetini oku ve zihin haritasını incele.",
                        "Testi çözerek hemen anında dönüt al."
                    ),
                    estimatedReadMinutes = 5,
                    xpReward = 50
                )
            )
        }
    }

    fun getTopicById(topicId: String): Topic? {
        return MaarifCurriculumData.sampleTopics.find { it.id == topicId }
            ?: getTopicsForSubject("turkce").firstOrNull()
    }

    fun getQuestionsForTopic(topicId: String): List<QuizQuestion> {
        val questions = MaarifCurriculumData.sampleQuestions.filter { it.topicId == topicId }
        return questions.ifEmpty {
            // Fallback generated questions for this topic
            val topic = getTopicById(topicId)
            listOf(
                QuizQuestion(
                    id = "q_gen_1",
                    topicId = topicId,
                    subjectId = topic?.subjectId ?: "turkce",
                    grade = preferences.gradeLevel,
                    questionText = "${topic?.title ?: "Bu konu"} ile ilgili aşağıdakilerden hangisi temel bir kazanımdır?",
                    options = listOf(
                        "A) Kavramları sadece ezberlemek",
                        "B) İlkeleri anlayıp problem çözmede ve günlük hayatta uygulamak",
                        "C) Soru çözmeden konuyu geçmek",
                        "D) Formülleri kontrol etmemek"
                    ),
                    correctIndex = 1,
                    explanation = "Türkiye Yüzyılı Maarif Modeli'nde esas olan; bilginin ezberlenmesi değil, kavranarak günlük hayat problemlerine ve analitik düşünceye uyarlanmasıdır.",
                    hint = "Maarif Modeli'nin beceri odaklı yaklaşımını hatırla."
                ),
                QuizQuestion(
                    id = "q_gen_2",
                    topicId = topicId,
                    subjectId = topic?.subjectId ?: "turkce",
                    grade = preferences.gradeLevel,
                    questionText = "Bu konuda öğrendiğiniz yöntemlerle ilgili en doğru ifade hangisidir?",
                    options = listOf(
                        "A) Doğru strateji ve düzenli tekrar başarıyı artırır.",
                        "B) Hızlı çözmek doğru çözmekten önemlidir.",
                        "C) Açıklamaları okumaya gerek yoktur.",
                        "D) Yanlış yapılan sorular tekrar incelenmemelidir."
                    ),
                    correctIndex = 0,
                    explanation = "Etkili öğrenme sürecinde düzenli tekrar ve analiz edilen açıklamalar başarıyı kalıcı kılar.",
                    hint = "Düzenli çalışma prensibini seç."
                )
            )
        }
    }

    fun getPlacementExamQuestions(): List<QuizQuestion> {
        return MaarifCurriculumData.placementExamQuestions
    }

    fun submitQuizResult(
        topicId: String,
        topicTitle: String,
        subjectName: String,
        totalQuestions: Int,
        correctCount: Int,
        wrongCount: Int,
        isPlacement: Boolean = false
    ) {
        val earnedXp = (correctCount * 15) + 20
        val newTotalXp = preferences.userXp + earnedXp
        preferences.userXp = newTotalXp

        if (isPlacement) {
            preferences.placementExamDone = true
            val percentage = (correctCount * 100) / totalQuestions.coerceAtLeast(1)
            preferences.placementScore = percentage
            preferences.placementLevel = when {
                percentage >= 85 -> "İleri Seviye (Uzman)"
                percentage >= 65 -> "Gelişmiş Seviye"
                percentage >= 45 -> "Orta Seviye"
                else -> "Temel Düzey"
            }
        }

        scope.launch {
            val entity = QuizAttemptEntity(
                topicId = topicId,
                topicTitle = topicTitle,
                subjectName = subjectName,
                grade = preferences.gradeLevel,
                totalQuestions = totalQuestions,
                correctCount = correctCount,
                wrongCount = wrongCount,
                xpEarned = earnedXp,
                isPlacement = isPlacement,
                isSynced = syncManager.isOnline.value
            )
            database.quizAttemptDao().insertAttempt(entity)

            // Queue for sync if offline
            if (!syncManager.isOnline.value) {
                database.syncQueueDao().enqueue(
                    OfflineSyncQueueEntity(
                        actionType = "QUIZ_RESULT",
                        payloadJson = "{\"topicId\":\"$topicId\",\"correct\":$correctCount,\"xp\":$earnedXp}"
                    )
                )
            } else {
                syncManager.triggerAutoSync()
            }
            refreshProfile()
        }
    }

    fun recordDuelResult(
        opponentName: String,
        userScore: Int,
        opponentScore: Int,
        subjectName: String
    ) {
        val isWin = userScore >= opponentScore
        val gainedXp = if (isWin) 80 else 30
        preferences.userXp += gainedXp

        scope.launch {
            database.duelHistoryDao().insertDuel(
                DuelHistoryEntity(
                    opponentName = opponentName,
                    opponentGrade = preferences.gradeLevel,
                    userScore = userScore,
                    opponentScore = opponentScore,
                    isVictory = isWin,
                    subjectName = subjectName,
                    xpGained = gainedXp,
                    isSynced = syncManager.isOnline.value
                )
            )
            if (!syncManager.isOnline.value) {
                database.syncQueueDao().enqueue(
                    OfflineSyncQueueEntity(
                        actionType = "DUEL_RESULT",
                        payloadJson = "{\"opponent\":\"$opponentName\",\"win\":$isWin,\"xp\":$gainedXp}"
                    )
                )
            } else {
                syncManager.triggerAutoSync()
            }
            refreshProfile()
        }
    }

    suspend fun getAnalyticsReport(): AnalyticsReport {
        val attempts = database.quizAttemptDao().getAllAttemptsList()
        val totalQuestions = attempts.sumOf { it.totalQuestions }.coerceAtLeast(36)
        val correctCount = attempts.sumOf { it.correctCount }.coerceAtLeast(30)
        val accuracy = (correctCount * 100) / totalQuestions

        // Subject masteries
        val subjects = getSubjectsForCurrentConfig()
        val masteries = subjects.map { subj ->
            val subjAttempts = attempts.filter { it.subjectName.contains(subj.shortName, ignoreCase = true) }
            val subjTotal = subjAttempts.sumOf { it.totalQuestions }
            val subjCorrect = subjAttempts.sumOf { it.correctCount }
            val percentage = if (subjTotal > 0) {
                (subjCorrect * 100) / subjTotal
            } else {
                // Baseline mastery score
                when (subj.id) {
                    "matematik" -> 88
                    "turkce" -> 92
                    "fen" -> 78
                    "sosyal" -> 85
                    "kuran" -> 90
                    else -> 82
                }
            }
            val status = when {
                percentage >= 85 -> "Çok İyi / Maarif Zirve 🌟"
                percentage >= 70 -> "İyi Düzeyde 👍"
                else -> "Tekrar Edilmeli 💡"
            }
            SubjectMastery(
                subjectId = subj.id,
                subjectName = subj.name,
                masteryPercentage = percentage,
                totalAnswered = if (subjTotal > 0) subjTotal else 12,
                correctCount = if (subjCorrect > 0) subjCorrect else 10,
                colorHex = subj.colorHex,
                statusText = status
            )
        }

        val weeklyStats = listOf(
            WeeklyStudyStat("Pzt", 15, 35),
            WeeklyStudyStat("Sal", 22, 50),
            WeeklyStudyStat("Çar", 18, 40),
            WeeklyStudyStat("Per", 25, 55),
            WeeklyStudyStat("Cum", 30, 65),
            WeeklyStudyStat("Cmt", 40, 80),
            WeeklyStudyStat("Paz", 28, 45)
        )

        val strong = listOf(
            "Türkçe: Paragrafta ve Cümlede Anlam sorularında %92 başarı oranı.",
            "Matematik: Doğal Sayılar ve Milyonlu Basamak Analizinde sıfır hata.",
            "Düzenli Çalışma: 4 günlük kesintisiz çalışma serisi motivasyonunu koruyor."
        )

        val improvements = listOf(
            "Fen Bilimleri: 'Kuvvet ve Enerji / Ay Evreleri' konularında pratik test çözümü önerilir.",
            "Zaman Yönetimi: Soru başına ortalama süreyi 50 saniyeden 40 saniyeye düşürmek için Hızlı Testler çöz."
        )

        val advice = "Türkiye Yüzyılı Maarif Modeli Gelişim Analizi: Öğrencinin kavramsal anlama kapasitesi yüksek. Eksik görülen konularda 'Hızlı Özet' kartlarını gözden geçirip düello modunda akranlarıyla soru yarışlarına katılması bilgiyi pekiştirecektir."

        return AnalyticsReport(
            overallAccuracy = accuracy,
            totalStudyHours = 5.8f,
            solvedQuestionCount = totalQuestions,
            correctRate = accuracy,
            subjectMasteries = masteries,
            weeklyStats = weeklyStats,
            strongPoints = strong,
            improvementPoints = improvements,
            maarifPedagogicalAdvice = advice
        )
    }

    fun getLeaderboard(): List<LeaderboardUser> {
        val current = buildUserProfile()
        return MaarifCurriculumData.mockLeaderboard.map {
            if (it.isCurrentUser) {
                it.copy(
                    xp = current.xp,
                    streak = current.streakDays,
                    grade = current.grade,
                    schoolType = current.schoolType,
                    title = current.levelTitle
                )
            } else it
        }.sortedByDescending { it.xp }
            .mapIndexed { index, user -> user.copy(rank = index + 1) }
    }
}
