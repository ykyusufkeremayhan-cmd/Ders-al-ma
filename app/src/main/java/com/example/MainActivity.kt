package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SportsKabaddi
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.SchoolType
import com.example.ui.screens.AnalyticsDashboardScreen
import com.example.ui.screens.DuelScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LeaderboardScreen
import com.example.ui.screens.OnboardingScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.SubjectDetailScreen
import com.example.ui.screens.TopicExplanationScreen
import com.example.ui.theme.MaarifAkademiTheme
import com.example.ui.viewmodel.MaarifViewModel
import com.example.ui.viewmodel.Screen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaarifAkademiTheme {
                MaarifAppRoot()
            }
        }
    }
}

@Composable
fun MaarifAppRoot(
    viewModel: MaarifViewModel = viewModel()
) {
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val dailyQuests by viewModel.dailyQuests.collectAsStateWithLifecycle()
    val badges by viewModel.badges.collectAsStateWithLifecycle()
    val selectedSubject by viewModel.selectedSubject.collectAsStateWithLifecycle()
    val selectedTopic by viewModel.selectedTopic.collectAsStateWithLifecycle()

    val quizQuestions by viewModel.quizQuestions.collectAsStateWithLifecycle()
    val currentQuizIndex by viewModel.currentQuestionIndex.collectAsStateWithLifecycle()
    val selectedOption by viewModel.selectedOptionIndex.collectAsStateWithLifecycle()
    val isAnswerSubmitted by viewModel.isAnswerSubmitted.collectAsStateWithLifecycle()
    val correctCount by viewModel.correctCount.collectAsStateWithLifecycle()
    val wrongCount by viewModel.wrongCount.collectAsStateWithLifecycle()
    val isQuizCompleted by viewModel.quizCompleted.collectAsStateWithLifecycle()
    val isPlacementMode by viewModel.isPlacementMode.collectAsStateWithLifecycle()

    val duelQuestions by viewModel.duelQuestions.collectAsStateWithLifecycle()
    val duelIndex by viewModel.duelQuestionIndex.collectAsStateWithLifecycle()
    val userDuelScore by viewModel.userDuelScore.collectAsStateWithLifecycle()
    val botDuelScore by viewModel.botDuelScore.collectAsStateWithLifecycle()
    val duelTime by viewModel.duelTimeRemaining.collectAsStateWithLifecycle()
    val isDuelCompleted by viewModel.duelCompleted.collectAsStateWithLifecycle()
    val duelOpponentName by viewModel.duelOpponentName.collectAsStateWithLifecycle()

    val analyticsReport by viewModel.analyticsReport.collectAsStateWithLifecycle()
    val leaderboardUsers by viewModel.leaderboardUsers.collectAsStateWithLifecycle()
    val feedbackMessage by viewModel.feedbackMessage.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(feedbackMessage) {
        feedbackMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearFeedbackMessage()
        }
    }

    // Determine whether to show bottom navigation bar
    val showBottomBar = currentScreen in listOf(
        Screen.HOME,
        Screen.LEADERBOARD,
        Screen.ANALYTICS,
        Screen.PROFILE
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            if (showBottomBar) {
                MaarifBottomBar(
                    currentScreen = currentScreen,
                    onNavigate = { screen -> viewModel.navigateTo(screen) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = currentScreen,
                label = "ScreenTransition",
                transitionSpec = { fadeIn() togetherWith fadeOut() }
            ) { screen ->
                when (screen) {
                    Screen.ONBOARDING -> {
                        OnboardingScreen(
                            onComplete = { schoolType, grade ->
                                viewModel.completeOnboarding(schoolType, grade)
                            }
                        )
                    }

                    Screen.HOME -> {
                        val subjects = viewModel.repository.getSubjectsForCurrentConfig()
                        HomeScreen(
                            userProfile = userProfile,
                            subjects = subjects,
                            dailyQuests = dailyQuests,
                            onSubjectClick = { subject -> viewModel.selectSubject(subject) },
                            onStartPlacementClick = { viewModel.startPlacementExam() },
                            onStartDuelClick = { viewModel.startDuel() },
                            onAnalyticsClick = { viewModel.navigateTo(Screen.ANALYTICS) },
                            onSyncClick = { viewModel.triggerCloudSync() }
                        )
                    }

                    Screen.SUBJECT_DETAIL -> {
                        selectedSubject?.let { subject ->
                            val topics = viewModel.repository.getTopicsForSubject(subject.id)
                            SubjectDetailScreen(
                                subject = subject,
                                topics = topics,
                                grade = userProfile.grade,
                                onBack = { viewModel.navigateBack() },
                                onTopicClick = { topic -> viewModel.selectTopic(topic) },
                                onStartQuizClick = { topic -> viewModel.startQuizForTopic(topic) }
                            )
                        } ?: viewModel.navigateTo(Screen.HOME)
                    }

                    Screen.TOPIC_EXPLANATION -> {
                        selectedTopic?.let { topic ->
                            TopicExplanationScreen(
                                topic = topic,
                                onBack = { viewModel.navigateBack() },
                                onStartQuizClick = { viewModel.startQuizForTopic(topic) }
                            )
                        } ?: viewModel.navigateTo(Screen.HOME)
                    }

                    Screen.QUIZ, Screen.PLACEMENT_EXAM -> {
                        QuizScreen(
                            title = selectedTopic?.title ?: "Etkileşimli Test",
                            questions = quizQuestions,
                            currentIndex = currentQuizIndex,
                            selectedOption = selectedOption,
                            isSubmitted = isAnswerSubmitted,
                            correctCount = correctCount,
                            wrongCount = wrongCount,
                            isCompleted = isQuizCompleted,
                            isPlacementExam = isPlacementMode,
                            onOptionSelect = { option -> viewModel.selectQuizOption(option) },
                            onSubmitAnswer = { viewModel.submitCurrentAnswer() },
                            onNextQuestion = { viewModel.nextQuizQuestion() },
                            onBack = { viewModel.navigateBack() },
                            onGoToAnalytics = { viewModel.navigateTo(Screen.ANALYTICS) }
                        )
                    }

                    Screen.DUEL -> {
                        DuelScreen(
                            opponentName = duelOpponentName,
                            questions = duelQuestions,
                            currentIndex = duelIndex,
                            timeRemaining = duelTime,
                            userScore = userDuelScore,
                            botScore = botDuelScore,
                            selectedOption = selectedOption,
                            isSubmitted = isAnswerSubmitted,
                            isCompleted = isDuelCompleted,
                            onOptionSelect = { option -> viewModel.submitDuelAnswer(option) },
                            onBack = { viewModel.navigateBack() },
                            onRestartDuel = { viewModel.startDuel() }
                        )
                    }

                    Screen.ANALYTICS -> {
                        AnalyticsDashboardScreen(
                            report = analyticsReport,
                            onBack = { viewModel.navigateBack() }
                        )
                    }

                    Screen.LEADERBOARD -> {
                        LeaderboardScreen(
                            users = leaderboardUsers,
                            onBack = { viewModel.navigateBack() }
                        )
                    }

                    Screen.PROFILE -> {
                        ProfileScreen(
                            userProfile = userProfile,
                            badges = badges,
                            onBack = { viewModel.navigateBack() },
                            onChangeSchoolOrGradeClick = { viewModel.navigateTo(Screen.ONBOARDING) },
                            onSyncClick = { viewModel.triggerCloudSync() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MaarifBottomBar(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .navigationBarsPadding()
            .testTag("maarif_bottom_bar")
    ) {
        NavigationBarItem(
            selected = currentScreen == Screen.HOME,
            onClick = { onNavigate(Screen.HOME) },
            icon = { Icon(Icons.Default.School, contentDescription = "Dersler") },
            label = { Text("Dersler", fontWeight = FontWeight.SemiBold) },
            modifier = Modifier.testTag("nav_item_home")
        )

        NavigationBarItem(
            selected = currentScreen == Screen.DUEL,
            onClick = { onNavigate(Screen.DUEL) },
            icon = { Icon(Icons.Default.SportsKabaddi, contentDescription = "Düello") },
            label = { Text("Düello", fontWeight = FontWeight.SemiBold) },
            modifier = Modifier.testTag("nav_item_duel")
        )

        NavigationBarItem(
            selected = currentScreen == Screen.ANALYTICS,
            onClick = { onNavigate(Screen.ANALYTICS) },
            icon = { Icon(Icons.Default.Assessment, contentDescription = "Analiz") },
            label = { Text("Analiz", fontWeight = FontWeight.SemiBold) },
            modifier = Modifier.testTag("nav_item_analytics")
        )

        NavigationBarItem(
            selected = currentScreen == Screen.LEADERBOARD,
            onClick = { onNavigate(Screen.LEADERBOARD) },
            icon = { Icon(Icons.Default.EmojiEvents, contentDescription = "Liderlik") },
            label = { Text("Liderlik", fontWeight = FontWeight.SemiBold) },
            modifier = Modifier.testTag("nav_item_leaderboard")
        )

        NavigationBarItem(
            selected = currentScreen == Screen.PROFILE,
            onClick = { onNavigate(Screen.PROFILE) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profil") },
            label = { Text("Profil", fontWeight = FontWeight.SemiBold) },
            modifier = Modifier.testTag("nav_item_profile")
        )
    }
}
