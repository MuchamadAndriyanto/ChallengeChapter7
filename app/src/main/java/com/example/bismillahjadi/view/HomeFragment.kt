package com.example.bismillahjadi.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bismillahjadi.R
import com.example.bismillahjadi.databinding.FragmentHomeBinding
import com.example.bismillahjadi.view.adapter.MovieTopAdapter
import com.example.bismillahjadi.viewmodel.ListMovieTopViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var pref: SharedPreferences
    private val ViewModel: ListMovieTopViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getDataMovie()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pref = requireActivity().getSharedPreferences("Register", Context.MODE_PRIVATE)
        val username = pref.getString("username", "username")
        binding.tvWelcome.text = "Welcome, $username"


        //Profile
        binding.btnProfile.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_profileFragment)
        }


    }
    override fun onStart(){
        super.onStart()

        ViewModel.getMovies().observe(requireActivity()) {
            val adapter = MovieTopAdapter(it)
            val layoutManager = GridLayoutManager(context,2)
            binding.rvTopRated.layoutManager = layoutManager
            binding.rvTopRated.adapter = adapter
        }

    }
}
