package com.example.aston_dev_5.fragment

import androidx.recyclerview.widget.RecyclerView
import com.example.aston_dev_5.databinding.FragmentContactBinding
import com.example.aston_dev_5.placeholder.ContactContent.ContactItem
import com.example.aston_dev_5.utils.setImageFromUrl


/**
 * ContactViewHolder для RecyclerView. Формирует одну запись в RecyclerView
 */
internal class ContactViewHolder(
    private val binding: FragmentContactBinding,
    onClickRecyclerViewInterface: OnClickRecyclerViewInterface,
) :
    RecyclerView.ViewHolder(binding.root) {

    var mItem: ContactItem? = null

    fun onBind(item: ContactItem) {
        with(binding) {
            idView.text = item.id.toString()
            itemName.text = item.name
            itemSurname.text = item.surname
            itemNumber.text = item.phoneNumber
            avatarView.setImageFromUrl(item.avatarUrl)
        }

        mItem = item

    }

    init {
        binding.root.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onClickRecyclerViewInterface.onItemClick(mItem, position)
            }
        }
        binding.root.setOnLongClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onClickRecyclerViewInterface.onItemLongClick(mItem, position)
            }
            true
        }
    }

    override fun toString(): String {
        return (super.toString() + " ")
    }
}