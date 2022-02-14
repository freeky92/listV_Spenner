package com.bignerdranch.android.listv_spenner.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bignerdranch.android.listv_spenner.databinding.MyAdapterItemBinding
import com.bignerdranch.android.listv_spenner.databinding.MySpinnerAdapterItemBinding
import com.bignerdranch.android.listv_spenner.model.Item

class MySpinnerAdapter(
    private val list: List<Item>,
) : BaseAdapter() {

    override fun getItem(position: Int): Item {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding =
            convertView?.tag as MySpinnerAdapterItemBinding? ?: createBinding(parent.context)

        val item = getItem(position)

        binding.titleTv.text = item.title
        binding.noteTv.text = item.note

        return binding.root
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    private fun createBinding(context: Context): MySpinnerAdapterItemBinding {
        val binding = MySpinnerAdapterItemBinding.inflate(LayoutInflater.from(context))
        binding.root.tag = binding
        return binding
    }

}