package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.MaarifRepository
import com.example.model.AnalyticsReport
import com.example.model.BadgeItem
import com.example.model.DailyQuest
import com.example.model.GradeLevel
import com.example.model.LeaderboardUser
import com.example.model.QuizQuestion
import com.example.model.SchoolType
import com.example.model.Subject
import com.example.model.Topic
import com.example.model.UserProfile
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class Screen {
    ONBOARDING,
    HOME,
    SUBJECT_DETAIL,
    TOPIC_EXPLANATION,
    QUIZ,
    PLACEMENT_EXAM,
    ANALYTICS,
    LEADERBOARD,
    DUEL,
    PROFILE
}

class MaarifViewModel(application: Application) : AndroidViewModel(application) {
    val repository = MaarifRepository(application.applicationContext)

    private val _currentScreen = MutableStateFlow(
        if (repository.preferences.isOnboardingCompleted) Screen.HOME else Screen.ONBOARDING
    )
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    // Navigation back stack for BackHandler
    private val backStack = mutableListOf<Screen>()

    // Profile & Config
    val userProfile: StateFlow<UserProfile> = repository.userProfile
    val dailyQuests: StateFlow<List<DailyQuest>> = repository.dailyQuests
    val badges: StateFlow<List<BadgeItem>> = repository.badges

    // Selected items for navigation
    private val _selectedSubject = MutableStateFlow<Subject?>(null)
    val selectedSubject: StateFlow<Subject?> = _selectedSubject.asStateFlow()

    private val _selectedTopic = MutableStateFlow<Topic?>(null)
    val selectedTopic: StateFlow<Topic?> = _selectedTopic.asStateFlow()

    // Active Quiz Session State
    private val _quizQuestions = MutableStateFlow<List<QuizQuestion>>(emptyList())
    val quizQuestions: StateFlow<List<QuizQuestion>> = _quizQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _selectedOptionIndex = MutableStateFlow<Int?>(null)
    val selectedOptionIndex: StateFlow<Int?> = _selectedOptionIndex.asStateFlow()

    private val _isAnswerSubmitted = MutableStateFlow(false)
    val isAnswerSubmitted: StateFlow<Boolean> = _isAnswerSubmitted.asStateFlow()

    private val _correctCount = MutableStateFlow(0)
    val correctCount: StateFlow<Int> = _correctCount.asStateFlow()

    private val _wrongCount = MutableStateFlow(0)
    val wrongCount: StateFlow<Int> = _wrongCount.asStateFlow()

    private val _quizCompleted = MutableStateFlow(false)
    val quizCompleted: StateFlow<Boolean> = _quizCompleted.asStateFlow()

    private val _isPlacementMode = MutableStateFlow(false)
    val isPlacementMode: StateFlow<Boolean> = _isPlacementMode.asStateFlow()

    // Active Duel Mode State
    private val _duelQuestions = MutableStateFlow<List<QuizQuestion>>(emptyList())
    val duelQuestions: StateFlow<List<QuizQuestion>> = _duelQuestions.asStateFlow()

    private val _duelQuestionIndex = MutableStateFlow(0)
    val duelQuestionIndex: StateFlow<Int> = _duelQuestionIndex.asStateFlow()

    private val _userDuelScore = MutableStateFlow(0)
    val userDuelScore: StateFlow<Int> = _userDuelScore.asStateFlow()

    private val _botDuelScore = MutableStateFlow(0)
    val botDuelScore: StateFlow<Int> = _botDuelScore.asStateFlow()

    private val _duelTimeRemaining = MutableStateFlow(12)
    val duelTimeRemaining: StateFlow<Int> = _duelTimeRemaining.asStateFlow()

    private val _duelCompleted = MutableStateFlow(false)
    val duelCompleted: StateFlow<Boolean> = _duelCompleted.asStateFlow()

    private val _duelOpponentName = MutableStateFlow("Ahmet (8. Sınıf)")
    val duelOpponentName: StateFlow<String> = _duelOpponentName.asStateFlow()

