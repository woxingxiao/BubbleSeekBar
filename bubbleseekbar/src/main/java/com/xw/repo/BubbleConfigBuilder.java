package com.xw.repo;

import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;

import static com.xw.repo.BubbleUtils.dp2px;
import static com.xw.repo.BubbleUtils.sp2px;

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
    long animDuration;
    boolean touchToSeek;
    boolean seekStepSection;
    boolean seekBySection;
    int bubbleColor;
    int bubbleTextSize;
    int bubbleTextColor;
    boolean alwaysShowBubble;
    long alwaysShowBubbleDelay;
    boolean hideBubble;
    boolean rtl;

    private BubbleSeekBar mBubbleSeekBar;

    BubbleConfigBuilder(BubbleSeekBar bubbleSeekBar) {
        mBubbleSeekBar = bubbleSeekBar;
    }

    public void build() {
        mBubbleSeekBar.config(this);
    }

    public BubbleConfigBuilder min(float min) {
        this.min = min;
        this.progress = min;
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

    public BubbleConfigBuilder floatType() {
        this.floatType = true;
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
        this.sectionTextColor = color;
        return this;
    }

    public BubbleConfigBuilder secondTrackColor(@ColorInt int color) {
        this.secondTrackColor = color;
        this.thumbColor = color;
        this.thumbTextColor = color;
        this.bubbleColor = color;
        return this;
    }

    public BubbleConfigBuilder thumbColor(@ColorInt int color) {
        this.thumbColor = color;
        return this;
    }

    public BubbleConfigBuilder sectionCount(@IntRange(from = 1) int count) {
        this.sectionCount = count;
        return this;
    }

    public BubbleConfigBuilder showSectionMark() {
        this.showSectionMark = true;
        return this;
    }

    public BubbleConfigBuilder autoAdjustSectionMark() {
        this.autoAdjustSectionMark = true;
        return this;
    }

    public BubbleConfigBuilder showSectionText() {
        this.showSectionText = true;
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

    public BubbleConfigBuilder sectionTextInterval(@IntRange(from = 1) int interval) {
        this.sectionTextInterval = interval;
        return this;
    }

    public BubbleConfigBuilder showThumbText() {
        this.showThumbText = true;
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

    public BubbleConfigBuilder showProgressInFloat() {
        this.showProgressInFloat = true;
        return this;
    }

    public BubbleConfigBuilder animDuration(long duration) {
        animDuration = duration;
        return this;
    }

    public BubbleConfigBuilder touchToSeek() {
        this.touchToSeek = true;
        return this;
    }

    public BubbleConfigBuilder seekStepSection() {
        this.seekStepSection = true;
        return this;
    }

    public BubbleConfigBuilder seekBySection() {
        this.seekBySection = true;
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

    public BubbleConfigBuilder alwaysShowBubble() {
        this.alwaysShowBubble = true;
        return this;
    }

    public BubbleConfigBuilder alwaysShowBubbleDelay(long delay) {
        alwaysShowBubbleDelay = delay;
        return this;
    }

    public BubbleConfigBuilder hideBubble() {
        this.hideBubble = true;
        return this;
    }

    public BubbleConfigBuilder rtl(boolean rtl) {
        this.rtl = rtl;
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

    public long getAnimDuration() {
        return animDuration;
    }

    public boolean isTouchToSeek() {
        return touchToSeek;
    }

    public boolean isSeekStepSection() {
        return seekStepSection;
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

    public boolean isAlwaysShowBubble() {
        return alwaysShowBubble;
    }

    public long getAlwaysShowBubbleDelay() {
        return alwaysShowBubbleDelay;
    }

    public boolean isHideBubble() {
        return hideBubble;
    }

    public boolean isRtl() {
        return rtl;
    }
}
