package com.example.kevin.myapplicationtestappbar;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                pushFragment(new MyFragment());
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                if(fm.getFragments().size() > 0)
                    removeFragment(fm.getFragments().get(fm.getFragments().size() -1));
            }
        });

    }

    public AppBarLayout getAppBarLayout(){
        return findViewById(R.id.app_bar);
    }

    public void pushFragment(Fragment fragment){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.mainContainer, fragment).commit();
    }

    public void removeFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().remove(fragment).commit();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        System.out.println("On back pressed");
        FragmentManager fm = getSupportFragmentManager();
        if(fm.getFragments().size() > 0)
            removeFragment(fm.getFragments().get(fm.getFragments().size() -1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


/*
New design
package com.example.kevin.appbartest;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                pushFragment(new FragmentWithCustomToolbar());
            }
        });

        final ViewPager pager = findViewById(R.id.pager);

        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                FragmentWithCustomToolbar fragment = (FragmentWithCustomToolbar) pagerAdapter.getItem(i);
                fragment.setText("Fragment position: "+i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        setupToolbar();
    }

    public void setupToolbar(){
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        final CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        final Toolbar toolbar = findViewById(R.id.toolbar);

        toolbarLayout.setTitle("");

        final View view = getLayoutInflater().inflate(R.layout.custom_header, null);

        toolbarLayout.addView(view);

        view.measure(0,0);
        toolbar.getLayoutParams().height = view.getMeasuredHeight();

        CollapsingToolbarLayout.LayoutParams viewLayoutParams = (CollapsingToolbarLayout.LayoutParams) view.getLayoutParams();

        System.out.println("View height: "+view.getMeasuredHeight()+", start :"+viewLayoutParams.topMargin);

        final ImageView imageView = findViewById(R.id.moduleImage);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            int initHeight = -1;
            int initWidth = -1;
            int initToolbarHeight = -1;
            Bitmap initBitmap;

            int minimumImageSize = 50;
            int scrolRange = -1;
            int marginImage = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                System.out.println("Vertical offser=t: "+verticalOffset+", scroll Range: "+appBarLayout.getTotalScrollRange());

//                if(scrolRange == -1) scrolRange = appBarLayout.getTotalScrollRange();

                scrolRange = appBarLayout.getTotalScrollRange();

                CollapsingToolbarLayout.LayoutParams viewLayoutParams = (CollapsingToolbarLayout.LayoutParams) view.getLayoutParams();

                System.out.println("View height: "+view.getMeasuredHeight()+", start :"+viewLayoutParams.topMargin);
                if(scrolRange > 0){
                    if(initBitmap == null && imageView.getDrawable() != null){
                        initBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                        initHeight = imageView.getHeight();
                        initWidth = imageView.getWidth();
                    }

                    if(initToolbarHeight == -1){
                        initToolbarHeight = toolbar.getHeight();
                    }

                    int resize = (scrolRange + verticalOffset) * 100 / scrolRange;

                    if(resize < minimumImageSize) resize = minimumImageSize;

                    int newWidth = (initWidth * resize)/100;
                    int newHeight = (initHeight * resize)/100;

                    System.out.println("Init width: "+initWidth+", new width: "+newWidth+"; initHeight: "+initHeight+" new height: "+newHeight);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                    layoutParams.width = newWidth;
                    layoutParams.height = newHeight;

//                    int marginImage = (int) getResources().getDimension(R.dimen.image_margin);
                    if(marginImage == -1) marginImage = (int) getResources().getDimension(R.dimen.image_margin);


                    CollapsingToolbarLayout.LayoutParams toolbarLayoutParams = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
                    toolbarLayoutParams.height = initToolbarHeight - (initHeight - newHeight) + marginImage*2;


                    System.out.println("Init margin: "+marginImage);
//                    layoutParams.topMargin = marginImage + Math.abs(verticalOffset);// + (initToolbarHeight - toolbarLayoutParams.height) + (initHeight - newHeight);
//                    layoutParams.topMargin = marginImage;
//                    if(resize<=minimumImageSize)
//                        layoutParams.topMargin = Math.abs(verticalOffset) - marginImage*2;
//                    else
//                        layoutParams.topMargin = Math.abs(verticalOffset) - marginImage*2 + (initToolbarHeight - toolbarLayoutParams.height);
                    int percent = (resize - minimumImageSize) * 100 / resize;
                    if(percent < 0) percent = 0;
                    System.out.println("PErcent: "+percent);
//                    layoutParams.topMargin = Math.abs(verticalOffset) - marginImage*2 + ((initToolbarHeight - toolbarLayoutParams.height)*percent)/100;
                    layoutParams.topMargin = Math.abs(verticalOffset) - marginImage*2 + ((initToolbarHeight - toolbarLayoutParams.height)*percent)/100;

                    imageView.requestLayout();
                }
            }
        });

    }

//    public void pushFragment(Fragment fragment){
//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction().replace(R.id.mainContainer, fragment).commit();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

 */