    private var duelTimerJob: Job? = null

    // Analytics Report State
    private val _analyticsReport = MutableStateFlow<AnalyticsReport?>(null)
    val analyticsReport: StateFlow<AnalyticsReport?> = _analyticsReport.asStateFlow()

    // Leaderboard State
    private val _leaderboardUsers = MutableStateFlow<List<LeaderboardUser>>(emptyList())
    val leaderboardUsers: StateFlow<List<LeaderboardUser>> = _leaderboardUsers.asStateFlow()

    // Toast / Feedback message
    private val _feedbackMessage = MutableStateFlow<String?>(null)
    val feedbackMessage: StateFlow<String?> = _feedbackMessage.asStateFlow()

    init {
        loadLeaderboard()
        loadAnalytics()
    }

    fun navigateTo(screen: Screen) {
        if (_currentScreen.value != screen) {
            backStack.add(_currentScreen.value)
            _currentScreen.value = screen
            if (screen == Screen.ANALYTICS) {
                loadAnalytics()
            } else if (screen == Screen.LEADERBOARD) {
                loadLeaderboard()
            }
        }
    }

    fun navigateBack(): Boolean {
        return if (backStack.isNotEmpty()) {
            val prev = backStack.removeAt(backStack.size - 1)
            _currentScreen.value = prev
            true
        } else {
            if (_currentScreen.value != Screen.HOME && repository.preferences.isOnboardingCompleted) {
                _currentScreen.value = Screen.HOME
                true
            } else {
                false
            }
        }
    }

    fun completeOnboarding(schoolType: SchoolType, grade: Int) {
        repository.setSchoolTypeAndGrade(schoolType, grade)
        _currentScreen.value = Screen.HOME
        _feedbackMessage.value = "${schoolType.title} - $grade. Sınıf müfredatı başarıyla yüklendi!"
    }

    fun selectSubject(subject: Subject) {
        _selectedSubject.value = subject
        navigateTo(Screen.SUBJECT_DETAIL)
    }

    fun selectTopic(topic: Topic) {
        _selectedTopic.value = topic
        navigateTo(Screen.TOPIC_EXPLANATION)
    }

    fun startQuizForTopic(topic: Topic) {
        _selectedTopic.value = topic
        _isPlacementMode.value = false
        val questions = repository.getQuestionsForTopic(topic.id)
        _quizQuestions.value = questions
        _currentQuestionIndex.value = 0
        _selectedOptionIndex.value = null
        _isAnswerSubmitted.value = false
        _correctCount.value = 0
        _wrongCount.value = 0
        _quizCompleted.value = false
        navigateTo(Screen.QUIZ)
    }

    fun startPlacementExam() {
        _isPlacementMode.value = true
        val questions = repository.getPlacementExamQuestions()
        _quizQuestions.value = questions
        _currentQuestionIndex.value = 0
        _selectedOptionIndex.value = null
        _isAnswerSubmitted.value = false
        _correctCount.value = 0
        _wrongCount.value = 0
        _quizCompleted.value = false
        navigateTo(Screen.PLACEMENT_EXAM)
    }

    fun selectQuizOption(index: Int) {
        if (!_isAnswerSubmitted.value) {
            _selectedOptionIndex.value = index
        }
    }

    fun submitCurrentAnswer() {
        val selected = _selectedOptionIndex.value ?: return
        if (_isAnswerSubmitted.value) return

        val currentQ = _quizQuestions.value.getOrNull(_currentQuestionIndex.value) ?: return
        _isAnswerSubmitted.value = true

        if (selected == currentQ.correctIndex) {
            _correctCount.value += 1
        } else {
            _wrongCount.value += 1
        }
    }

