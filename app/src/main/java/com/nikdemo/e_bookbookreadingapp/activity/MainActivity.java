package com.nikdemo.e_bookbookreadingapp.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.fragment.BookFragment;
import com.nikdemo.e_bookbookreadingapp.fragment.HomeFragment;
import com.nikdemo.e_bookbookreadingapp.fragment.LibraryFragment;
import com.nikdemo.e_bookbookreadingapp.fragment.PlanFragment;
import com.nikdemo.e_bookbookreadingapp.fragment.ProfileFragment;
import com.nikdemo.e_bookbookreadingapp.menu.DrawerAdapter;
import com.nikdemo.e_bookbookreadingapp.menu.DrawerItem;
import com.nikdemo.e_bookbookreadingapp.menu.SimpleItem;
import com.nikdemo.e_bookbookreadingapp.utils.BaseActivity;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.callback.DragStateListener;

import java.util.Arrays;

public class MainActivity extends BaseActivity implements DrawerAdapter.OnItemSelectedListener {

    private static final int MENU_POS_HOME = 0;
    private static final int MENU_POS_BOOK = 1;
    private static final int MENU_POS_LIBRARY = 2;
    private static final int MENU_POS_FAVORITE = 3;
    private static final int MENU_POS_PLAN = 4;
    private static final int MENU_POS_SETTING = 5;
    private static final int MENU_POS_LOGOUT = 6;
    private static final String BACK_STACK_ROOT_TAG = "main_fragment";


    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNavBuilder SlidingRootNavBuilder;
    public static SlidingRootNav slidingRootNav;
    private Toolbar toolbar;
    Fragment fr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.actionbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        SlidingRootNavBuilder = new SlidingRootNavBuilder(this);
        slidingRootNav = SlidingRootNavBuilder.withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withDragDistance(150)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .withContentClickableWhenMenuOpened(false)
                .inject();

        fr = new HomeFragment();
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fr);
        fragmentTransaction.commit();
        setMenu();

    }

    void openFragment() {
        getSupportFragmentManager().popBackStack(BACK_STACK_ROOT_TAG, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fr);
        fragmentTransaction.addToBackStack(BACK_STACK_ROOT_TAG);
        fragmentTransaction.commit();
    }

    private void setMenu() {
        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();
        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(MENU_POS_HOME),
                createItemFor(MENU_POS_BOOK),
                createItemFor(MENU_POS_LIBRARY),
                createItemFor(MENU_POS_FAVORITE),
                createItemFor(MENU_POS_PLAN),
                createItemFor(MENU_POS_SETTING),
                createItemFor(MENU_POS_LOGOUT) ));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.menu_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        ((TextView) findViewById(R.id.tv_name)).setText("Ravi Patel");

    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(android.R.color.white))
                .withTextTint(color(android.R.color.white))
                .withSelectedIconTint(color(android.R.color.black))
                .withSelectedTextTint(color(android.R.color.black));
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    @Override
    public void onItemSelected(int position) {
        switch (position) {
            case MENU_POS_HOME: {
                fr = new HomeFragment();
                FragmentTransaction fragmentTransaction =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, fr);
                fragmentTransaction.commit();
                break;
            }
            case MENU_POS_BOOK: {
                fr = new BookFragment();
                openFragment();
                break;
            }
            case MENU_POS_LIBRARY: {
                fr = new LibraryFragment();
                openFragment();
                break;
            }
            case MENU_POS_FAVORITE: {
                break;
            }
            case MENU_POS_PLAN: {
                fr = new PlanFragment();
                openFragment();
                break;
            }
            case MENU_POS_SETTING: {
                fr = new ProfileFragment();
                openFragment();
                break;
            }
            case MENU_POS_LOGOUT: {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
                break;
            }

        }
        slidingRootNav.closeMenu();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
