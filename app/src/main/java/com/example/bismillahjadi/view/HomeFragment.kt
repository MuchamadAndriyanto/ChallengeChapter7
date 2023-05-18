@file:Suppress("RedundantOverride",
    "UnusedImport"
)

package com.example.bismillahjadi.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bismillahjadi.R
import com.example.bismillahjadi.databinding.FragmentHomeBinding
import com.example.bismillahjadi.view.adapter.MovieTopAdapter
import com.example.bismillahjadi.viewmodel.ListMovieTopViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var pref: SharedPreferences
    private lateinit var movieadapter: MovieTopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setViewModel()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pref = requireActivity().getSharedPreferences("Register", Context.MODE_PRIVATE)
        val username = pref.getString("username", "username")
        binding.tvWelcome.text = "Welcome, $username"


        //Profile
        binding.btnProfile.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.btnFavorit.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_favoritFragment)
        }


    }
    override fun onStart() {
        super.onStart()

        val viewModel = ViewModelProvider(this)[ListMovieTopViewModel::class.java]
        viewModel.getlivedatamovie().observe(this) {
            movieadapter = MovieTopAdapter(it)
            val layoutManager = GridLayoutManager(context,2)
            binding.rvTopRated.layoutManager = layoutManager
            binding.rvTopRated.adapter = MovieTopAdapter(it)
        }

        viewModel.getAllMovieTop()


    }
}