    fun nextQuizQuestion() {
        val nextIdx = _currentQuestionIndex.value + 1
        if (nextIdx < _quizQuestions.value.size) {
            _currentQuestionIndex.value = nextIdx
            _selectedOptionIndex.value = null
            _isAnswerSubmitted.value = false
        } else {
            // Quiz finished
            _quizCompleted.value = true
            val topic = _selectedTopic.value
            val topicId = topic?.id ?: if (_isPlacementMode.value) "placement" else "general"
            val topicTitle = if (_isPlacementMode.value) "Seviye Belirleme Sınavı" else (topic?.title ?: "Konu Testi")
            val subjectName = if (_isPlacementMode.value) "Genel Maarif" else (topic?.subjectId ?: "Genel")

            repository.submitQuizResult(
                topicId = topicId,
                topicTitle = topicTitle,
                subjectName = subjectName,
                totalQuestions = _quizQuestions.value.size,
                correctCount = _correctCount.value,
                wrongCount = _wrongCount.value,
                isPlacement = _isPlacementMode.value
            )
            loadAnalytics()
            loadLeaderboard()
        }
    }

    // Duel Battle Mode
    fun startDuel(opponentName: String = "Maarif Kâşifi (Zeynep)") {
        _duelOpponentName.value = opponentName
        val questions = repository.getPlacementExamQuestions().shuffled().take(5)
        _duelQuestions.value = questions
        _duelQuestionIndex.value = 0
        _userDuelScore.value = 0
        _botDuelScore.value = 0
        _duelCompleted.value = false
        _selectedOptionIndex.value = null
        _isAnswerSubmitted.value = false
        navigateTo(Screen.DUEL)
        startDuelTimer()
    }

    private fun startDuelTimer() {
        duelTimerJob?.cancel()
        _duelTimeRemaining.value = 12
        duelTimerJob = viewModelScope.launch {
            while (_duelTimeRemaining.value > 0 && !_duelCompleted.value) {
                delay(1000)
                _duelTimeRemaining.value -= 1
            }
            if (!_isAnswerSubmitted.value && !_duelCompleted.value) {
                // Time's up for this question
                submitDuelAnswer(null)
            }
        }
    }

    fun submitDuelAnswer(selectedIndex: Int?) {
        duelTimerJob?.cancel()
        _isAnswerSubmitted.value = true
        _selectedOptionIndex.value = selectedIndex

        val currentQ = _duelQuestions.value.getOrNull(_duelQuestionIndex.value) ?: return

        // User score calculation
        if (selectedIndex != null && selectedIndex == currentQ.correctIndex) {
            val speedBonus = _duelTimeRemaining.value * 2
            _userDuelScore.value += (100 + speedBonus)
        }

        // Bot simulation: 75% accuracy with small random response
        val botIsCorrect = (1..100).random() <= 75
        if (botIsCorrect) {
            val botSpeed = (2..10).random() * 2
            _botDuelScore.value += (100 + botSpeed)
        }

        viewModelScope.launch {
            delay(1500) // Brief pause to see results
            val nextIdx = _duelQuestionIndex.value + 1
            if (nextIdx < _duelQuestions.value.size) {
                _duelQuestionIndex.value = nextIdx
                _selectedOptionIndex.value = null
                _isAnswerSubmitted.value = false
                startDuelTimer()
            } else {
                _duelCompleted.value = true
                repository.recordDuelResult(
                    opponentName = _duelOpponentName.value,
                    userScore = _userDuelScore.value,
                    opponentScore = _botDuelScore.value,
                    subjectName = "Maarif Karışık Düello"
                )
                loadLeaderboard()
            }
        }
    }

    fun triggerCloudSync() {
        repository.syncManager.triggerAutoSync()
        _feedbackMessage.value = "Bulut senkronizasyonu başlatıldı..."
    }

    fun clearFeedbackMessage() {
        _feedbackMessage.value = null
    }

    fun loadAnalytics() {
        viewModelScope.launch {
            _analyticsReport.value = repository.getAnalyticsReport()
        }
    }

    fun loadLeaderboard() {
        _leaderboardUsers.value = repository.getLeaderboard()
    }
}
