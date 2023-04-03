package com.example.aston_dev_5.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aston_dev_5.R;
import com.example.aston_dev_5.placeholder.ContactContent;

/**
 * ContactFragment - Отображение RecyclerView контактов
 */
public class ContactFragment extends Fragment implements OnClickRecyclerViewInterface {

    /**
     * Пустой конструктор ContactFragment
     */
    public ContactFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new MyContactRecyclerViewAdapter(ContactContent.ITEMS, this));
        }
        return view;
    }


    @Override
    public void onItemClick(ContactContent.ContactItem item) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
                || isTablet()) {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer2,
                             DescriptionContactFragment.newInstance(item.name, item.surname, item.phoneNumber))
                    .commit();
        } else {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer,
                            DescriptionContactFragment.newInstance(item.name, item.surname, item.phoneNumber))
                    .addToBackStack(null)
                    .commit();
        }
    }


    public boolean isTablet() {
        return (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}