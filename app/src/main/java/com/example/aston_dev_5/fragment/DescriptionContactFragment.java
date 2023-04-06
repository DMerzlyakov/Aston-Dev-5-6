package com.example.aston_dev_5.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.aston_dev_5.utils.ConstantsProject;
import com.example.aston_dev_5.R;
import com.example.aston_dev_5.databinding.FragmentDescriptionContactBinding;
import com.example.aston_dev_5.placeholder.ContactContent;
import com.example.aston_dev_5.utils.HelpersUtil;

import static com.example.aston_dev_5.utils.ConstantsProject.ARG_PARAM_CONTACT_ITEM;
import static com.example.aston_dev_5.utils.HelpersUtilKt.setImageFromUrl;


/**
 * DescriptionContactFragment - Фрагмент для отображения подробной информации о Контакте
 */
public class DescriptionContactFragment extends Fragment implements View.OnClickListener {

    private ContactContent.ContactItem contactItem;
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
            contactItem = getArguments().getParcelable(ARG_PARAM_CONTACT_ITEM);
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
                contactItem.name = binding.editNameView.getText().toString();
                contactItem.surname = binding.editSurnameView.getText().toString();
                contactItem.phoneNumber = binding.editPhoneView.getText().toString();

                Bundle args = new Bundle();
                args.putParcelable(ARG_PARAM_CONTACT_ITEM, contactItem);
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
        binding.nameView.setText(contactItem.id + ". " + contactItem.name);
        binding.surnameView.setText(contactItem.surname);
        binding.phoneView.setText(contactItem.phoneNumber);
        setImageFromUrl(binding.imageAvatar, contactItem.avatarUrl);
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
            binding.editNameView.setText(contactItem.name);
            binding.editSurnameView.setText(contactItem.surname);
            binding.editPhoneView.setText(contactItem.phoneNumber);
        }

        binding.nameView.setVisibility(textVisibility);
        binding.surnameView.setVisibility(textVisibility);
        binding.phoneView.setVisibility(textVisibility);
    }

    /**
     * Создание экземпляра Фрагмента с первоначальными данными
     */
    public static DescriptionContactFragment newInstance(ContactContent.ContactItem item) {
        DescriptionContactFragment fragment = new DescriptionContactFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM_CONTACT_ITEM, item);
        fragment.setArguments(args);

        return fragment;
    }
}