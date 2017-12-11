package com.xw.repo;

/**
 * Created by ewojahn on 07.12.17.
 */

public class DefaultBubbleValueToTextConverter implements BubbleSeekBar.BubbleValueToTextConverter {
    @Override
    public String convertValueToText(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, Boolean isShowProgressInFloat) {
        return isShowProgressInFloat ?
                String.valueOf(progressFloat) : String.valueOf(progress);
    }
}
