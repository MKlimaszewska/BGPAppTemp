package com.bgpapp.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bgpapp.BR

class RecyclerAdapter<T>(@LayoutRes val layoutId: Int) : RecyclerView.Adapter<RecyclerAdapter.ItemHolder<T>>(), BindableAdapter<List<T>> {

    private val items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder<T> {
        val inflate = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflate, layoutId, parent, false)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemHolder<T>, position: Int) {
        holder.bind(items[position])
    }

    override fun setData(data: List<T>) {
        if(items.isNotEmpty()) {
            items.clear()
        }
        items.addAll(data)
        notifyDataSetChanged()
    }

    class ItemHolder<T>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.setVariable(BR.viewModel, item)
            binding.executePendingBindings()
        }
    }

}