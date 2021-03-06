package com.cryptoapp.chuddyni.cryptoapp.currencylist;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.cryptoapp.chuddyni.cryptoapp.BuildConfig;
import com.cryptoapp.chuddyni.cryptoapp.DrawerController;
import com.cryptoapp.chuddyni.cryptoapp.R;
import com.cryptoapp.chuddyni.cryptoapp.models.rest.CMCCoin;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class CurrencyListTabsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        FavoriteCurrencyListFragment.AllCoinsListUpdater, AllCurrencyListFragment.FavoritesListUpdater, DrawerController {

    private SectionsPagerAdapterCurrencyList mSectionsPagerAdapter;
    public ViewPager mViewPager;
    public static String baseImageURL = "";
    public static String SYMBOL = "SYMBOL";
    private Toolbar mToolbar;
    boolean doubleBackToExitPressedOnce = false;
    public static String IMAGE_URL_FORMAT = "https://s2.coinmarketcap.com/static/img/coins/32x32/%s.png";
    public final static String DAY = "24h";
    public final static String WEEK = "7d";
    public final static String HOUR = "1h";
    public final static String SORT_SETTING = "sort_setting";
    public AppCompatActivity context;
    private LibsBuilder libsBuilder;
    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list_tabs);
        context = this;
        mToolbar = findViewById(R.id.toolbar_currency_list);
        setSupportActionBar(mToolbar);
        TabLayout tabLayout = findViewById(R.id.currency_list_tabs);
        mViewPager = findViewById(R.id.currency_list_tabs_container);
        libsBuilder = new LibsBuilder()
                //provide a style (optional) (LIGHT, DARK, LIGHT_DARK_TOOLBAR)
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                .withAboutIconShown(true)
                .withLicenseShown(true)
                .withVersionShown(true)
                .withAboutVersionShownName(true)
                .withAboutVersionShownCode(true)
                .withAboutVersionString("Version: " + BuildConfig.VERSION_NAME)
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                .withActivityTitle("cryptoapp")
                .withLibraries("easyrest", "materialabout", "androiddevicenames", "customtabs", "togglebuttongroup", "materialfavoritebutton");
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .build();
        drawer.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                switch (position) {
                    case 1:
                        drawer.closeDrawer();
                        return true;
                    case 2:
                        drawer.closeDrawer();
                        drawer.setSelection(1);
                        return true;
                    case 3:
                        drawer.closeDrawer();
                        drawer.setSelection(1);
                        return true;
                    case 4:
                        drawer.closeDrawer();
                        drawer.setSelection(1);
                        libsBuilder.start(context);
                    default:
                        return true;
                }
            }
        });

        mSectionsPagerAdapter = new SectionsPagerAdapterCurrencyList(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.addOnPageChangeListener(this);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        Fragment fragment = mSectionsPagerAdapter.getFragment(position);
        if (fragment != null) {
            fragment.onResume();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tap back again to exit.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void hideHamburger() {
        drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
    }

    public void showHamburger() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
    }

    public void removeFavorite(CMCCoin coin) {
        FavoriteCurrencyListFragment frag = (FavoriteCurrencyListFragment) mSectionsPagerAdapter.getFragment(1);
        if (frag != null) {
            frag.removeFavorite(coin);
        }
    }

    public void addFavorite(CMCCoin coin) {
        FavoriteCurrencyListFragment frag = (FavoriteCurrencyListFragment) mSectionsPagerAdapter.getFragment(1);
        if (frag != null) {
            frag.addFavorite(coin);
        }
    }

    public void allCoinsModifyFavorites(CMCCoin coin) {
        AllCurrencyListFragment frag = (AllCurrencyListFragment) mSectionsPagerAdapter.getFragment(0);
        if (frag != null) {
            frag.getAdapter().notifyDataSetChanged();
        }
    }

    public void performFavsSort() {
        FavoriteCurrencyListFragment frag = (FavoriteCurrencyListFragment) mSectionsPagerAdapter.getFragment(1);
        if (frag != null) {
            frag.performFavsSort();
        }
    }

    public void performAllCoinsSort() {
        AllCurrencyListFragment frag = (AllCurrencyListFragment) mSectionsPagerAdapter.getFragment(0);
        if (frag != null) {
            frag.performAllCoinsSort();
        }
    }

}
