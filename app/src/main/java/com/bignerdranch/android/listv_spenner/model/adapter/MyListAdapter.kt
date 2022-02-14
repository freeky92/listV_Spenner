package com.bignerdranch.android.listv_spenner.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bignerdranch.android.listv_spenner.databinding.MyAdapterItemBinding
import com.bignerdranch.android.listv_spenner.model.Item

typealias OnEventListener = (Item) -> Unit

class MyListAdapter(
    private val list: List<Item>,
    private val onEventListener: OnEventListener
) : BaseAdapter(), View.OnClickListener {

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
            convertView?.tag as MyAdapterItemBinding? ?: createBinding(parent.context)

        val item = getItem(position)

        binding.titleTv.text = item.title
        binding.deleteTb.tag = item
        binding.noteTv.text = item.note

        return binding.root
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun onClick(v: View?) {
        val item = v?.tag as Item
        onEventListener.invoke(item)
    }

    private fun createBinding(context: Context): MyAdapterItemBinding {
        val binding = MyAdapterItemBinding.inflate(LayoutInflater.from(context))
        binding.deleteTb.setOnClickListener(this)
        binding.root.tag = binding
        return binding
    }

}