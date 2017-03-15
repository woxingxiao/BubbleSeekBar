package com.xw.samlpe.bubbleseekbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.xw.repo.BubbleSeekBar;

import java.util.Random;

/**
 * DemoFragment1
 * <><p/>
 * Created by woxingxiao on 2017-03-11.
 */

public class DemoFragment1 extends Fragment {

    public static DemoFragment1 newInstance() {
        return new DemoFragment1();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_demo_1, container, false);

        final BubbleSeekBar bubbleSeekBar = (BubbleSeekBar) view.findViewById(R.id.demo_1_seek_bar);
        Button button = (Button) view.findViewById(R.id.demo_1_button);

        bubbleSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void getProgressOnActionUp(int progress, float progressFloat) {
                Toast.makeText(getContext(), "progressOnActionUp:" + progress, Toast.LENGTH_SHORT).show();
            }
        });
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
