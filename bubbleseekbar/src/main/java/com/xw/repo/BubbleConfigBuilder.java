package com.xw.repo;

import android.content.res.Resources;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.util.TypedValue;

/**
 * config BubbleSeekBar's attributes
 * <p/>
 * Created by woxingxiao on 2017-03-14.
 */
public class BubbleConfigBuilder {

    public float min, max, progress;
    public int trackSize, trackColor, secondTrackSize, secondTrackColor, thumbRadius, thumbColor,
            thumbRadiusOnDragging, sectionCount, sectionTextSize, sectionTextColor,
            sectionTextPosition, sectionTextInterval, thumbTextSize, thumbTextColor,
            bubbleColor, bubbleTextSize, bubbleTextColor;
    public boolean floatType, showSectionMark, autoAdjustSectionMark, showSectionText,
            showThumbText, showProgressInFloat, touchToSeek, seekBySection;

    public BubbleConfigBuilder min(float min) {
        this.min = min;
        return this;
    }

    public BubbleConfigBuilder max(float max) {
        this.max = max;
        return this;
    }

    public BubbleConfigBuilder progress(float progress) {
        this.progress = progress;
        return this;
    }

    public BubbleConfigBuilder floatType(boolean b) {
        this.floatType = b;
        return this;
    }

    public BubbleConfigBuilder trackSize(int dp) {
        this.trackSize = dp2px(dp);
        return this;
    }

    public BubbleConfigBuilder secondTrackSize(int dp) {
        this.secondTrackSize = dp2px(dp);
        return this;
    }

    public BubbleConfigBuilder thumbRadius(int dp) {
        this.thumbRadius = dp2px(dp);
        return this;
    }

    public BubbleConfigBuilder thumbRadiusOnDragging(int dp) {
        this.thumbRadiusOnDragging = dp2px(dp);
        return this;
    }

    public BubbleConfigBuilder trackColor(@ColorInt int color) {
        this.trackColor = color;
        return this;
    }

    public BubbleConfigBuilder secondTrackColor(@ColorInt int color) {
        this.secondTrackColor = color;
        return this;
    }

    public BubbleConfigBuilder thumbColor(@ColorInt int color) {
        this.thumbColor = color;
        return this;
    }

    public BubbleConfigBuilder sectionCount(int dp) {
        this.sectionCount = dp2px(dp);
        return this;
    }

    public BubbleConfigBuilder showSectionMark(boolean show) {
        this.showSectionMark = show;
        return this;
    }

    public BubbleConfigBuilder autoAdjustSectionMark(boolean auto) {
        this.autoAdjustSectionMark = auto;
        return this;
    }

    public BubbleConfigBuilder showSectionText(boolean show) {
        this.showSectionText = show;
        return this;
    }

    public BubbleConfigBuilder sectionTextSize(int sp) {
        this.sectionTextSize = sp2px(sp);
        return this;
    }

    public BubbleConfigBuilder sectionTextColor(@ColorInt int color) {
        this.sectionTextColor = color;
        return this;
    }

    public BubbleConfigBuilder sectionTextPosition(@BubbleSeekBar.TextPosition int position) {
        this.sectionTextPosition = position;
        return this;
    }

    public BubbleConfigBuilder sectionTextInterval(@IntRange(from = 1, to = 100) int interval) {
        this.sectionTextInterval = interval;
        return this;
    }

    public BubbleConfigBuilder showThumbText(boolean show) {
        this.showThumbText = show;
        return this;
    }

    public BubbleConfigBuilder thumbTextSize(int sp) {
        this.thumbTextSize = sp2px(sp);
        return this;
    }

    public BubbleConfigBuilder thumbTextColor(@ColorInt int color) {
        thumbTextColor = color;
        return this;
    }

    public BubbleConfigBuilder showProgressInFloat(boolean show) {
        this.showProgressInFloat = show;
        return this;
    }

    public BubbleConfigBuilder touchToSeek(boolean b) {
        this.touchToSeek = b;
        return this;
    }

    public BubbleConfigBuilder seekBySection(boolean b) {
        this.seekBySection = b;
        return this;
    }

    public BubbleConfigBuilder bubbleColor(@ColorInt int color) {
        this.bubbleColor = color;
        return this;
    }

    public BubbleConfigBuilder bubbleTextSize(int sp) {
        this.bubbleTextSize = sp2px(sp);
        return this;
    }

    public BubbleConfigBuilder bubbleTextColor(@ColorInt int color) {
        this.bubbleTextColor = color;
        return this;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }
}
