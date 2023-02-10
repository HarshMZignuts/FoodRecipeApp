package com.example.foodrecipeapp.ui.overview

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi

import androidx.appcompat.widget.SearchView.OnQueryTextListener

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.databinding.FragmentDetailBinding

import com.example.foodrecipeapp.databinding.FragmentOverViewBinding
import com.example.foodrecipeapp.util.NetworkResult
import com.example.foodrecipeapp.util.Recipe

import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class OverViewFragment : Fragment() {

    private  var _binding: FragmentOverViewBinding? = null
    private val binding : FragmentOverViewBinding
    get() = _binding!!
    private lateinit var adapter: OverViewAdapter
    private val viewModel: OverviewViewModel by viewModels()

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

        viewModel.getAllRecipe2()
        viewModel.getQurryRecipe2(binding.searchView.query.toString())
        setUpObservers()


        return view
    }

    private fun setUPUi() {

        adapter = OverViewAdapter(onMainClick = {
            Timber.e(it.title)

            // Toast.makeText(requireContext(),"${it.title}",Toast.LENGTH_SHORT).show()
            it.id?.let { it ->

                findNavController().navigate(
                    OverViewFragmentDirections.actionOverViewFragmentToDetailFragment(it)
                )
            }
        }

        )
        binding.recyclerView.shimmerLayout = R.layout.data_place_hloder
        //binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getQurryRecipe2(query.toString())

                return true
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getQurryRecipe2(newText.toString())

                return true
            }

        })
//        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//
//                return false
//            }
//
//        })

    }

    private fun setUpObservers() {


//        viewModel.myResponce4.observe(viewLifecycleOwner, Observer {
//
//
//            it.results?.let { recipe ->
//
//
//
//
////                        Timber.e(recipe.toString())
////                        adapter.setData(recipe.filterNotNull())
//
//            }
//
//
//        })
        viewModel.myRecipeResponce.observe(viewLifecycleOwner, Observer {response->
            when(response){
                is NetworkResult.Error ->{
                    response.message
                }
                is NetworkResult.Loading ->{
                    binding.recyclerView.showShimmer()
                    Handler().postDelayed(Runnable {
                        binding.recyclerView.hideShimmer()
                    },3000)

                }
                is NetworkResult.Success ->{
                    response.data?.results.let { recipe->
                        adapter.setData(recipe?.filterNotNull() ?: emptyList())
                    }
                }

            }


        })




    }

    override fun onResume() {
        super.onResume()
//        binding.shimmerViewContainer.startShimmer()
    }

    override fun onPause() {

//        binding.shimmerViewContainer.stopShimmer()
        super.onPause()
    }


}