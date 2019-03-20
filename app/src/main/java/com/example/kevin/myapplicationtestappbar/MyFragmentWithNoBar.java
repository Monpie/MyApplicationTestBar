package com.example.kevin.myapplicationtestappbar;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragmentWithNoBar extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_scrolling, container, false);

        v.findViewById(R.id.pushButton).setVisibility(View.GONE);

        AppBarLayout appBarLayout = ((ScrollingActivity) getActivity()).getAppBarLayout();
//        appBarLayout.setVisibility(View.GONE);

        CoordinatorLayout mainLayout = getActivity().findViewById(R.id.mainLayout);

        CollapsingToolbarLayout toolbarLayout = getActivity().findViewById(R.id.toolbar_layout);
//        toolbarLayout.setContentScrim(null);

        appBarLayout.setExpanded(false);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        ((CustomAppBarLayoutBehavior)layoutParams.getBehavior()).setScrollBehavior(false);
        ((ImageView) getActivity().findViewById(R.id.ivProfile)).setImageBitmap(null);

//        appBarLayout.setExpanded(false, false);
//        appBarLayout.setActivated(false);
//        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)appBarLayout.getLayoutParams();
//        lp.height = appBarLayout.getHeight();

//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mainLayout.getLayoutParams();
//        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
//        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
//        if(behavior!=null) {
//            System.out.println("Behavior not null");
////            behavior.onNestedFling(mainLayout, appBarLayout, null, 0, 10000, true);
//            behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
//                @Override
//                public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
//                    System.out.println("Can drag");
//                    return false;
//                }
//            });
////            params.setBehavior(behavior);
//        }
//        System.out.println("Toolbar height: "+toolbar.getHeight());



        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        AppBarLayout appBarLayout = ((ScrollingActivity) getActivity()).getAppBarLayout();
        appBarLayout.setExpanded(true);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();

        ((CustomAppBarLayoutBehavior)layoutParams.getBehavior()).setScrollBehavior(true);
    }
}
