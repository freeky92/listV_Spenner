package com.bignerdranch.android.listv_spenner.ui.main

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bignerdranch.android.listv_spenner.R
import com.bignerdranch.android.listv_spenner.databinding.DialogAddItemBinding
import com.bignerdranch.android.listv_spenner.databinding.MainFragmentBinding
import com.bignerdranch.android.listv_spenner.model.Item
import com.bignerdranch.android.listv_spenner.model.adapter.MyListAdapter
import com.bignerdranch.android.listv_spenner.model.adapter.MySpinnerAdapter


class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var spinnerAdapter: MySpinnerAdapter
    private lateinit var listAdapter: MyListAdapter

    private val viewModel by viewModels<MainVM>()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = MyListAdapter(data1) {
            delete(it)
        }
        binding.listView.adapter = listAdapter
        binding.listView.setOnItemClickListener { _, _, position, _ ->
            openItemInDialog(listAdapter.getItem(position))
        }

        spinnerAdapter = MySpinnerAdapter(data2)
        binding.spinner.adapter = spinnerAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.addFb.setOnClickListener {
            addItem()
        }
    }

    private fun delete(item: Item) {
        val listener = DialogInterface.OnClickListener { _, which ->
            if (which == DialogInterface.BUTTON_POSITIVE) {
                data1.remove(item)
                listAdapter.notifyDataSetChanged()
            }
        }
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete_item))
            .setMessage(getString(R.string.sure_delete))
            .setPositiveButton("Delete", listener)
            .setNegativeButton("Cancel", listener)
            .create()
        dialog.show()
    }

    private fun addItem() {
        val dialogAddItemBinding = DialogAddItemBinding.inflate(layoutInflater)
        val addAlertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Add item")
            .setView(dialogAddItemBinding.root)
            .setPositiveButton("Add") { d, which ->
                val title = dialogAddItemBinding.titleEt.text.toString()
                val note = dialogAddItemBinding.noteEt.text.toString()
                if (title.isNotBlank()) {
                    create(title, note)
                }
                listAdapter.notifyDataSetChanged()
            }
            .create()
        addAlertDialog.show()
    }

    private fun create(title: String, note: String) {
        val item = Item(
            id = (0..100000).random().toLong(),
            title = title,
            note = note
        )
        data1.add(item)
    }

    private fun openItemInDialog(item: Item) {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(item.title)
            .setMessage(item.note)
            .setPositiveButton(R.string.ok_bt) { _, _ -> }
            .create()
        alertDialog.show()
    }

    companion object {
        fun newInstance() = MainFragment()

        val data1 = mutableListOf(
            Item((1..10000).random().toLong(), "Baracuda", "Silent"),
            Item((1..10000).random().toLong(), "Nemesida", "Boxcar"),
            Item((1..10000).random().toLong(), "Spikelet", "Home"),
            Item((1..10000).random().toLong(), "Rock", "Medal"),
            Item((1..10000).random().toLong(), "Unicorn", "Sharp"),
        )

        val data2 = mutableListOf(
            Item((1..100).random().toLong(), "Spain", "code1"),
            Item((1..100).random().toLong(), "Italy", "code44"),
            Item((1..100).random().toLong(), "Portugal", "code33"),
            Item((1..100).random().toLong(), "Greece", "anti-code1"),
            Item((1..100).random().toLong(), "Switzerland", "anti-task"),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}