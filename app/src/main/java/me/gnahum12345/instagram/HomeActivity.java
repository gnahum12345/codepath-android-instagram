package me.gnahum12345.instagram;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import me.gnahum12345.instagram.model.Post;

public class HomeActivity extends AppCompatActivity implements HomeFragment.HomeFragmentListener, CreateFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener {
    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(TAG, "Implementing on detail Click");
    }

    @Override
    public void onDetailClick() {
        Log.d(TAG, "Implementing on detail Click");
    }

    private static final String TAG = "HomeActivityTAG";

    private final List<Fragment> fragments = new ArrayList<>();

    private BottomNavigationView bottomNavigation;

    private ViewPager viewPager;

    /**
     * The adapter used to display information for our bottom navigation view.
     */
    private PageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        viewPager = findViewById(R.id.pager);

        fragments.add(new HomeFragment()); // 0
        fragments.add(new CreateFragment()); // 1
        fragments.add(new ProfileFragment()); // 2
//        fragments.add(new DetailFragment()); // TO BE DETERMINED

        adapter = new PageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                switch(i) {
                    case 0:
                        bottomNavigation.setSelectedItemId(R.id.action_home);
                        break;
                    case 1:
                        bottomNavigation.setSelectedItemId(R.id.action_create);
                        break;
                    case 2:
                        bottomNavigation.setSelectedItemId(R.id.action_profile);
                        break;
                    default:
                        outlineMenus();
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });



        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                outlineMenus();
                switch(menuItem.getItemId()) {
                    case R.id.action_home:
                        viewPager.setCurrentItem(0, true);
                        menuItem.setIcon(R.drawable.instagram_home_filled_24);
                        Log.d(TAG, "HOME");
                        return true;
                    case R.id.action_create:
                        viewPager.setCurrentItem(1, true);
                        menuItem.setIcon(R.drawable.instagram_new_post_filled_24);
                        return true;
                    case R.id.action_profile:
                        viewPager.setCurrentItem(2, true);
                        menuItem.setIcon(R.drawable.instagram_user_filled_24);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    private void outlineMenus() {
        Menu btnMenu = bottomNavigation.getMenu();
        btnMenu.findItem(R.id.action_create).setIcon(R.drawable.instagram_new_post_outline_24);
        btnMenu.findItem(R.id.action_home).setIcon(R.drawable.instagram_home_outline_24);
        btnMenu.findItem(R.id.action_profile).setIcon(R.drawable.instagram_user_outline_24);
    }

}
