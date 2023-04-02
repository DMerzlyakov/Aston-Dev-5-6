package com.example.aston_dev_5.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aston_dev_5.databinding.FragmentDescriptionContactBinding;

/**
 *
 */
public class DescriptionContactFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "surname";
    private static final String ARG_PARAM3 = "number";

    private String mName;
    private String mSurname;
    private String mPhoneNumber;

    private FragmentDescriptionContactBinding binding;

    public DescriptionContactFragment() {
        // Required empty public constructor
    }

    public static DescriptionContactFragment newInstance(String name, String surname, String number) {

        DescriptionContactFragment fragment = new DescriptionContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        args.putString(ARG_PARAM2, surname);
        args.putString(ARG_PARAM3, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Log.e("asd", "init");
//        if (savedInstanceState != null) {
//            mName = savedInstanceState.getString(ARG_PARAM1);
//            mSurname = savedInstanceState.getString(ARG_PARAM2);

//        }
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_PARAM1);
            mSurname = getArguments().getString(ARG_PARAM2);
            mPhoneNumber = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDescriptionContactBinding.inflate(inflater, container, false);

        binding.nameView.setText(mName);

        binding.surnameView.setText(mSurname);

        binding.phoneView.setText(mPhoneNumber);

        return binding.getRoot();
    }

//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString(ARG_PARAM1, mName);
//        outState.putString(ARG_PARAM2, mSurname);
//    }


}