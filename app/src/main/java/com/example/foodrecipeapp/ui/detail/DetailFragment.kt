package com.example.foodrecipeapp.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.DecimalFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.ActionProvider
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.databinding.FragmentDetailBinding
import com.example.foodrecipeapp.ui.listingredients.ListIngredientsFragment
import com.example.foodrecipeapp.ui.overview.OverViewFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var adapter: DetailIngrideantAdapter
    private val viewModel: DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        setupUi()
        setUpObservers()
        return binding.root
    }

    private fun setupUi() {
        adapter = DetailIngrideantAdapter()
        binding.recycleIndi.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.imgViewBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.recycleIndi.adapter = adapter
        args.id.let {
            Timber.e(it.toString())
            viewModel.getRecipe2(it)

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("StringFormatInvalid", "SuspiciousIndentation")
    private fun setUpObservers() {
        viewModel.myResponce2.observe(viewLifecycleOwner, Observer {
            it.let {
                it.body().let {
                    binding.detailViewModel = it
                    val df = DecimalFormat("#.##")
                    it?.nutrition?.nutrients?.forEach {
                        var perv = it.amount!! * it.percentOfDailyNeeds!! / 100
                        when (it.name) {
                            "Carbohydrates" -> {
                                Timber.e("Carbohydrates ${it.percentOfDailyNeeds}")
                                binding.progressBar.progress = it.percentOfDailyNeeds.toInt()
                                binding.tvCarbAmount.text = it.amount.toString()
                                binding.tvCarbUnit.text = it.unit
                                binding.tvCarbPer.text = this.resources.getString(
                                    R.string.percentage_forment,
                                    df.format(it.percentOfDailyNeeds)
                                )
                            }
                            "Fat" -> {
                                Timber.e("Fat ${it.percentOfDailyNeeds}")
                                binding.progressBarFat.progress = it.percentOfDailyNeeds.toInt()
                                binding.tvFatAmount.text = it.amount.toString()
                                binding.tvFatUnit.text = it.unit
                                binding.tvFatPer.text = this.resources.getString(
                                    R.string.percentage_forment,
                                    df.format(it.percentOfDailyNeeds)
                                )
                            }
                            "Protein" -> {
                                Timber.e("Protein ${it.percentOfDailyNeeds}")
                                binding.progressBarProtien.progress = it.percentOfDailyNeeds.toInt()
                                binding.tvProAmount.text = it.amount.toString()
                                binding.tvProUnit.text = it.unit
                                binding.tvProPer.text = this.resources.getString(
                                    R.string.percentage_forment,
                                    df.format(it.percentOfDailyNeeds)
                                )
                            }
                            "Calories" -> {
                                Timber.e("Calories ${it.percentOfDailyNeeds}")
                                binding.progressBarCal.progress = it.percentOfDailyNeeds.toInt()
                                binding.tvCalAmount.text = it.amount.toString()
                                binding.tvCalUnit.text = it.unit
                            }
                        }
                    }
                    var url = it?.sourceUrl
                    binding.tvSeeDetailRecipe.setOnClickListener {
                        var intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    }

                    it?.extendedIngredients?.let { responce ->
                        adapter.setData(responce)
                    }
                    //this is for rating
                    val rating: Float = it?.healthScore!!.toFloat() * 5 / 100
                    binding.ratingBar.rating = rating
                    //set id for see indigidiants
                    it.id?.let { it ->
                        binding.tvSeeDetailsIng.setOnClickListener { responce ->
                            findNavController().navigate(
                                DetailFragmentDirections.actionDetailFragmentToListIngredientsFragment(
                                    it
                                )
                            )
                        }
                    }


                }
            }
        })


    }
}