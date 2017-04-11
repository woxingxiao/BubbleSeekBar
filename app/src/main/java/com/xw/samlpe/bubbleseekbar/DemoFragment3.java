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
 * DemoFragment3
 * <><p/>
 * Created by woxingxiao on 2017-03-11.
 */

public class DemoFragment3 extends Fragment {

    public static DemoFragment3 newInstance() {
        return new DemoFragment3();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_demo_3, container, false);
        ObservableScrollView mObsScrollView = (ObservableScrollView) view.findViewById(R.id.demo_4_obs_scroll_view);
        final BubbleSeekBar bubbleSeekBar1 = (BubbleSeekBar) view.findViewById(R.id.demo_4_seek_bar_1);
        final BubbleSeekBar bubbleSeekBar2 = (BubbleSeekBar) view.findViewById(R.id.demo_4_seek_bar_2);
        final BubbleSeekBar bubbleSeekBar3 = (BubbleSeekBar) view.findViewById(R.id.demo_4_seek_bar_3);
        final TextView progressText1 = (TextView) view.findViewById(R.id.demo_4_progress_text_1);
        final TextView progressText2 = (TextView) view.findViewById(R.id.demo_4_progress_text_2);
        final TextView progressText3 = (TextView) view.findViewById(R.id.demo_4_progress_text_3);

        mObsScrollView.setOnScrollChangedListener(new ObservableScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                bubbleSeekBar1.correctOffsetWhenContainerOnScrolling();
            }
        });
        bubbleSeekBar2.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
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

        // trigger by set progress or seek by finger
        bubbleSeekBar3.setProgress(bubbleSeekBar3.getMax());

        return view;
    }

}
