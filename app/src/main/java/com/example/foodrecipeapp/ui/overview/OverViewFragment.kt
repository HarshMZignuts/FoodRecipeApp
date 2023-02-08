package com.example.foodrecipeapp.ui.overview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.example.foodrecipeapp.databinding.FragmentOverViewBinding

import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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