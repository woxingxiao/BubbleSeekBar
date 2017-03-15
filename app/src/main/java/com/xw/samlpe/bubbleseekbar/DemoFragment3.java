package com.xw.samlpe.bubbleseekbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xw.repo.BubbleSeekBar;

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
        BubbleSeekBar bubbleSeekBar1 = (BubbleSeekBar) view.findViewById(R.id.demo_3_seek_bar_1);
        BubbleSeekBar bubbleSeekBar2 = (BubbleSeekBar) view.findViewById(R.id.demo_3_seek_bar_2);
        BubbleSeekBar bubbleSeekBar3 = (BubbleSeekBar) view.findViewById(R.id.demo_3_seek_bar_3);
        BubbleSeekBar bubbleSeekBar4 = (BubbleSeekBar) view.findViewById(R.id.demo_3_seek_bar_4);

        bubbleSeekBar1.getConfigBuilder()
                .min(0)
                .max(50)
                .progress(20)
                .sectionCount(5)
                .trackColor(ContextCompat.getColor(getContext(), R.color.color_gray))
                .secondTrackColor(ContextCompat.getColor(getContext(), R.color.color_blue))
                .thumbColor(ContextCompat.getColor(getContext(), R.color.color_blue))
                .showSectionText()
                .sectionTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary))
                .sectionTextSize(18)
                .showThumbText()
                .thumbTextColor(ContextCompat.getColor(getContext(), R.color.color_red))
                .thumbTextSize(18)
                .bubbleColor(ContextCompat.getColor(getContext(), R.color.color_green))
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
                .trackColor(ContextCompat.getColor(getContext(), R.color.color_red_light))
                .secondTrackColor(ContextCompat.getColor(getContext(), R.color.color_red))
                .seekBySection()
                .showSectionText()
                .sectionTextPosition(BubbleSeekBar.TextPosition.BELOW_SECTION_MARK)
                .build();

        bubbleSeekBar3.getConfigBuilder()
                .min(1)
                .max(1.5f)
                .floatType()
                .sectionCount(10)
                .secondTrackColor(ContextCompat.getColor(getContext(), R.color.color_green))
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

}
