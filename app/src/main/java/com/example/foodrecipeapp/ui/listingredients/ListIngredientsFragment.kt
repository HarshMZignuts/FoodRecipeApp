package com.example.foodrecipeapp.ui.listingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.databinding.FragmentListIngredientsBinding
import com.example.foodrecipeapp.ui.detail.DetailIngrideantAdapter
import com.example.foodrecipeapp.ui.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListIngredientsFragment : Fragment() {
    private lateinit var binding : FragmentListIngredientsBinding
    private lateinit var adapter : ListIngredientsAdapter
    private val viewModel : DetailViewModel by viewModels()
    private val args by navArgs<ListIngredientsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListIngredientsBinding.inflate(inflater,container,false)
       setUpUi()
        setUpObsver()
        return binding.root
    }
    private fun setUpUi(){
        adapter = ListIngredientsAdapter()
        binding.listRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.listRecycler.adapter = adapter

        args.id.let {
            viewModel.getRecipe2(it)
        }
    }
    private fun setUpObsver(){
        viewModel.myResponce2.observe(viewLifecycleOwner, Observer {
            it.let {
                it.body().let {
                    it?.extendedIngredients?.let {
                        adapter.setData(it)
                    }
                }
            }
        })
    }

}