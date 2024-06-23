package com.glucode.about_you.mockdata

import com.glucode.about_you.engineers.models.Answer
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.engineers.models.Question
import com.glucode.about_you.engineers.models.QuickStats

object MockData {
    val engineers = mutableListOf(
        Engineer(
            name = "Reenen",
            role = "Dev manager",
            defaultImageName = "",
            quickStats = QuickStats(years = 6, coffees = 5400, bugs = 1800),
            questions = listOf(
                Question.One(Answer(text = "6am", index = 0)),
                Question.Two(Answer(text = "10 to 15 years old", index = 1)),
                Question.Three(Answer(text = "Python", index = 0)),
                Question.Four(Answer(text = "Every few months", index = 0)),
                Question.Five(Answer(text = "Watch or read a tutorial", index = 3))
            )
        ),
        Engineer(
            name = "Wilmar",
            role = "Head of Engineering",
            defaultImageName = "",
            quickStats = QuickStats(years = 15, coffees = 4000, bugs = 4000),
            questions = listOf(
                Question.One(Answer(text = "midnight", index = 3)),
                Question.Two(Answer(text = "10 to 15 years old", index = 1)),
                Question.Three(Answer(text = "Python", index = 0)),
                Question.Four(Answer(text = "Every few months", index = 0)),
                Question.Five(Answer(text = "Call a coworker or friend", index = 2))
            )
        ),
        Engineer(
            name = "Eben",
            role = "Head of Testing",
            defaultImageName = "",
            quickStats = QuickStats(years = 14, coffees = 1000, bugs = 100),
            questions = listOf(
                Question.One(Answer(text = "midnight", index = 3)),
                Question.Two(Answer(text = "10 to 15 years old", index = 0)),
                Question.Three(Answer(text = "Kotlin", index = 1)),
                Question.Four(Answer(text = "Every few months", index = 0)),
                Question.Five(Answer(text = "Watch or read a tutorial", index = 3))
            )
        ),
        Engineer(
            name = "Stefan",
            role = "Senior dev",
            defaultImageName = "",
            quickStats = QuickStats(years = 7, coffees = 9000, bugs = 700),
            questions = listOf(
                Question.One(Answer(text = "6am", index = 0)),
                Question.Two(Answer(text = "21 to 25 years old", index = 3)),
                Question.Three(Answer(text = "Ruby", index = 3)),
                Question.Four(Answer(text = "Once a year", index = 1)),
                Question.Five(Answer(text = "Visit Stack Overflow", index = 0))
            )
        ),
        Engineer(
            name = "Brandon",
            role = "Senior dev",
            defaultImageName = "",
            quickStats = QuickStats(years = 9, coffees = 99999, bugs = 99999),
            questions = listOf(
                Question.One(Answer(text = "6am", index = 0)),
                Question.Two(Answer(text = "10 to 15 years old", index = 1)),
                Question.Three(Answer(text = "C++", index = 5)),
                Question.Four(Answer(text = "Every few months", index = 0)),
                Question.Five(Answer(text = "Visit Stack Overflow", index = 0))
            )
        ),
        Engineer(
            name = "Henri",
            role = "Senior dev",
            defaultImageName = "",
            quickStats = QuickStats(years = 10, coffees = 1800, bugs = 1000),
            questions = listOf(
                Question.One(Answer(text = "6am", index = 0)),
                Question.Two(Answer(text = "10 to 15 years old", index = 0)),
                Question.Three(Answer(text = "Rust", index = 6)),
                Question.Four(Answer(text = "Every few months", index = 0)),
                Question.Five(Answer(text = "Go down a google rabbit hole", index = 4))
            )
        )
    )

    fun updateEngineerImage(engineerName: String, newImageName: String) {
        val engineerToUpdate = engineers.find { it.name == engineerName }
        engineerToUpdate?.defaultImageName = newImageName
    }
}