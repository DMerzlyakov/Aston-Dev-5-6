package com.example.aston_dev_5.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.aston_dev_5.ConstantsProject;
import com.example.aston_dev_5.HelpersUtil;
import com.example.aston_dev_5.R;
import com.example.aston_dev_5.databinding.FragmentDescriptionContactBinding;

/**
 * DescriptionContactFragment - Фрагмент для отображения подробной информации о Контакте
 */
public class DescriptionContactFragment extends Fragment implements View.OnClickListener {

    private int mId;
    private String mName;
    private String mSurname;
    private String mPhoneNumber;

    private FragmentDescriptionContactBinding binding;

    /**
     * Пустой конструктор DescriptionContactFragment
     */
    public DescriptionContactFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mId = getArguments().getInt(ConstantsProject.ARG_PARAM_ID);
            mName = getArguments().getString(ConstantsProject.ARG_PARAM_NAME);
            mSurname = getArguments().getString(ConstantsProject.ARG_PARAM_SURNAME);
            mPhoneNumber = getArguments().getString(ConstantsProject.ARG_PARAM_PHONE_NUMBER);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDescriptionContactBinding.inflate(inflater, container, false);

        setTextToTextView();

        binding.btnEdit.setOnClickListener(this);
        binding.btnSave.setOnClickListener(this);
        binding.btnCancel.setOnClickListener(this);

        return binding.getRoot();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_edit:
            case R.id.btn_cancel:
                changeVisibilityButtons();
                changeVisibilityTextViews();
                break;

            case R.id.btn_save:
                mName = binding.editNameView.getText().toString();
                mSurname = binding.editSurnameView.getText().toString();
                mPhoneNumber = binding.editPhoneView.getText().toString();

                Bundle args = new Bundle();
                args.putInt(ConstantsProject.ARG_PARAM_ID, mId);
                args.putString(ConstantsProject.ARG_PARAM_NAME, mName);
                args.putString(ConstantsProject.ARG_PARAM_SURNAME, mSurname);
                args.putString(ConstantsProject.ARG_PARAM_PHONE_NUMBER, mPhoneNumber);
                getParentFragmentManager().setFragmentResult(ConstantsProject.REQUEST_KEY, args);

                if (HelpersUtil.isScreenForTwoFragments(getResources())) {
                    changeVisibilityButtons();
                    changeVisibilityTextViews();
                    setTextToTextView();
                } else {
                    getParentFragmentManager().popBackStack();
                }

                // Закрываем клавиатуру пользователя при сохранении
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                break;
        }
    }

    /**
     * Отображение текстовых данных на фрагменте
     */
    private void setTextToTextView() {
        binding.nameView.setText(mName);
        binding.surnameView.setText(mSurname);
        binding.phoneView.setText(mPhoneNumber);
    }

    /**
     * Изменение видимости кнопок
     * Переходы реализованы в виде:
     * Visible -> Gone
     * Gone -> Visible
     */
    private void changeVisibilityButtons() {
        if (binding.btnEdit.getVisibility() == View.VISIBLE) {
            binding.btnEdit.setVisibility(View.GONE);
            binding.btnSave.setVisibility(View.VISIBLE);
            binding.btnCancel.setVisibility(View.VISIBLE);
        } else {
            binding.btnEdit.setVisibility(View.VISIBLE);
            binding.btnSave.setVisibility(View.GONE);
            binding.btnCancel.setVisibility(View.GONE);
        }
    }

    /**
     * Изменение видимости TextView и EditText
     * Переходы реализованы в виде:
     * Visible -> Gone
     * Gone -> Visible
     */
    private void changeVisibilityTextViews() {
        int editVisibility, textVisibility;

        if (binding.nameView.getVisibility() == View.VISIBLE) {
            editVisibility = View.VISIBLE;
            textVisibility = View.GONE;
        } else {
            editVisibility = View.GONE;
            textVisibility = View.VISIBLE;
        }

        binding.editNameView.setVisibility(editVisibility);
        binding.editSurnameView.setVisibility(editVisibility);
        binding.editPhoneView.setVisibility(editVisibility);

        if (editVisibility == View.VISIBLE) {
            binding.editNameView.setText(mName);
            binding.editSurnameView.setText(mSurname);
            binding.editPhoneView.setText(mPhoneNumber);
        }

        binding.nameView.setVisibility(textVisibility);
        binding.surnameView.setVisibility(textVisibility);
        binding.phoneView.setVisibility(textVisibility);
    }

    /** Создание экземпляра Фрагмента с первоначальными данными */
    public static DescriptionContactFragment newInstance(int id, String name, String surname, String number) {

        DescriptionContactFragment fragment = new DescriptionContactFragment();
        Bundle args = new Bundle();
        args.putInt(ConstantsProject.ARG_PARAM_ID, id);
        args.putString(ConstantsProject.ARG_PARAM_NAME, name);
        args.putString(ConstantsProject.ARG_PARAM_SURNAME, surname);
        args.putString(ConstantsProject.ARG_PARAM_PHONE_NUMBER, number);
        fragment.setArguments(args);

        return fragment;
    }
}