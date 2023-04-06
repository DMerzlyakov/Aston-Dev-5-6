package com.example.aston_dev_5.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_dev_5.databinding.FragmentContactBinding
import com.example.aston_dev_5.placeholder.ContactContent.ContactItem

/**
 * MyContactAdapter для RecyclerView. Обрабатывает изменения списка и обрабатывает нажатия
 */
internal class MyContactRecyclerViewAdapter(
    private val mValues: List<ContactItem>,
    val onClickRecyclerViewInterface: OnClickRecyclerViewInterface,
) : RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            mItem = mValues[position]
            mNumberView.text = mValues[position].phoneNumber
            mNameView.text = mValues[position].name
            mSurnameView.text = mValues[position].surname
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(binding: FragmentContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val mNumberView: TextView
        val mNameView: TextView
        val mSurnameView: TextView
        var mItem: ContactItem? = null

        init {
            mNumberView = binding.itemNumber
            mNameView = binding.itemName
            mSurnameView = binding.itemSurname
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClickRecyclerViewInterface.onItemClick(mItem)
                }
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