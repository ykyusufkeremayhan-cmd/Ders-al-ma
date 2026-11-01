package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.QuizQuestion
import com.example.ui.components.AccuracyDonutChart
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.GoldXp
import com.example.ui.theme.PrimaryBlue
import com.example.ui.theme.SecondaryTeal
import com.example.ui.theme.SuccessGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    title: String,
    questions: List<QuizQuestion>,
    currentIndex: Int,
    selectedOption: Int?,
    isSubmitted: Boolean,
    correctCount: Int,
    wrongCount: Int,
    isCompleted: Boolean,
    isPlacementExam: Boolean,
    onOptionSelect: (Int) -> Unit,
    onSubmitAnswer: () -> Unit,
    onNextQuestion: () -> Unit,
    onBack: () -> Unit,
    onGoToAnalytics: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    val currentQ = questions.getOrNull(currentIndex)
    val totalCount = questions.size
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = if (isPlacementExam) "Seviye Belirleme Sınavı" else title,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            maxLines = 1
                        )
                        if (!isCompleted && totalCount > 0) {
                            Text(
                                text = "Soru ${currentIndex + 1} / $totalCount",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("btn_back_quiz")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Geri"
                        )
                    }
                },
                actions = {
                    if (!isCompleted) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(end = 12.dp)
                        ) {
                            Text(
                                text = "✓ $correctCount",
                                color = SuccessGreen,
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "✗ $wrongCount",
                                color = ErrorRed,
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        if (isCompleted) {
            // Quiz Completed Result Card
            QuizResultView(
                isPlacementExam = isPlacementExam,
                totalQuestions = totalCount,
                correctCount = correctCount,
                wrongCount = wrongCount,
                onBack = onBack,
                onGoToAnalytics = onGoToAnalytics,
                modifier = Modifier.padding(innerPadding)
            )
        } else if (currentQ != null) {
            // Active Question View
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Progress Bar
                LinearProgressIndicator(
                    progress = { (currentIndex.toFloat() + if (isSubmitted) 1f else 0.5f) / totalCount },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    color = PrimaryBlue,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(16.dp)
                ) {
                    // Question Box
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(PrimaryBlue.copy(alpha = 0.12f))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "Maarif Modeli Soru",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = PrimaryBlue,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }

                                if (currentQ.hint.isNotEmpty()) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(GoldXp.copy(alpha = 0.12f))
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Lightbulb,
                                            contentDescription = null,
                                            tint = GoldXp,
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "İpucu",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = GoldXp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            Text(
                                text = currentQ.questionText,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    lineHeight = 24.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Options List
                    currentQ.options.forEachIndexed { index, optionText ->
                        val isChosen = selectedOption == index
                        val isCorrectChoice = index == currentQ.correctIndex

                        val optionBgColor = when {
                            isSubmitted && isCorrectChoice -> SuccessGreen.copy(alpha = 0.15f)
                            isSubmitted && isChosen && !isCorrectChoice -> ErrorRed.copy(alpha = 0.15f)
                            isChosen -> PrimaryBlue.copy(alpha = 0.12f)
                            else -> MaterialTheme.colorScheme.surface
                        }

                        val borderColor = when {
                            isSubmitted && isCorrectChoice -> SuccessGreen
                            isSubmitted && isChosen && !isCorrectChoice -> ErrorRed
                            isChosen -> PrimaryBlue
                            else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                        }

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                                .testTag("quiz_option_$index")
                                .clip(RoundedCornerShape(14.dp))
                                .clickable(enabled = !isSubmitted) { onOptionSelect(index) },
                            color = optionBgColor,
                            border = BorderStroke(if (isChosen || (isSubmitted && isCorrectChoice)) 2.dp else 1.dp, borderColor),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape)
                                        .background(
                                            when {
                                                isSubmitted && isCorrectChoice -> SuccessGreen
                                                isSubmitted && isChosen -> ErrorRed
                                                isChosen -> PrimaryBlue
                                                else -> MaterialTheme.colorScheme.surfaceVariant
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = ('A' + index).toString(),
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                        color = if (isChosen || (isSubmitted && isCorrectChoice)) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Text(
                                    text = optionText,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Normal
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )

                                if (isSubmitted) {
                                    if (isCorrectChoice) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = "Doğru",
                                            tint = SuccessGreen,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    } else if (isChosen) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Yanlış",
                                            tint = ErrorRed,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Explanation Card (Shown when user submits)
                    AnimatedVisibility(
                        visible = isSubmitted,
                        enter = fadeIn() + slideInVertically()
                    ) {
                        Column {
                            Spacer(modifier = Modifier.height(14.dp))
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (selectedOption == currentQ.correctIndex)
                                        SuccessGreen.copy(alpha = 0.1f)
                                    else
                                        SecondaryTeal.copy(alpha = 0.1f)
                                )
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.AutoAwesome,
                                            contentDescription = null,
                                            tint = if (selectedOption == currentQ.correctIndex) SuccessGreen else SecondaryTeal,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "MEB & EBA Çözümlü Açıklama",
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = if (selectedOption == currentQ.correctIndex) SuccessGreen else SecondaryTeal
                                            )
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = currentQ.explanation,
                                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Bottom Action Button (Submit or Next)
                    if (!isSubmitted) {
                        Button(
                            onClick = onSubmitAnswer,
                            enabled = selectedOption != null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("btn_submit_quiz_answer"),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                        ) {
                            Text(
                                text = "Cevabı Onayla",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                        }
                    } else {
                        Button(
                            onClick = onNextQuestion,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("btn_next_quiz_question"),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (currentIndex + 1 < totalCount) PrimaryBlue else GoldXp
                            )
                        ) {
                            Text(
                                text = if (currentIndex + 1 < totalCount) "Sonraki Soru ➔" else "Testi Tamamla & Sonuçları Gör 🏆",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuizResultView(
    isPlacementExam: Boolean,
    totalQuestions: Int,
    correctCount: Int,
    wrongCount: Int,
    onBack: () -> Unit,
    onGoToAnalytics: () -> Unit,
    modifier: Modifier = Modifier
) {
    val accuracy = if (totalQuestions > 0) (correctCount * 100) / totalQuestions else 0
    val earnedXp = (correctCount * 15) + 20

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape)
                .background(GoldXp.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.EmojiEvents,
                contentDescription = null,
                tint = GoldXp,
                modifier = Modifier.size(44.dp)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = if (isPlacementExam) "Seviye Belirleme Tamamlandı!" else "Harika İş Çıkardın!",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        Text(
            text = if (isPlacementExam)
                "Türkiye Yüzyılı Maarif Modeli kapsamında hazırbulunuşluk düzeyin analiz edildi."
            else
                "Konu kazanımlarını başarıyla pekiştirdin.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Donut Chart
        AccuracyDonutChart(percentage = accuracy)

        Spacer(modifier = Modifier.height(18.dp))

        // Score Cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ResultMetricCard(
                modifier = Modifier.weight(1f),
                title = "Doğru",
                value = "$correctCount",
                color = SuccessGreen
            )
            ResultMetricCard(
                modifier = Modifier.weight(1f),
                title = "Yanlış",
                value = "$wrongCount",
                color = ErrorRed
            )
            ResultMetricCard(
                modifier = Modifier.weight(1f),
                title = "Kazanılan XP",
                value = "+$earnedXp",
                color = GoldXp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onGoToAnalytics,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("btn_result_to_analytics"),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
        ) {
            Icon(imageVector = Icons.Default.Assessment, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Detaylı Gelişim Raporunu İncele",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("btn_result_back_to_lessons"),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(text = "Derslere Dön")
        }
    }
}

@Composable
fun ResultMetricCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    color: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                color = color
            )
        }
    }
}
