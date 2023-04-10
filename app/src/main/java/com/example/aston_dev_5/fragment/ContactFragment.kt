package com.example.aston_dev_5.fragment

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

    private var adapter: ContactRecyclerViewAdapter? = null
    private lateinit var binding: FragmentContactListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentContactListBinding.inflate(inflater, container, false)

        initialRecycleView()
        setListenersForResultApi()

        binding.searchEditText.doAfterTextChanged { filterData(it.toString().trim()) }

        return binding.root
    }

    /** Фильтрация данных по строке */
    private fun filterData(strFilter: String) {
        val filteredList = mutableListOf<ContactItem>()
        ITEMS.forEach { item ->
            if (item.name.plus(" " + item.surname).contains(strFilter, ignoreCase = true)) {
                filteredList.add(item)
            }
        }
        adapter?.submitList(filteredList)
    }

    /** Установка адаптера для RecyclerView*/
    private fun initialRecycleView() = with(binding) {
        list.layoutManager = LinearLayoutManager(requireContext())
        adapter = ContactRecyclerViewAdapter(this@ContactFragment)
        list.adapter = adapter
        val dividerItemDecoration = ContactItemDecorator(1,10)
        list.addItemDecoration(dividerItemDecoration)
        adapter?.submitList(ITEMS.toList())
    }

    /** Установка слушателя данных по ResultApi от других фрагментов*/
    private fun setListenersForResultApi() {
        setFragmentResultListener(ConstantsProject.REQUEST_KEY) {  _, result ->
            val contactItem: ContactItem? = getParcelableData(result)
            val newItemsList = adapter?.currentList?.toMutableList()
            val itemToChange = newItemsList?.find { it.id == contactItem?.id }
            val newItem = ContactItem(
                contactItem!!.id,
                contactItem.name,
                contactItem.surname,
                contactItem.phoneNumber,
                contactItem.avatarUrl
            )
            newItemsList?.set(newItemsList.indexOf(itemToChange), newItem)

            val handler = Handler(Looper.getMainLooper())

            handler.postDelayed({
                adapter?.submitList(newItemsList)
            }, 500L)

            if (!HelpersUtil.isScreenForTwoFragments(resources)) {
                binding.searchEditText.setText("")
            }

            val currentListCopy = ITEMS.toMutableList()
            val currentItem = currentListCopy.find { it.id == contactItem.id }
            if (currentItem?.id != null) {
                currentListCopy[currentItem.id] = newItem
                updateDataList(currentListCopy)
            }

        }
    }

    /**
     * Отложенное обновление данных
     * Так как используется список, а не бд, нужна небольшая задержка
     * Чтобы DiffUtil корректно работал
     * */
    private fun updateDataList(newItems: List<ContactItem>) {
        val delayInMillis = 2000L // 3 секунды
        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            ITEMS = newItems
        }, delayInMillis)


    }

    /** Преобразование из Parcelable в ContactItem */
    private fun getParcelableData(result: Bundle) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Если sdk >= 33
            result.getParcelable(ARG_PARAM_CONTACT_ITEM, ContactItem::class.java)
        } else {
            // Если меньше
            result.getParcelable<ContactItem>(ARG_PARAM_CONTACT_ITEM)
        }

    /**
     * Переопределенные методы для обработки кликов по элементу
     */
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

        val buff = adapter?.currentList?.toMutableList()
        buff?.remove(item)
        adapter?.submitList(buff)
    }

}