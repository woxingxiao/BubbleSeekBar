package com.xw.samlpe.bubbleseekbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xw.repo.BubbleSeekBar;

import java.util.Locale;

/**
 * DemoFragment1
 * <><p/>
 * Created by woxingxiao on 2017-03-11.
 */

public class DemoFragment2 extends Fragment {

    public static DemoFragment2 newInstance() {
        return new DemoFragment2();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_demo_2, container, false);

        final BubbleSeekBar bubbleSeekBar = (BubbleSeekBar) view.findViewById(R.id.demo_2_seek_bar);
        final TextView progressText1 = (TextView) view.findViewById(R.id.demo_2_progress_text_1);
        final TextView progressText2 = (TextView) view.findViewById(R.id.demo_2_progress_text_2);
        final TextView progressText3 = (TextView) view.findViewById(R.id.demo_2_progress_text_3);

        bubbleSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(int progress, float progressFloat) {
                String s = String.format(Locale.CHINA, "onChanged int:%d, float:%.1f", progress, progressFloat);
                progressText1.setText(s);
            }

            @Override
            public void getProgressOnActionUp(int progress, float progressFloat) {
                String s = String.format(Locale.CHINA, "onActionUp int:%d, float:%.1f", progress, progressFloat);
                progressText2.setText(s);
            }

            @Override
            public void getProgressOnFinally(int progress, float progressFloat) {
                String s = String.format(Locale.CHINA, "onFinally int:%d, float:%.1f", progress, progressFloat);
                progressText3.setText(s);
            }
        });

        return view;
    }
}
