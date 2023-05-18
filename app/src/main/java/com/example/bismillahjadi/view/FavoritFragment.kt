package com.example.bismillahjadi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bismillahjadi.databinding.FragmentFavoritBinding
import com.example.bismillahjadi.view.adapter.FavoritAdapter
import com.example.bismillahjadi.viewmodel.FavoritViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritFragment : Fragment() {
    private lateinit var binding: FragmentFavoritBinding
    private lateinit var favoriteAdapter: FavoritAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoritBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onStart() {
        super.onStart()

        val viewModel = ViewModelProvider(this)[FavoritViewModel::class.java]
        viewModel.getFavoriteMovie().observe(this) {
            favoriteAdapter = FavoritAdapter(it)
            val layoutManager = GridLayoutManager(context,2)
            binding.rvFavMovie.layoutManager = layoutManager
            binding.rvFavMovie.adapter = FavoritAdapter(it)
        }


    }

}