package com.xw.samlpe.bubbleseekbar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xw.repo.BubbleSeekBar;

/**
 * DemoFragment3
 * <p>
 * Created by woxingxiao on 2017-03-11.
 */

public class DemoFragment3 extends Fragment {

    private Activity mActivity;

    public static DemoFragment3 newInstance() {
        return new DemoFragment3();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_demo_3, container, false);
        BubbleSeekBar bubbleSeekBar1 = view.findViewById(R.id.demo_3_seek_bar_1);
        BubbleSeekBar bubbleSeekBar2 = view.findViewById(R.id.demo_3_seek_bar_2);
        BubbleSeekBar bubbleSeekBar3 = view.findViewById(R.id.demo_3_seek_bar_3);
        BubbleSeekBar bubbleSeekBar4 = view.findViewById(R.id.demo_3_seek_bar_4);

        bubbleSeekBar1.getConfigBuilder()
                .min(0)
                .max(50)
                .progress(20)
                .sectionCount(5)
                .trackColor(ContextCompat.getColor(mActivity, R.color.color_gray))
                .secondTrackColor(ContextCompat.getColor(mActivity, R.color.color_blue))
                .thumbColor(ContextCompat.getColor(mActivity, R.color.color_blue))
                .showSectionText()
                .sectionTextColor(ContextCompat.getColor(mActivity, R.color.colorPrimary))
                .sectionTextSize(18)
                .showThumbText()
                .thumbTextColor(ContextCompat.getColor(mActivity, R.color.color_red))
                .thumbTextSize(18)
                .bubbleColor(ContextCompat.getColor(mActivity, R.color.color_green))
                .bubbleTextSize(18)
                .showSectionMark()
                .seekBySection()
                .autoAdjustSectionMark()
                .sectionTextPosition(BubbleSeekBar.TextPosition.BELOW_SECTION_MARK)
                .build();

        bubbleSeekBar2.getConfigBuilder()
                .min(-50)
                .max(50)
                .sectionCount(10)
                .sectionTextInterval(2)
                .trackColor(ContextCompat.getColor(mActivity, R.color.color_red_light))
                .secondTrackColor(ContextCompat.getColor(mActivity, R.color.color_red))
                .seekBySection()
                .showSectionText()
                .sectionTextPosition(BubbleSeekBar.TextPosition.BELOW_SECTION_MARK)
                .build();

        bubbleSeekBar3.getConfigBuilder()
                .min(1)
                .max(1.5f)
                .floatType()
                .sectionCount(10)
                .secondTrackColor(ContextCompat.getColor(mActivity, R.color.color_green))
                .showSectionText()
                .showThumbText()
                .build();

        bubbleSeekBar4.getConfigBuilder()
                .min(-0.4f)
                .max(0.4f)
                .progress(0)
                .floatType()
                .sectionCount(10)
                .sectionTextInterval(2)
                .showSectionText()
                .sectionTextPosition(BubbleSeekBar.TextPosition.BELOW_SECTION_MARK)
                .autoAdjustSectionMark()
                .build();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = (Activity) context;
    }
}
