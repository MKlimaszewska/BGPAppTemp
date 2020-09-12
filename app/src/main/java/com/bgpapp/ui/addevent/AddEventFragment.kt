package com.bgpapp.ui.addevent


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import com.bgpapp.R
import com.bgpapp.common.NoFilterArrayAdapter
import com.bgpapp.databinding.FragmentAddEventBinding
import kotlinx.android.synthetic.main.fragment_add_event.*
import java.util.*

class AddEventFragment : Fragment() {

    private val viewModel = AddEventViewModel()
    private val dateOfBirthDateListener =
        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            viewModel.updateDateOfBirth(year, month, dayOfMonth)
        }
    private val timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        viewModel.updateHour(hourOfDay, minute)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentAddEventBinding>(
            inflater,
            R.layout.fragment_add_event,
            container,
            false
        ).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
            it.meetingSpinnerAdapter = NoFilterArrayAdapter<String>(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                listOf("Turniej", "Towarzyskie")
            )
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

        hour.setOnClickListener {
            it.requestFocusFromTouch()
            TimePickerDialog(
                requireContext(), timeListener, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true
            ).show()
        }
    }


}
