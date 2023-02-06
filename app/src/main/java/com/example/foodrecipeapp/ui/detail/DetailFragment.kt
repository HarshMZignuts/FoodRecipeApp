package com.example.foodrecipeapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : Fragment() {
private lateinit var binding : FragmentDetailBinding
private val viewModel : DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        
        setupUi()
        setUpObservers()
        return binding.root
    }
    private fun setupUi(){

        args.id.let {
            Timber.e(it.toString())
          viewModel.getRecipe2(it)
          
        }
    }
    private fun setUpObservers(){
        viewModel.myResponce2.observe(viewLifecycleOwner, Observer {
            it.let {
                it.body().let {
                    binding.detailViewModel = it
                    it?.nutrition?.nutrients?.forEach {
                        if(it.name == "Carbohydrates")
                        {
                            binding.progressBar.progress = it.amount!!.toInt()
                        }
                        else if (it.name == "Fat"){
                            binding.progressBarFat.progress = it.amount!!.toInt()
                        }
                        else if(it.name == "Protein"){
                            binding.progressBarProtien.progress = it.amount!!.toInt()
                        }
                    }

                }
            }
        })



}}