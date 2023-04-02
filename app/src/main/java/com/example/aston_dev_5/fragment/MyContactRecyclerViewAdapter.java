package com.example.aston_dev_5.fragment;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aston_dev_5.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.aston_dev_5.databinding.FragmentContactBinding;

import java.util.List;

/**
 *
 */
public class MyContactRecyclerViewAdapter extends RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;
    private final OnClickRecyclerViewInterface onClickRecyclerViewInterface;

    public MyContactRecyclerViewAdapter(List<PlaceholderItem> items, OnClickRecyclerViewInterface onClickRecyclerViewInterface) {
        mValues = items;
        this.onClickRecyclerViewInterface = onClickRecyclerViewInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentContactBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNumberView.setText(mValues.get(position).phoneNumber);
        holder.mNameView.setText(mValues.get(position).name);
        holder.mSurnameView.setText(mValues.get(position).surname);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mNumberView;
        public final TextView mNameView;
        public final TextView mSurnameView;
        public PlaceholderItem mItem;

        public ViewHolder(FragmentContactBinding binding) {
            super(binding.getRoot());
            mNumberView = binding.itemNumber;
            mNameView = binding.itemName;
            mSurnameView = binding.itemSurname;

            binding.getRoot().setOnClickListener(view -> {
                if (onClickRecyclerViewInterface != null) {
                    int position = getBindingAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        onClickRecyclerViewInterface.onItemClick(mItem);
                    }
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}