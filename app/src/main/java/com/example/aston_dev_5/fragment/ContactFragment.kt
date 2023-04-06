package com.example.aston_dev_5.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aston_dev_5.R
import com.example.aston_dev_5.databinding.FragmentContactListBinding
import com.example.aston_dev_5.placeholder.ContactContent.ContactItem
import com.example.aston_dev_5.placeholder.ContactContent.ITEMS
import com.example.aston_dev_5.utils.ConstantsProject
import com.example.aston_dev_5.utils.ConstantsProject.ARG_PARAM_CONTACT_ITEM
import com.example.aston_dev_5.utils.HelpersUtil

/**
 * ContactFragment - Отображение RecyclerView контактов
 */
class ContactFragment : Fragment(), OnClickRecyclerViewInterface {

    private var adapter: MyContactRecyclerViewAdapter? = null
    private lateinit var binding: FragmentContactListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentContactListBinding.inflate(inflater, container, false)

        initialRecycleView()
        setListenersForResultApi()

        binding.searchEditText.doAfterTextChanged { adapter?.setFilter(it.toString().trim())}

        return binding.root
    }

    /** Установка адаптера для RecyclerView*/
    private fun initialRecycleView() = with(binding){
        list.layoutManager = LinearLayoutManager(requireContext())
        adapter = MyContactRecyclerViewAdapter(ITEMS, this@ContactFragment)
        list.adapter = adapter
    }



    private fun setListenersForResultApi() {
        setFragmentResultListener(ConstantsProject.REQUEST_KEY) { _, result ->
            val contactItem: ContactItem? = getParcelableData(result)
            val itemToChange = ITEMS.find { it.id == contactItem?.id }

            with(itemToChange) {
                this?.name = contactItem?.name
                this?.surname = contactItem?.surname
                this?.phoneNumber = contactItem?.phoneNumber
            }

            adapter?.notifyDataSetChanged()
        }
    }


    private fun getParcelableData(result: Bundle) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Если sdk >= 33
            result.getParcelable(ARG_PARAM_CONTACT_ITEM, ContactItem::class.java)
        } else {
            // Если меньше
            result.getParcelable<ContactItem>(ARG_PARAM_CONTACT_ITEM)
        }

    override fun onItemClick(item: ContactItem, position: Int) {
        val descriptionFragment = DescriptionContactFragment.newInstance(item)

        if (HelpersUtil.isScreenForTwoFragments(resources)) {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer2,
                    descriptionFragment
                )
                .commit()
        } else {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    descriptionFragment
                )
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onItemLongClick(item: ContactItem, position: Int) {
        ITEMS.remove(item)
        adapter?.let {
            it.notifyItemRemoved(position)
            it.notifyItemRangeChanged(position, it.itemCount)
        }
    }
}