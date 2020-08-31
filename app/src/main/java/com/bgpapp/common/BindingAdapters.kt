package com.bgpapp.common

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

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