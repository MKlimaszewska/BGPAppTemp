package com.bgpapp.ui.addevent


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.bgpapp.R
import com.bgpapp.common.NoFilterArrayAdapter
import com.bgpapp.databinding.FragmentAddEventBinding
import com.bgpapp.navigation.observeNavigation
import com.bgpapp.service.BGPService
import com.bgpapp.service.RestService
import com.bgpapp.service.RestServiceBuilder
import kotlinx.android.synthetic.main.fragment_add_event.*
import kotlinx.coroutines.launch
import java.util.*

class AddEventFragment : Fragment() {

    private val viewModel = AddEventViewModel(BGPService(RestServiceBuilder.build(RestService::class.java)))
    private val dateOfBirthDateListener =
        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            viewModel.updateDateOfBirth(year, month, dayOfMonth)
        }
    private val timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        viewModel.updateHour(hourOfDay, minute)
    }
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.observeNavigation(this)
        adapter = ArrayAdapter<String>(requireContext(), R.layout.support_simple_spinner_dropdown_item, android.R.id.text1)
        return DataBindingUtil.inflate<FragmentAddEventBinding>(inflater, R.layout.fragment_add_event, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
            it.meetingSpinnerAdapter = NoFilterArrayAdapter<String>(requireContext(), R.layout.support_simple_spinner_dropdown_item, listOf("TOURNAMENT", "CASUAL"))
            it.placeSpinnerAdapter = adapter
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        date.setOnClickListener {
            it.requestFocusFromTouch()
            DatePickerDialog(
                requireContext(),
                dateOfBirthDateListener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        lifecycleScope.launch {
            adapter.addAll(viewModel.getPubs())
            adapter.notifyDataSetChanged()
        }

        hour.setOnClickListener {
            it.requestFocusFromTouch()
            TimePickerDialog(
                requireContext(), timeListener, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true
            ).show()
        }
    }


}
