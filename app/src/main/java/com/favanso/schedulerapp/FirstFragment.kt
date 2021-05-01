package com.favanso.schedulerapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.favanso.schedulerapp.MainActivity.Companion.listItems
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)

        calendarView.setOnDateChangeListener { calendarView, i, i2, i3 ->
            val date = "${i2 + 1}/${i3.toString()}/$i"
            dateView.text = date
        }

        floatingActionButton.setOnClickListener {
            listItems.add(dateView.text.toString() + " "
                    + eventTitle.text.toString() + " " + eventDetails.text.toString())

            Navigation.findNavController(it).navigate(R.id.action_FirstFragment_to_SecondFragment)
            Snackbar.make(view, "Event added to list", Snackbar.LENGTH_LONG)
                    .setAction("Undo", undoOnClickListener).show()

        }
    }

    var undoOnClickListener: View.OnClickListener = View.OnClickListener { view ->

        listItems.removeAt(listItems.size - 1)
        viewModel.addList()
        Snackbar.make(view, "Item removed,  refresh the screen to see it", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }


}//END FIRST FRAGMENT