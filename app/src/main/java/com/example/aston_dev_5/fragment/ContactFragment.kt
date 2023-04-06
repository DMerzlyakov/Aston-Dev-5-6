package com.example.aston_dev_5.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_dev_5.ConstantsProject
import com.example.aston_dev_5.HelpersUtil
import com.example.aston_dev_5.R
import com.example.aston_dev_5.placeholder.ContactContent
import com.example.aston_dev_5.placeholder.ContactContent.ContactItem

/**
 * ContactFragment - Отображение RecyclerView контактов
 */
class ContactFragment : Fragment(), OnClickRecyclerViewInterface {

    private var adapter: MyContactRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)

        // Установка адаптера для RecyclerView
        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(requireContext())
            adapter = MyContactRecyclerViewAdapter(ContactContent.ITEMS, this)
            view.adapter = adapter
        }
        setListenersForResultApi()
        return view
    }

    private fun setListenersForResultApi() {

        setFragmentResultListener(ConstantsProject.REQUEST_KEY) { _, result ->

            val mId = result.getInt(ConstantsProject.ARG_PARAM_ID)
            val mName = result.getString(ConstantsProject.ARG_PARAM_NAME)
            val mSurname = result.getString(ConstantsProject.ARG_PARAM_SURNAME)
            val mPhoneNumber = result.getString(ConstantsProject.ARG_PARAM_PHONE_NUMBER)
            ContactContent.ITEMS[mId].editItem(mName, mSurname, mPhoneNumber)
            adapter?.notifyItemChanged(mId)
        }
    }

    override fun onItemClick(item: ContactItem) {
        startDescriptionFragment(item)
    }

    private fun startDescriptionFragment(item: ContactItem) {
        if (HelpersUtil.isScreenForTwoFragments(resources)) {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer2,
                    DescriptionContactFragment.newInstance(
                        item.id,
                        item.name,
                        item.surname,
                        item.phoneNumber
                    )
                )
                .commit()
        } else {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    DescriptionContactFragment.newInstance(
                        item.id,
                        item.name,
                        item.surname,
                        item.phoneNumber
                    )
                )
                .addToBackStack(null)
                .commit()
        }
    }
}