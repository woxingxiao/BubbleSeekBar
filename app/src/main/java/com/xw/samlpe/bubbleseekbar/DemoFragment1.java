package com.xw.samlpe.bubbleseekbar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xw.repo.BubbleSeekBar;

import java.util.Random;

/**
 * DemoFragment1
 * <p>
 * Created by woxingxiao on 2017-03-11.
 */

public class DemoFragment1 extends Fragment {

    public static DemoFragment1 newInstance() {
        return new DemoFragment1();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_demo_1, container, false);

        final BubbleSeekBar bubbleSeekBar = view.findViewById(R.id.demo_1_seek_bar);
        bubbleSeekBar.setProgress(20);
        Button button = view.findViewById(R.id.demo_1_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress = new Random().nextInt((int) bubbleSeekBar.getMax());
                bubbleSeekBar.setProgress(progress);
                Snackbar.make(v, "set random progress = " + progress, Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
