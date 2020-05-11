package com.example.meetfinance.presentation.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.meetfinance.presentation.model.CountryItem;
import com.example.meetfinance.R;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<CountryItem> {

    CountryAdapter(Context context, ArrayList<CountryItem> countryList) {
        super(context, 0, countryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.country_spinner_row, parent, false
            );
        }

        ImageView imageViewFlag = convertView.findViewById(R.id.iv_france);
        CountryItem currentItem = getItem(position);


        if (currentItem != null) {
            imageViewFlag.setImageResource(currentItem.getFlagImage());
        }

        return convertView;

    }
}
