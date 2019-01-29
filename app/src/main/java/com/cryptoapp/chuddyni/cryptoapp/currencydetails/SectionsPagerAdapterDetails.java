package com.cryptoapp.chuddyni.cryptoapp.currencydetails;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cryptoapp.chuddyni.cryptoapp.currencydetails.chartandtable.GraphFragment;
import com.cryptoapp.chuddyni.cryptoapp.currencydetails.markets.MarketsFragment;

public class SectionsPagerAdapterDetails extends FragmentPagerAdapter {

    private String symbol;
    private String id;

    public SectionsPagerAdapterDetails(FragmentManager fm) {
        super(fm);
    }
    protected SectionsPagerAdapterDetails(FragmentManager fm, String symbol, String id) {
        super(fm);
        this.symbol = symbol;
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return GraphFragment.newInstance(this.symbol, this.id);
            case 1:
                return MarketsFragment.newInstance(this.symbol);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Chart";
            case 1:
                return "Markets";
            default:
                return null;
        }
    }
}
