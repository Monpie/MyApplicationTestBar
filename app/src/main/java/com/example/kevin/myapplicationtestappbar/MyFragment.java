package com.example.kevin.myapplicationtestappbar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_scrolling, container, false);

        ((ScrollingActivity) getActivity()).getSupportActionBar();

        Button button = view.findViewById(R.id.pushButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ScrollingActivity) getActivity()).pushFragment(new MyFragmentWithNoBar());
            }
        });

        ImageView imageView = getActivity().findViewById(R.id.ivCover);
        imageView.setImageResource(R.drawable.winter);
        return view;
    }
}
