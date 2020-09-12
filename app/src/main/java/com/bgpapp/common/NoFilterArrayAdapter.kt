package com.bgpapp.common

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter

class NoFilterArrayAdapter<T>(context: Context, textViewResourceId: Int, private val objects: List<T>) : ArrayAdapter<T>(context, textViewResourceId, objects) {

    override fun getFilter(): Filter {
        return NoFilter()
    }

    private inner class NoFilter : Filter() {
        override fun performFiltering(arg0: CharSequence): FilterResults {
            val result = FilterResults()
            result.values = objects
            result.count = objects.size
            return result
        }

        override fun publishResults(arg0: CharSequence, arg1: FilterResults) {
            notifyDataSetChanged()
        }
    }
}