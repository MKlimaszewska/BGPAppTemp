package com.bgpapp.common

import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(data)
    }
}

@BindingAdapter("android:visibility")
fun View.changeVisibility(value: Boolean) {
    if(value) {
        visibility = View.VISIBLE
    } else {
        visibility = View.GONE
    }
}

@BindingAdapter("bgp:image")
fun ImageView.setImage(value: String) {
    Glide.with(this.context).load(value).into(this)
}

@BindingAdapter("adapter")
fun AutoCompleteTextView.setupAdapter(adapter: ArrayAdapter<*>) {
    setAdapter(adapter)
}