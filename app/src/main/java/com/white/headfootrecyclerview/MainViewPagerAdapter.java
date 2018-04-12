package com.white.headfootrecyclerview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by MicYun on 2017/10/23.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

  private List<Fragment> list;
  private String[] titles;

  public MainViewPagerAdapter(FragmentManager fm, List<Fragment> list, String[] titles) {
    super(fm);
    this.list = list;
    this.titles = titles;
  }

  @Override
  public Fragment getItem(int position) {
    return null != list ? list.get(position) : null;
  }

  @Override
  public int getCount() {
    return null != list ? list.size() : 0;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return titles[position];
  }
}
