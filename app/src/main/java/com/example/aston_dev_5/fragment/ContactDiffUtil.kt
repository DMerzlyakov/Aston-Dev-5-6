package com.example.aston_dev_5.fragment

import androidx.recyclerview.widget.DiffUtil
import com.example.aston_dev_5.placeholder.ContactContent

/**
 * ContactDiffUtil для RecyclerView. Вычисляет какие из данных необходимо перезаписать
 */
class ContactDiffUtil : DiffUtil.ItemCallback<ContactContent.ContactItem>() {

    override fun areItemsTheSame(
        oldItem: ContactContent.ContactItem,
        newItem: ContactContent.ContactItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ContactContent.ContactItem,
        newItem: ContactContent.ContactItem
    ): Boolean {
        return oldItem == newItem
    }

}