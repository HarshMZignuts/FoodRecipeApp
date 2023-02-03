package com.example.foodrecipeapp.util

import androidx.recyclerview.widget.DiffUtil

class DiffUtill(
     val oldList : List<Recipe?>,
     val newList: List<Recipe?>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]?.id == newList[newItemPosition]?.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition]?.id != newList[newItemPosition]?.id->{
                false
            }
            oldList[oldItemPosition]?.title != newList[newItemPosition]?.title->{
                false
            }
            oldList[oldItemPosition]?.image != newList[newItemPosition]?.image->{
                false
            }
            oldList[oldItemPosition]?.imageType != newList[newItemPosition]?.imageType->{
                false
            }
            else->true
        }
    }

}