package com.example.yodono2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class Informacion extends Fragment {

    ExpandableListView expandableTextView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_informacion, container, false);

        expandableTextView=view.findViewById(R.id.eTV);
        InfoAdapter adapter = new InfoAdapter(getContext());
        expandableTextView.setAdapter(adapter);

        return view;
    }
}