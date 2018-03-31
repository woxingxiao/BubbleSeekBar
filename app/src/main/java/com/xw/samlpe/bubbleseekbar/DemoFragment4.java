package com.xw.samlpe.bubbleseekbar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xw.repo.BubbleSeekBar;

import java.util.Locale;

/**
 * DemoFragment4
 * <p>
 * Created by woxingxiao on 2017-03-11.
 */

public class DemoFragment4 extends Fragment {

    private Activity mActivity;

    public static DemoFragment4 newInstance() {
        return new DemoFragment4();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_demo_4, container, false);
        ObservableScrollView mObsScrollView = view.findViewById(R.id.demo_4_obs_scroll_view);
        final BubbleSeekBar bubbleSeekBar1 = view.findViewById(R.id.demo_4_seek_bar_1);
        final BubbleSeekBar bubbleSeekBar2 = view.findViewById(R.id.demo_4_seek_bar_2);
        final BubbleSeekBar bubbleSeekBar3 = view.findViewById(R.id.demo_4_seek_bar_3);
        final BubbleSeekBar bubbleSeekBar4 = view.findViewById(R.id.demo_4_seek_bar_4);
        final TextView progressText1 = view.findViewById(R.id.demo_4_progress_text_1);
        final TextView progressText2 = view.findViewById(R.id.demo_4_progress_text_2);
        final TextView progressText3 = view.findViewById(R.id.demo_4_progress_text_3);

        mObsScrollView.setOnScrollChangedListener(new ObservableScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                bubbleSeekBar1.correctOffsetWhenContainerOnScrolling();
            }
        });
        bubbleSeekBar2.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                String s = String.format(Locale.CHINA, "onChanged int:%d, float:%.1f", progress, progressFloat);
                progressText1.setText(s);
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                String s = String.format(Locale.CHINA, "onActionUp int:%d, float:%.1f", progress, progressFloat);
                progressText2.setText(s);
            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                String s = String.format(Locale.CHINA, "onFinally int:%d, float:%.1f", progress, progressFloat);
                progressText3.setText(s);
            }
        });

        // trigger by set progress or seek by finger
        bubbleSeekBar3.setProgress(bubbleSeekBar3.getMax());

        // customize section texts
        bubbleSeekBar4.setCustomSectionTextArray(new BubbleSeekBar.CustomSectionTextArray() {
            @NonNull
            @Override
            public SparseArray<String> onCustomize(int sectionCount, @NonNull SparseArray<String> array) {
                array.clear();
                array.put(1, "bad");
                array.put(4, "ok");
                array.put(7, "good");
                array.put(9, "great");

                return array;
            }
        });
        bubbleSeekBar4.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                int color;
                if (progress <= 10) {
                    color = ContextCompat.getColor(mActivity, R.color.color_red);
                } else if (progress <= 40) {
                    color = ContextCompat.getColor(mActivity, R.color.color_red_light);
                } else if (progress <= 70) {
                    color = ContextCompat.getColor(mActivity, R.color.colorAccent);
                } else if (progress <= 90) {
                    color = ContextCompat.getColor(mActivity, R.color.color_blue);
                } else {
                    color = ContextCompat.getColor(mActivity, R.color.color_green);
                }

                bubbleSeekBar.setSecondTrackColor(color);
                bubbleSeekBar.setThumbColor(color);
                bubbleSeekBar.setBubbleColor(color);
            }
        });
        bubbleSeekBar4.setProgress(60);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = (Activity) context;
    }
}
