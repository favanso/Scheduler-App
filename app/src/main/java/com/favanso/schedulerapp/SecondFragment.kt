package com.favanso.schedulerapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.favanso.schedulerapp.MainActivity.Companion.listItems
import kotlinx.android.synthetic.main.fragment_second.*
import java.util.ArrayList

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    var adapter: ArrayAdapter<String>? = null

    companion object {
        fun newInstance() = SecondFragment()
    }

    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)

        viewModel.addList()
        val listObserver = Observer<ArrayList<String>> { list ->
            adapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_list_item_1, list)
            listView.adapter = adapter
            adapter?.notifyDataSetChanged()
        }

        viewModel.getValue().observe(viewLifecycleOwner, listObserver)

        listView.setOnItemLongClickListener() { parent, view, position, id ->
            val selectedItem: String = parent.getItemAtPosition(position).toString()
            adapter?.remove(selectedItem)
            adapter?.notifyDataSetChanged()
            listItems.removeAt(position)
            Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show()
            true
        }

    }
}
