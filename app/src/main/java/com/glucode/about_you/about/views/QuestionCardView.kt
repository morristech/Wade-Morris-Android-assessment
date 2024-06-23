package com.glucode.about_you.about.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewQuestionCardBinding

class QuestionCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private val binding: ViewQuestionCardBinding =
        ViewQuestionCardBinding.inflate(LayoutInflater.from(context), this)

    var title: String? = null
        set(value) {
            field = value
            binding.title.text = value
        }

    var answers: List<String> = listOf()
        set(value) {
            field = value
            binding.answers.removeAllViews()
            value.forEach { answer ->
                addAnswer(answer)
            }
        }

    var selection: Int? = null
        set(value) {
            field = value
            value ?: return
            binding.answers.children.elementAt(value).isSelected = true
        }

    init {
        radius = resources.getDimension(R.dimen.corner_radius_normal)
        elevation = resources.getDimension(R.dimen.elevation_normal)
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.black))
    }

    private fun addAnswer(title: String) {
        val answerView = AnswerCardView(context)
        answerView.title = title
        answerView.setOnClickListener { onAnswerClick(it) }
        binding.answers.addView(answerView)
    }

    private fun onAnswerClick(view: View) {
        val answerCardViews = binding.answers.children.filterIsInstance<AnswerCardView>().toList()
        val clickedIndex = answerCardViews.indexOf(view)
        if (clickedIndex != -1) {
            setSelection(clickedIndex)
        }
    }

    private fun setSelection(index: Int) {
        binding.answers.children.forEachIndexed { i, view ->
            view.isSelected = (i == index)
        }
    }
}