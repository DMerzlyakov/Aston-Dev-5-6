package com.example.aston_dev_5.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_dev_5.databinding.FragmentContactBinding
import com.example.aston_dev_5.placeholder.ContactContent.ContactItem
import com.example.aston_dev_5.utils.setImageFromUrl

/**
 * MyContactAdapter для RecyclerView. Обрабатывает изменения списка и обрабатывает нажатия
 */
internal class MyContactRecyclerViewAdapter(
    private val mValues: List<ContactItem>,
    val onClickRecyclerViewInterface: OnClickRecyclerViewInterface,
) : RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder>() {

    private val filteredList = mValues.toMutableList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    fun setFilter(filter: String) {
        filteredList.clear()
        mValues.forEach { item ->
            if (item.name.plus(" " + item.surname).contains(filter, ignoreCase = true)) {
                filteredList.add(item)
            }
        }
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            mItem = filteredList[position]
            mNumberView.text = filteredList[position].phoneNumber
            mNameView.text = filteredList[position].name
            mSurnameView.text = filteredList[position].surname
            mIdView.text = filteredList[position].id.toString()
            mAvatarView.setImageFromUrl(filteredList[position].avatarUrl)
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    inner class ViewHolder(binding: FragmentContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val mNumberView: TextView
        val mNameView: TextView
        val mSurnameView: TextView
        val mAvatarView: ImageView
        val mIdView: TextView
        var mItem: ContactItem? = null

        init {
            mNumberView = binding.itemNumber
            mNameView = binding.itemName
            mSurnameView = binding.itemSurname
            mAvatarView = binding.avatarView
            mIdView = binding.idView
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
            return (super.toString() + " '"
                    + mNameView.text + " "
                    + mSurnameView.text + " "
                    + mSurnameView.text + " '")
        }
    }
}