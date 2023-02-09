package com.example.foodrecipeapp.ui.overview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.example.foodrecipeapp.databinding.FragmentOverViewBinding
import com.example.foodrecipeapp.util.Recipe

import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class OverViewFragment : Fragment() {

private lateinit var binding: FragmentOverViewBinding
private lateinit var adapter :OverViewAdapter
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
        viewModel.getQurryRecipe2(binding.searchView.query.toString())
       setUpObservers()


        return view
    }
    private fun setUPUi(){

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

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.recyclerView.adapter = adapter
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
    private fun setUpObservers(){


        viewModel.myResponce4.observe(viewLifecycleOwner, Observer {

           val filterlist = ArrayList<Recipe>()
            it.results?.let {recipe->

                binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        viewModel.getQurryRecipe2(query.toString())
                        for(i in recipe)
                        {
                            if(i?.title?.lowercase(Locale.ROOT)!!.contains(binding.searchView.query)){
                                filterlist.add(i!!)
                            }
                        }

                            adapter.setData(filterlist)

                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        viewModel.getQurryRecipe2(newText.toString())
                        for(i in recipe)
                        {
                            if(i?.title?.lowercase(Locale.ROOT)!!.contains(binding.searchView.query)){
                                filterlist.add(i!!)
                            }
                        }

                            adapter.setData(filterlist)
                        
                        return true
                    }

                })


//                        Timber.e(recipe.toString())
//                        adapter.setData(recipe.filterNotNull())

                }


        })
        viewModel.myResponce5.observe(viewLifecycleOwner, Observer {
            it.results?.let {recipe->

                adapter.setData(recipe.filterNotNull())
            }
        })


    }




}