package com.esigelec.zengyuhao.materialdesignpractice.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.esigelec.zengyuhao.materialdesignpractice.Core.Image.EfficientBitmap;
import com.esigelec.zengyuhao.materialdesignpractice.Fragment.BaseLazyFragment;
import com.esigelec.zengyuhao.materialdesignpractice.Fragment.LazyLoadTestFragment;
import com.esigelec.zengyuhao.materialdesignpractice.R;

public class LazyFragmentActivity extends AppCompatActivity {

    private static String[] titles = {"Dice", "Play", "Info", "Android", "Wu", "Earth"};
    private static int[] imageResId = {R.drawable.img0, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable
            .img4, R.drawable.img5};

    private static Bitmap mPlaceHolderBitmap;

    private int pagerHeigth = 100, pagerWidth = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy_fragment);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

        final ViewTreeObserver observer = viewPager.getViewTreeObserver();
        if (observer != null) {
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    pagerHeigth = viewPager.getHeight();
                    pagerWidth = viewPager.getWidth();
                    mPlaceHolderBitmap = EfficientBitmap.decodeBitmap(getResources(), R.drawable.ic_action_replay,
                            pagerHeigth, pagerWidth);

                    ViewTreeObserver observer1 = viewPager.getViewTreeObserver();
                    observer1.removeOnGlobalLayoutListener(this);
                }
            });
        }
        viewPager.setAdapter(new MyPagerAdapter(getFragmentManager()));
        //viewPager.setCurrentItem(0);

        //viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.i("addOnPageChangeListener", "-->" + state + " : " + System.currentTimeMillis());
//                if (state == 0) {
//                    Log.i("addOnPageChangeListener", "--> idle : " + System.currentTimeMillis());
//                }
            }
        });


//        try {
//            Field mScroller;
//            mScroller = ViewPager.class.getDeclaredField("mScroller");
//            mScroller.setAccessible(true);
//            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext());
//            // scroller.setFixedDuration(5000);
//            mScroller.set(viewPager, scroller);
//        } catch (NoSuchFieldException e) {
//        } catch (IllegalArgumentException e) {
//        } catch (IllegalAccessException e) {
//        }

    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return LazyLoadTestFragment.newInstance(BaseLazyFragment.MODE_LAZY, position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


    public class FixedSpeedScroller extends Scroller {

        private int mDuration = 200;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }


        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }
}
