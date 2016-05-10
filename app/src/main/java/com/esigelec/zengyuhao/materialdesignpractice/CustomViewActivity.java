package com.esigelec.zengyuhao.materialdesignpractice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.esigelec.zengyuhao.materialdesignpractice.Views.CircleProgressBar;
import com.esigelec.zengyuhao.materialdesignpractice.Views.CircleProgressBarView;

public class CustomViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        final CircleProgressBarView progressBar = (CircleProgressBarView) findViewById(R.id.progressbar);
        progressBar.updatePercentage(50);

        final CircleProgressBar progressBar1 = (CircleProgressBar) findViewById(R.id.progressbar1);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setMax(100);
        seekBar.setProgress(50);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.updatePercentage(progress);
                progressBar1.setPercentage(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


}
