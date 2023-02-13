package com.example.foodrecipeapp.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.DecimalFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
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
import com.example.foodrecipeapp.ui.overview.OverViewFragment
import com.example.foodrecipeapp.ui.overview.OverViewFragmentDirections
import com.example.foodrecipeapp.util.NetworkResult
import com.example.foodrecipeapp.util.snackBar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    private val binding: FragmentDetailBinding
        get() = _binding!!

    private lateinit var adapter: DetailIngrideantAdapter
    private val viewModel: DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        setupUi()
        setUpObservers()



        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
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
        viewModel.myResponceData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is NetworkResult.Error -> {
                    response.message?.snackBar(requireView(), requireContext())
                }
                is NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {
                    response.data.let { detail ->

                        binding.detailViewModel = detail
                        val df = DecimalFormat("#.##")

                        detail?.nutrition?.nutrients?.forEach { nutrient ->

                            when (nutrient.name) {
                                "Carbohydrates" -> {
                                    Timber.e("Carbohydrates ${nutrient.percentOfDailyNeeds}")
                                    binding.progressBar.progress =
                                        nutrient.percentOfDailyNeeds!!.toInt()
                                    binding.tvCarbAmount.text = nutrient.amount.toString()
                                    binding.tvCarbUnit.text = nutrient.unit
                                    binding.tvCarbPer.text = this.resources.getString(
                                        R.string.percentage_forment,
                                        df.format(nutrient.percentOfDailyNeeds)
                                    )
                                }
                                "Fat" -> {
                                    Timber.e("Fat ${nutrient.percentOfDailyNeeds}")
                                    binding.progressBarFat.progress =
                                        nutrient.percentOfDailyNeeds!!.toInt()
                                    binding.tvFatAmount.text = nutrient.amount.toString()
                                    binding.tvFatUnit.text = nutrient.unit
                                    binding.tvFatPer.text = this.resources.getString(
                                        R.string.percentage_forment,
                                        df.format(nutrient.percentOfDailyNeeds)
                                    )
                                }
                                "Protein" -> {
                                    Timber.e("Protein ${nutrient.percentOfDailyNeeds}")
                                    binding.progressBarProtien.progress =
                                        nutrient.percentOfDailyNeeds!!.toInt()
                                    binding.tvProAmount.text = nutrient.amount.toString()
                                    binding.tvProUnit.text = nutrient.unit
                                    binding.tvProPer.text = this.resources.getString(
                                        R.string.percentage_forment,
                                        df.format(nutrient.percentOfDailyNeeds)
                                    )
                                }
                                "Calories" -> {
                                    Timber.e("Calories ${nutrient.percentOfDailyNeeds}")
                                    binding.progressBarCal.progress =
                                        nutrient.percentOfDailyNeeds!!.toInt()
                                    binding.tvCalAmount.text = nutrient.amount.toString()
                                    binding.tvCalUnit.text = nutrient.unit
                                }
                            }
                        }

                        val url = detail?.sourceUrl
                        binding.tvSeeDetailRecipe.setOnClickListener {

                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse(url)
                            startActivity(intent)
                        }

                        detail?.extendedIngredients?.let { responce ->
                            adapter.setData(responce)
                        }

                        //this is for rating
                        val rating: Float = detail?.healthScore!!.toFloat() * 5 / 100
                        binding.ratingBar.rating = rating
                        //set id for see indigidiants
                        detail.id?.let { it ->
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
                else -> {}
            }

        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}