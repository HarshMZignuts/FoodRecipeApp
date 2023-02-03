package com.example.foodrecipeapp.ui.overview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.databinding.FragmentOverViewBinding
import com.example.foodrecipeapp.databinding.GridLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class OverViewFragment : Fragment() {

private lateinit var binding: FragmentOverViewBinding
private val adapter = OverViewAdapter()
    private val viewModel : OverviewViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        return inflater.inflate(R.layout.fragment_over_view, container, false)
        binding = FragmentOverViewBinding.inflate(LayoutInflater.from(context))
        val view = binding.root
        setUPUi()
        viewModel.getAllRecipe2()
       setUpObservers()

        return view
    }
    private fun setUPUi(){
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.recyclerView.adapter = adapter
    }
    private fun setUpObservers(){
        viewModel.myResponce4.observe(viewLifecycleOwner, Observer {
            it.results?.let {recipe->
                adapter.setData(recipe.filterNotNull())
            }
        })
        viewModel.myResponce5.observe(viewLifecycleOwner, Observer {
            it.results?.let {recipe->

                adapter.setData(recipe.filterNotNull())
            }
        })

    }


}