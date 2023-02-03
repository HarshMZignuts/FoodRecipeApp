package com.example.foodrecipeapp.ui.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.foodrecipeapp.databinding.GridLayoutBinding
import com.example.foodrecipeapp.util.DiffUtill
import com.example.foodrecipeapp.util.Recipe



class OverViewAdapter : RecyclerView.Adapter<OverViewAdapter.MyViewHolder>() {
    private var recipeList= emptyList<Recipe?>()

    class MyViewHolder(private val binding:GridLayoutBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(currentItem : Recipe){
            binding.girdModel = currentItem

        }
        companion object{
            fun from(parent: ViewGroup) : MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GridLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = recipeList.getOrNull(position)
        currentItem?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
    fun setData(recipeResponse: List<Recipe?>){
        val diffUtil = DiffUtill(recipeList,recipeResponse)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        recipeList=recipeResponse
        diffResult.dispatchUpdatesTo(this)

    }
}