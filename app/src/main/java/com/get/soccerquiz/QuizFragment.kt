package com.get.soccerquiz

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.get.soccerquiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    val quizItems = mutableListOf<QuizItem>(

        QuizItem("How many players does each team have on the pitch when a soccer match starts?", listOf<String>("11", "8", "12")),

        QuizItem("What should be the circumference of a Size 5 (adult) football?", listOf<String>("27\" to 28\"", "24\" to 25\"", "23\" to 24\"\n")),

        QuizItem("What is given to a player for a very serious personal foul on an opponent?", listOf<String>("Red Card", "Green Card", "Yellow Card")),

        QuizItem("To most places in the world, soccer is known as what?", listOf<String>("Football", "Footgame", "Legball")),

        QuizItem("Offside. If a player is offside, what action does the referee take?", listOf<String>(
            "Awards an indirect free kick to the opposing team",
            "Awards a penalty to the opposing team",
            "Awards a yellow card  to the player")),

        QuizItem("How many laws of Association Football are there?", listOf<String>("17", "11", "23")),

        QuizItem("Excluding the goalkeeper, what part of the body cannot touch the ball?", listOf<String>("Arm", "Head", "Shoulder")),

        QuizItem("What is it called when a player, without the ball on the offensive team is behind the last defender, or fullback?",
            listOf<String>(
                "Offside",
                "Outside",
                "Field-side")),
        QuizItem("The Ball. The circumference of the ball should not be greater than what?", listOf<String>("70", "80", "90")),

        QuizItem("How many minutes are played in a regular game (without injury time or extra time)?", listOf<String>("90", "95", "100")),

        QuizItem("What statement describes a proper throw-in?", listOf<String>(
            "Both hands must be on the ball behind the head, both feet must be on the ground",
            "Both hands must be on the ball behind the head",
            "Both feet must be on the ground")),

        QuizItem("How big is a regulation official soccer goal?",
            listOf<String>(
                "2.44m high, 7.32m wide",
                "2.55m high, 7.62m wide",
                "2.33m high, 8.15m wide")),

    )

    lateinit var currentQuizItem: QuizItem
    lateinit var answers: MutableList<String>
    private var quizItemIndex = 0
    private val numberOfQuestions = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentQuizBinding>(inflater, R.layout.fragment_quiz, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Answer the question"

        getRandomQuizItem()

        binding.quizFragment = this
        binding.btnOneMoreTime.setOnClickListener {

            val answerIndex = when(binding.quizRadioGroup.checkedRadioButtonId) {
                R.id.firstRadioButton -> 0
                R.id.secondRadioButton -> 1
                else -> 2
            }

            if(answers[answerIndex] == currentQuizItem.answers[0]) {
                quizItemIndex++
                if(quizItemIndex < numberOfQuestions) {
                    setQuizItem()
                    binding.invalidateAll() // show markup changes
                } else {
                    // navigate to goal fragment
                    view?.findNavController()?.navigate(R.id.action_quizFragment_to_goalFragment)
                }
            } else {
                // navigate to miss fragment
                view?.findNavController()?.navigate(R.id.action_quizFragment_to_missFragment)
            }

        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun setQuizItem() {
        currentQuizItem = quizItems[quizItemIndex]
        answers = currentQuizItem.answers.toMutableList()
        answers.shuffle()
    }

    private fun getRandomQuizItem() {
        quizItems.shuffle()
        quizItemIndex = 0
        setQuizItem()
    }

}