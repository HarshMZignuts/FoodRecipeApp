package com.example.foodrecipeapp.ui.overview

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.annotation.RequiresApi

import androidx.appcompat.widget.SearchView.OnQueryTextListener

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodrecipeapp.R


import com.example.foodrecipeapp.databinding.FragmentOverViewBinding
import com.example.foodrecipeapp.util.NetworkResult

import com.example.foodrecipeapp.util.snackBar

import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*


@AndroidEntryPoint
class OverViewFragment : Fragment() {

    private var _binding: FragmentOverViewBinding? = null
    private val binding: FragmentOverViewBinding
        get() = _binding!!
    private lateinit var adapter: OverViewAdapter
    private val viewModel: OverviewViewModel by viewModels()

    private var lastQuery = "";

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        return inflater.inflate(R.layout.fragment_over_view, container, false)
        _binding = FragmentOverViewBinding.inflate(LayoutInflater.from(context))
        val view = binding.root
        setUPUi()
        setUpObservers()


        return view
    }

    private fun setUPUi() {

        adapter = OverViewAdapter(onMainClick = {
            Timber.e(it.title)


            it.id?.let { it ->

                findNavController().navigate(
                    OverViewFragmentDirections.actionOverViewFragmentToDetailFragment(it)
                )
            }
        }

        )
        binding.recyclerView.shimmerLayout = R.layout.data_place_hloder

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty() && query != lastQuery) {
                    lastQuery = query
                    viewModel.getQurryRecipe2(query.toString())
                }

                return true
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty() && newText != lastQuery) {
                    lastQuery = newText
                    viewModel.getQurryRecipe2(newText.toString())
                }

                return true
            }

        })


    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setUpObservers() {


        viewModel.myRecipeResponce.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is NetworkResult.Error -> {
                    response.message?.snackBar(requireView(), requireContext())
                }
                is NetworkResult.Loading -> {
                    binding.recyclerView.showShimmer()
                }
                is NetworkResult.Success -> {
                    response.data?.results.let { recipe ->
                        binding.recyclerView.hideShimmer()
                        adapter.setData(recipe?.filterNotNull() ?: emptyList())
                    }
                }
            }
        })


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
