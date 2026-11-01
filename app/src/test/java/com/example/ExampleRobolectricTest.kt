package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.MaarifCurriculumData
import com.example.data.MaarifRepository
import com.example.model.GradeLevel
import com.example.model.SchoolType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun readStringFromContext() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Maarif Akademi", appName)
    }

    @Test
    fun testDevletSchoolCurriculumExcludesImamHatipOnlySubjects() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repo = MaarifRepository(context)
        repo.setSchoolTypeAndGrade(SchoolType.DEVLET, 5)

        val subjects = repo.getSubjectsForCurrentConfig()
        assertTrue(subjects.any { it.id == "turkce" })
        assertTrue(subjects.any { it.id == "matematik" })
        assertTrue(subjects.any { it.id == "fen" })
        assertFalse(subjects.any { it.isImamHatipOnly })
        assertFalse(subjects.any { it.id == "kuran" })
    }

    @Test
    fun testImamHatipSchoolCurriculumIncludesBothStandardAndSpecialSubjects() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repo = MaarifRepository(context)
        repo.setSchoolTypeAndGrade(SchoolType.IMAM_HATIP, 5)

        val subjects = repo.getSubjectsForCurrentConfig()
        // Standard courses
        assertTrue(subjects.any { it.id == "turkce" })
        assertTrue(subjects.any { it.id == "matematik" })
        // Special courses
        assertTrue(subjects.any { it.id == "kuran" })
        assertTrue(subjects.any { it.id == "siyer" })
        assertTrue(subjects.any { it.id == "arapca" })
    }

    @Test
    fun testAllGradesAreSupported() {
        val grades = listOf(5, 6, 7, 8)
        assertEquals(4, GradeLevel.entries.size)
        grades.forEach { grade ->
            val gradeEnum = GradeLevel.fromNumber(grade)
            assertEquals(grade, gradeEnum.number)
        }
    }
}
