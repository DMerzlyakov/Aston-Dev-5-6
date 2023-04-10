package com.example.aston_dev_5.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.aston_dev_5.databinding.FragmentContactBinding
import com.example.aston_dev_5.placeholder.ContactContent.ContactItem

/**
 * MyContactAdapter для RecyclerView. Связывает данные с RecyclerView
 */
internal class ContactRecyclerViewAdapter(
    private val onClickRecyclerViewInterface: OnClickRecyclerViewInterface,
) : ListAdapter<ContactItem, ContactViewHolder>(ContactDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            FragmentContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickRecyclerViewInterface
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.onBind(currentList[position])

        Log.e("BindViewHolder", " Перезаписан на позиции: $position")
    }
}