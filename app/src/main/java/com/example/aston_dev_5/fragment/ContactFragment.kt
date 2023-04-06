package com.example.aston_dev_5.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aston_dev_5.ConstantsProject;
import com.example.aston_dev_5.HelpersUtil;
import com.example.aston_dev_5.R;
import com.example.aston_dev_5.placeholder.ContactContent;

/**
 * ContactFragment - Отображение RecyclerView контактов
 */
public class ContactFragment extends Fragment implements OnClickRecyclerViewInterface {


    private MyContactRecyclerViewAdapter adapter;

    /**
     * Пустой конструктор ContactFragment
     */
    public ContactFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        // Установка адаптера для RecyclerView
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

            adapter = new MyContactRecyclerViewAdapter(ContactContent.ITEMS, this);
            recyclerView.setAdapter(adapter);
        }

        setFragmentResultListeners();

        return view;
    }

    private void setFragmentResultListeners() {
        getParentFragmentManager().setFragmentResultListener(ConstantsProject.REQUEST_KEY, this,
                (requestKey, result) -> {

                    String mName = result.getString(ConstantsProject.ARG_PARAM_NAME);
                    String mSurname = result.getString(ConstantsProject.ARG_PARAM_SURNAME);
                    String mPhoneNumber = result.getString(ConstantsProject.ARG_PARAM_PHONE_NUMBER);

                    ContactContent.ContactItem data = new ContactContent.ContactItem(
                            result.getInt("id"), result.getString("name"),
                            result.getString("surname"), result.getString("number")
                    );

                    ContactContent.ITEMS.get(data.id).editItem(mName, mSurname, mPhoneNumber);

                    adapter.notifyItemChanged(data.id);
                });
    }


    @Override
    public void onItemClick(ContactContent.ContactItem item) {
        startDescriptionFragment(item);
    }

    private void startDescriptionFragment(ContactContent.ContactItem item) {
        if (HelpersUtil.isScreenForTwoFragments(getResources())) {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer2,
                            DescriptionContactFragment.newInstance(item.id, item.name, item.surname, item.phoneNumber))
                    .commit();
        } else {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer,
                            DescriptionContactFragment.newInstance(item.id, item.name, item.surname, item.phoneNumber))
                    .addToBackStack(null)
                    .commit();
        }
    }
}