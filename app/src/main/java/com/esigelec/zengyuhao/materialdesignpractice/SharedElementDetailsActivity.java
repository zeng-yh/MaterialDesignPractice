package com.esigelec.zengyuhao.materialdesignpractice;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AnimationUtils;

public class SharedElementDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element_details);
        getActionBar().hide();

        Slide slide = new Slide(Gravity.BOTTOM);
        slide.addTarget(R.id.description);
        slide.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in));
        slide.setDuration(400);
        getWindow().setEnterTransition(slide);
    }
}
