package com.example.todolistapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentSplashScreenBinding
import com.example.todolistapp.utils.changePage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {
    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)
        binding.splashScreenFragment = this

        return binding.root
    }

    fun clickButton(view: View) {
        Navigation.changePage(view, R.id.action_splashScreenFragment_to_homePageFragment)
    }
}