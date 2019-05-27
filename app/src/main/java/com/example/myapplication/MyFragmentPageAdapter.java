package com.example.myapplication;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class MyFragmentPageAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 2;
    MostNewFragment mostNewFragment = null;
    HottestFragment hottestFragment = null;

    public MyFragmentPageAdapter(FragmentManager fm) {
        super(fm);
        mostNewFragment = new MostNewFragment();
        hottestFragment = new HottestFragment();
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public int getCount() {
        return this.PAGER_COUNT;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fg = null;
        switch (position) {
            case ConsultFragment.PAGE_ONE:
                fg = mostNewFragment;
                break;
            case ConsultFragment.PAGE_TWO:
                fg = hottestFragment;
                break;
        }
        return fg;
    }
}
