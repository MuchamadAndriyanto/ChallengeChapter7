package com.example.bismillahjadi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bismillahjadi.databinding.FragmentFavoritBinding
import com.example.bismillahjadi.room.FavoritDatabase
import com.example.bismillahjadi.room.MovieFavorit
import com.example.bismillahjadi.view.adapter.FavoritAdapter
import com.example.bismillahjadi.viewmodel.FavoritViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Suppress("RedundantOverride", "RedundantSuppression", "RedundantSamConstructor")
@AndroidEntryPoint
class FavoritFragment : Fragment() {
    private lateinit var binding: FragmentFavoritBinding
    private var favDB: FavoritDatabase? = null
    private lateinit var adapter: FavoritAdapter
    private lateinit var viewmodel : FavoritViewModel


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
        favDB = FavoritDatabase.getInstance(requireContext())

        viewmodel = ViewModelProvider(this)[FavoritViewModel::class.java]

        adapter = FavoritAdapter(requireActivity(), ArrayList())

        binding.rvFavMovie.layoutManager = GridLayoutManager(context, 2)
        binding.rvFavMovie.adapter = adapter

        viewmodel = ViewModelProvider(this)[FavoritViewModel::class.java]
        viewmodel.getliveDataMoviefav().observe(viewLifecycleOwner, Observer {
            adapter.setMovie(it as ArrayList<MovieFavorit>)
        })
    }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            val moviedata = favDB?.favoritDao()!!.getAllMovieFavorit()
            activity?.runOnUiThread {
                adapter = FavoritAdapter(requireActivity(), moviedata)
                binding.rvFavMovie.layoutManager = GridLayoutManager(context, 2)
                binding.rvFavMovie.adapter = adapter


            }
        }
    }

}