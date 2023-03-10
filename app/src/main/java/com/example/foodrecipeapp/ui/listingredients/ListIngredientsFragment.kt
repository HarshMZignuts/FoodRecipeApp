package com.example.foodrecipeapp.ui.listingredients

import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.databinding.FragmentListIngredientsBinding
import com.example.foodrecipeapp.ui.detail.DetailIngrideantAdapter
import com.example.foodrecipeapp.ui.detail.DetailViewModel
import com.example.foodrecipeapp.util.NetworkResult
import com.example.foodrecipeapp.util.snackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListIngredientsFragment : Fragment() {
    private var _binding: FragmentListIngredientsBinding? = null
    private val binding: FragmentListIngredientsBinding
        get() = _binding!!
    private lateinit var adapter: ListIngredientsAdapter
    private val viewModel: DetailViewModel by viewModels()
    private val args by navArgs<ListIngredientsFragmentArgs>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListIngredientsBinding.inflate(inflater, container, false)
        setUpUi()
        setUpObsver()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setUpUi() {
        binding.listRecycler.shimmerLayout = R.layout.data_place_holder_indilist
        adapter = ListIngredientsAdapter()
        binding.listRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.listRecycler.adapter = adapter

        args.id.let {
            viewModel.getRecipe2(it)
        }
    }

    private fun setUpObsver() {
        viewModel.myResponceData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is NetworkResult.Error -> {
                    response.message?.snackBar(requireView(), requireContext())
                }
                is NetworkResult.Loading -> {
                    binding.listRecycler.showShimmer()


                }
                is NetworkResult.Success -> {
                    binding.listRecycler.hideShimmer()
                    response.data.let { detail ->

                        detail?.extendedIngredients?.let { exingi ->
                            adapter.setData(exingi)
                        }

                    }
                }

                else -> {}
            }

        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}