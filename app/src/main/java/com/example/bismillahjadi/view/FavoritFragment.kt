package com.example.bismillahjadi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bismillahjadi.R
import com.example.bismillahjadi.databinding.FragmentFavoritBinding
import com.example.bismillahjadi.room.FavoritDatabase
import com.example.bismillahjadi.view.adapter.FavoritAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritFragment : Fragment() {
    lateinit var binding: FragmentFavoritBinding
    private var fdMovie: FavoritDatabase? = null


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
        fdMovie = FavoritDatabase.getInstance(requireContext())
        getDataFav()


    }

    private fun getDataFav() {

        binding.rvFavMovie.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        GlobalScope.launch {
            val listdata = fdMovie?.favDao()!!.getAllFilmFavorites()
            activity?.runOnUiThread {
                listdata.observe(viewLifecycleOwner) {
                    //set adapter
                    binding.rvFavMovie.adapter = FavoritAdapter(it)
                }
            }
        }

    }
}