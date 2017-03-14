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

    float min;
    float max;
    float progress;
    boolean floatType;
    int trackSize;
    int secondTrackSize;
    int thumbRadius;
    int thumbRadiusOnDragging;
    int trackColor;
    int secondTrackColor;
    int thumbColor;
    int sectionCount;
    boolean showSectionMark;
    boolean autoAdjustSectionMark;
    boolean showSectionText;
    int sectionTextSize;
    int sectionTextColor;
    @BubbleSeekBar.TextPosition
    int sectionTextPosition;
    int sectionTextInterval;
    boolean showThumbText;
    int thumbTextSize;
    int thumbTextColor;
    boolean showProgressInFloat;
    boolean touchToSeek;
    boolean seekBySection;
    int bubbleColor;
    int bubbleTextSize;
    int bubbleTextColor;

    private BubbleSeekBar mBubbleSeekBar;

    BubbleConfigBuilder(BubbleSeekBar bubbleSeekBar) {
        mBubbleSeekBar = bubbleSeekBar;
    }

    public void build() {
        mBubbleSeekBar.config(this);
    }

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

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public float getProgress() {
        return progress;
    }

    public boolean isFloatType() {
        return floatType;
    }

    public int getTrackSize() {
        return trackSize;
    }

    public int getSecondTrackSize() {
        return secondTrackSize;
    }

    public int getThumbRadius() {
        return thumbRadius;
    }

    public int getThumbRadiusOnDragging() {
        return thumbRadiusOnDragging;
    }

    public int getTrackColor() {
        return trackColor;
    }

    public int getSecondTrackColor() {
        return secondTrackColor;
    }

    public int getThumbColor() {
        return thumbColor;
    }

    public int getSectionCount() {
        return sectionCount;
    }

    public boolean isShowSectionMark() {
        return showSectionMark;
    }

    public boolean isAutoAdjustSectionMark() {
        return autoAdjustSectionMark;
    }

    public boolean isShowSectionText() {
        return showSectionText;
    }

    public int getSectionTextSize() {
        return sectionTextSize;
    }

    public int getSectionTextColor() {
        return sectionTextColor;
    }

    public int getSectionTextPosition() {
        return sectionTextPosition;
    }

    public int getSectionTextInterval() {
        return sectionTextInterval;
    }

    public boolean isShowThumbText() {
        return showThumbText;
    }

    public int getThumbTextSize() {
        return thumbTextSize;
    }

    public int getThumbTextColor() {
        return thumbTextColor;
    }

    public boolean isShowProgressInFloat() {
        return showProgressInFloat;
    }

    public boolean isTouchToSeek() {
        return touchToSeek;
    }

    public boolean isSeekBySection() {
        return seekBySection;
    }

    public int getBubbleColor() {
        return bubbleColor;
    }

    public int getBubbleTextSize() {
        return bubbleTextSize;
    }

    public int getBubbleTextColor() {
        return bubbleTextColor;
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
