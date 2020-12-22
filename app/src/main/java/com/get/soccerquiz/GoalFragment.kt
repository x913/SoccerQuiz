package com.get.soccerquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.get.soccerquiz.databinding.FragmentGoalBinding


class GoalFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_goal, container, false)

        val binding = DataBindingUtil.inflate<FragmentGoalBinding>(inflater, R.layout.fragment_goal, container, false)
        binding.btnOneMoreTime.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_goalFragment_to_quizFragment)
        }
        return binding.root
    }


}