package com.xw.samlpe.bubbleseekbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xw.repo.BubbleSeekBar;

/**
 * DemoFragment1
 * <><p/>
 * Created by woxingxiao on 2017-03-11.
 */

public class DemoFragment3 extends Fragment {

    private BubbleSeekBar mBubbleSeekBar;

    public static DemoFragment3 newInstance() {
        return new DemoFragment3();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_demo_3, container, false);
        ObservableScrollView mObsScrollView = (ObservableScrollView) view.findViewById(R.id.demo_3_obs_scroll_view);
        mBubbleSeekBar = (BubbleSeekBar) view.findViewById(R.id.demo_3_seek_bar);

        mObsScrollView.setOnScrollChangedListener(new ObservableScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                mBubbleSeekBar.correctOffsetWhenContainerOnScrolling();
            }
        });

        return view;
    }

}
