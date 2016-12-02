package com.xw.repo.bubbleseekbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.math.BigDecimal;

import static com.xw.repo.bubbleseekbar.BubbleSeekBar.TextPosition.BOTTOM;
import static com.xw.repo.bubbleseekbar.BubbleSeekBar.TextPosition.SIDES;

/**
 * 气泡形式可视化的自定义SeekBar
 * <p/>
 * Created by woxingxiao on 2016-10-27.
 */
public class BubbleSeekBar extends View {

    @IntDef({SIDES, BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TextPosition {
        int SIDES = 0, BOTTOM = 1;
    }

    private int mMin; // 起始值
    private int mMax; // 结束值
    private float mProgress; // 实时值
    private int mTrackSize; // 下层track的高度
    private int mSecondTrackSize; // 上层track的高度
    private int mThumbRadius; // thumb的半径
    private int mThumbRadiusOnDragging; // 当thumb被拖拽时的半径
    private int mSectionCount; // min到max均分的份数
    private int mThumbColor; // thumb的颜色
    private int mTrackColor; // 下层track的颜色
    private int mSecondTrackColor; // 上层track的颜色
    private boolean isShowText; // 是否显示起始结束值文字
    private int mTextSize; // 起始结束值文字大小
    private int mTextColor; // 起始结束值文字颜色
    @TextPosition
    private int mTextPosition; // 起始结束值文字位置
    private boolean isShowThumbText; // 是否显示实时值文字
    private int mThumbTextSize; // 实时值文字大小
    private int mThumbTextColor; // 实时值文字颜色
    private int mBubbleColor;// 气泡颜色
    private int mBubbleTextSize; // 气泡文字大小
    private int mBubbleTextColor; // 气泡文字颜色
    private boolean isShowSectionMark; // 是否显示份数
    private boolean isAutoAdjustSectionMark; // 是否自动滑到最近的整份数
    private boolean isShowProgressInFloat; // 是否显示1位小数的浮点数progress

    private int mDelta; // max - min
    private float mThumbCenterX; // thumb的中心X坐标
    private float mTrackLength; // track的长度
    private float mSectionOffset; // 一个section的长度
    private boolean isThumbOnDragging; // thumb是否在被拖动
    private int mTextSpace; // 文字与其他的间距
    private OnProgressChangedListener mOnProgressChangedListener; // progress变化监听

    private float mLeft;
    private float mRight;
    private Paint mPaint;
    private Rect mRectText;
    private WindowManager mWindowManager;

    private BubbleView mBubbleView; // 自定义气泡View
    private int mBubbleRadius; // 气泡半径
    private float mBubbleCenterRawSolidX; // 气泡在最左边的固定RawX
    private float mBubbleCenterRawSolidY; // 气泡的固定RawY
    private float mBubbleCenterRawX; // 气泡的实时RawX
    private WindowManager.LayoutParams mLayoutParams;

    public BubbleSeekBar(Context context) {
        this(context, null);
    }

    public BubbleSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BubbleSeekBar, defStyleAttr, 0);
        mMin = a.getInteger(R.styleable.BubbleSeekBar_bsb_min, 0);
        mMax = a.getInteger(R.styleable.BubbleSeekBar_bsb_max, 100);
        mProgress = a.getInteger(R.styleable.BubbleSeekBar_bsb_progress, mMin);
        mTrackSize = a.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_track_size, dp2px(2));
        mSecondTrackSize = a.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_second_track_size,
                mTrackSize + dp2px(2));
        mThumbRadius = a.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_thumb_radius,
                mSecondTrackSize + dp2px(2));
        mThumbRadiusOnDragging = a.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_thumb_radius,
                mSecondTrackSize * 2);
        mSectionCount = a.getInteger(R.styleable.BubbleSeekBar_bsb_section_count, 10);
        mTrackColor = a.getColor(R.styleable.BubbleSeekBar_bsb_track_color,
                ContextCompat.getColor(context, R.color.colorPrimary));
        mSecondTrackColor = a.getColor(R.styleable.BubbleSeekBar_bsb_second_track_color,
                ContextCompat.getColor(context, R.color.colorAccent));
        mThumbColor = a.getColor(R.styleable.BubbleSeekBar_bsb_thumb_color, mSecondTrackColor);
        isShowText = a.getBoolean(R.styleable.BubbleSeekBar_bsb_show_text, false);
        mTextSize = a.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_text_size, sp2px(14));
        mTextColor = a.getColor(R.styleable.BubbleSeekBar_bsb_text_color, mTrackColor);
        int pos = a.getInteger(R.styleable.BubbleSeekBar_bsb_text_position, 0);
        if (pos == 0) {
            mTextPosition = SIDES;
        } else if (pos == 1) {
            mTextPosition = BOTTOM;
        }
        isShowThumbText = a.getBoolean(R.styleable.BubbleSeekBar_bsb_show_thumb_text, false);
        mThumbTextSize = a.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_thumb_text_size, sp2px(14));
        mThumbTextColor = a.getColor(R.styleable.BubbleSeekBar_bsb_thumb_text_color, mSecondTrackColor);
        mBubbleColor = a.getColor(R.styleable.BubbleSeekBar_bsb_bubble_color, mSecondTrackColor);
        mBubbleTextSize = a.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_bubble_text_size, sp2px(14));
        mBubbleTextColor = a.getColor(R.styleable.BubbleSeekBar_bsb_bubble_text_color, Color.WHITE);
        isShowSectionMark = a.getBoolean(R.styleable.BubbleSeekBar_bsb_show_section_mark, false);
        isAutoAdjustSectionMark = a.getBoolean(R.styleable.BubbleSeekBar_bsb_auto_adjust_section_mark, false);
        isShowProgressInFloat = a.getBoolean(R.styleable.BubbleSeekBar_bsb_show_progress_in_float, false);
        a.recycle();

        if (mMin > mMax) {
            int tmp = mMax;
            mMax = mMin;
            mMin = tmp;
        }
        if (mProgress < mMin) {
            mProgress = mMin;
        }
        if (mSecondTrackSize <= mTrackSize) {
            mSecondTrackSize = mTrackSize + dp2px(2);
        }
        if (mThumbRadius <= mSecondTrackSize) {
            mThumbRadius = mSecondTrackSize + dp2px(2);
        }
        if (mThumbRadiusOnDragging <= mSecondTrackSize) {
            mThumbRadiusOnDragging = mSecondTrackSize * 2;
        }
        if (mSectionCount <= 0 || mSectionCount > mMax - mMin) {
            mSectionCount = 10;
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mRectText = new Rect();

        mDelta = mMax - mMin;
        mTextSpace = dp2px(2);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        mBubbleView = new BubbleView(context);
        mBubbleView.setProgressText(String.valueOf(isShowProgressInFloat ? getProgressInFloat() : getProgress()));

        mPaint.setTextSize(mBubbleTextSize);
        mPaint.getTextBounds(String.valueOf(mMin), 0, String.valueOf(mMin).length(), mRectText);
        int w1 = (mRectText.width() + mTextSpace * 2) / 2;
        if (isShowProgressInFloat) {
            String text = (mMin < 0 ? "-" + mMin : mMin) + ".0";
            mPaint.getTextBounds(text, 0, text.length(), mRectText);
            w1 = (mRectText.width() + mTextSpace * 2) / 2;
        }

        mPaint.getTextBounds(String.valueOf(mMax), 0, String.valueOf(mMax).length(), mRectText);
        int w2 = (mRectText.width() + mTextSpace * 2) / 2;
        if (isShowProgressInFloat) {
            String text = (mMax < 0 ? "-" + mMax : mMax) + ".0";
            mPaint.getTextBounds(text, 0, text.length(), mRectText);
            w2 = (mRectText.width() + mTextSpace * 2) / 2;
        }

        mBubbleRadius = dp2px(14);
        mBubbleRadius = Math.max(mBubbleRadius, Math.max(w1, w2));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = mThumbRadiusOnDragging * 2;
        if (isShowThumbText) {
            mPaint.setTextSize(mThumbTextSize);
            mPaint.getTextBounds("0", 0, 1, mRectText);
            height += mRectText.height() + mTextSpace;
        }
        if (isShowText && mTextPosition == TextPosition.BOTTOM) {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds("0", 0, 1, mRectText);
            height = Math.max(height, mThumbRadiusOnDragging * 2 + mRectText.height() + mTextSpace);
        }
        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                height + getPaddingTop() + getPaddingBottom());

        mLeft = getPaddingLeft() + mThumbRadiusOnDragging;
        mRight = getWidth() - getPaddingRight() - mThumbRadiusOnDragging;

        if (isShowText) {
            mPaint.setTextSize(mTextSize);
            if (mTextPosition == TextPosition.SIDES) {

                mPaint.getTextBounds(String.valueOf(mMin), 0, String.valueOf(mMin).length(), mRectText);
                mLeft += (mRectText.width() + mTextSpace);

                mPaint.getTextBounds(String.valueOf(mMax), 0, String.valueOf(mMax).length(), mRectText);
                mRight -= (mRectText.width() + mTextSpace);

            } else if (mTextPosition == TextPosition.BOTTOM) {

                mPaint.getTextBounds(String.valueOf(mMin), 0, String.valueOf(mMin).length(), mRectText);
                float max = Math.max(mThumbRadiusOnDragging, mRectText.width() / 2f);
                mLeft = getPaddingLeft() + max;

                mPaint.getTextBounds(String.valueOf(mMax), 0, String.valueOf(mMax).length(), mRectText);
                max = Math.max(mThumbRadiusOnDragging, mRectText.width() / 2f);
                mRight = getWidth() - getPaddingRight() - max;
            }
        }

        mTrackLength = mRight - mLeft;
        mSectionOffset = mTrackLength * 1f / mSectionCount;

        mBubbleView.measure(widthMeasureSpec, heightMeasureSpec);

        int[] points = new int[2];
        getLocationOnScreen(points);
        mBubbleCenterRawSolidX = points[0] + mLeft - mBubbleView.getMeasuredWidth() / 2f;
        mBubbleCenterRawX = mBubbleCenterRawSolidX + mTrackLength * (mProgress - mMin) / mDelta;
        mBubbleCenterRawSolidY = points[1] - mBubbleView.getMeasuredHeight() - dp2px(24);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float x1 = getPaddingLeft();
        float x2 = getWidth() - getPaddingRight();
        float y = getPaddingTop() + mThumbRadiusOnDragging;

        if (isShowText) {
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setTextSize(mTextSize);
            mPaint.setColor(mTextColor);

            if (mTextPosition == TextPosition.SIDES) {
                float y_ = y + mRectText.height() / 2f;

                mPaint.getTextBounds(String.valueOf(mMin), 0, String.valueOf(mMin).length(), mRectText);
                canvas.drawText(String.valueOf(mMin), x1 + mRectText.width() / 2f, y_, mPaint);
                x1 += mRectText.width() + mTextSpace;

                mPaint.getTextBounds(String.valueOf(mMax), 0, String.valueOf(mMax).length(), mRectText);
                canvas.drawText(String.valueOf(mMax), x2 - mRectText.width() / 2f, y_, mPaint);
                x2 -= (mRectText.width() + mTextSpace);

            } else if (mTextPosition == TextPosition.BOTTOM) {
                float y_ = y + mThumbRadiusOnDragging + mTextSpace;

                mPaint.getTextBounds(String.valueOf(mMin), 0, String.valueOf(mMin).length(), mRectText);
                y_ += mRectText.height();
                x1 = mLeft;
                canvas.drawText(String.valueOf(mMin), x1, y_, mPaint);

                mPaint.getTextBounds(String.valueOf(mMax), 0, String.valueOf(mMax).length(), mRectText);
                x2 = mRight;
                canvas.drawText(String.valueOf(mMax), x2, y_, mPaint);
            }
        }

        if (!isShowText || mTextPosition != TextPosition.BOTTOM) {
            x1 += mThumbRadiusOnDragging;
            x2 -= mThumbRadiusOnDragging;
        }

        if (!isThumbOnDragging) {
            mThumbCenterX = mTrackLength / mDelta * (mProgress - mMin) + x1;
        }

        if (isShowThumbText && !isThumbOnDragging) {
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setTextSize(mThumbTextSize);
            mPaint.setColor(mThumbTextColor);

            if (isShowProgressInFloat) {
                mPaint.getTextBounds(String.valueOf(getProgressInFloat()), 0,
                        String.valueOf(getProgressInFloat()).length(), mRectText);
                float y_ = y + mRectText.height() + mThumbRadiusOnDragging + mTextSpace;
                canvas.drawText(String.valueOf(getProgressInFloat()), mThumbCenterX, y_, mPaint);
            } else {
                mPaint.getTextBounds(String.valueOf(getProgress()), 0,
                        String.valueOf(getProgress()).length(), mRectText);
                float y_ = y + mRectText.height() + mThumbRadiusOnDragging + mTextSpace;
                canvas.drawText(String.valueOf(getProgress()), mThumbCenterX, y_, mPaint);
            }
        }

        if (isShowSectionMark) {
            // 画分段标识点
            float r = (mThumbRadiusOnDragging - dp2px(2)) / 2f;
            for (int i = 0; i <= mSectionCount; i++) {
                if (x1 + i * mSectionOffset <= mTrackLength / mDelta * Math.abs(mProgress - mMin) + mLeft) {
                    mPaint.setColor(mSecondTrackColor);
                } else {
                    mPaint.setColor(mTrackColor);
                }
                canvas.drawCircle(x1 + i * mSectionOffset, y, r, mPaint);
            }
        }

        // 画下层track
        mPaint.setColor(mSecondTrackColor);
        mPaint.setStrokeWidth(mSecondTrackSize);
        canvas.drawLine(x1, y, mThumbCenterX, y, mPaint);

        // 画上层track
        mPaint.setColor(mTrackColor);
        mPaint.setStrokeWidth(mTrackSize);
        canvas.drawLine(mThumbCenterX, y, x2, y, mPaint);

        // 画thumb
        mPaint.setColor(mThumbColor);
        canvas.drawCircle(mThumbCenterX, y, isThumbOnDragging ? mThumbRadiusOnDragging : mThumbRadius, mPaint);
    }

    float dx;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                isThumbOnDragging = isThumbTouched(event);
                if (isThumbOnDragging) {
                    showBubble();
                    invalidate();
                }
                dx = mThumbCenterX - event.getX();

                break;
            case MotionEvent.ACTION_MOVE:
                if (isThumbOnDragging) {
                    mThumbCenterX = event.getX() + dx;
                    if (mThumbCenterX < mLeft) {
                        mThumbCenterX = mLeft;
                    }
                    if (mThumbCenterX > mRight) {
                        mThumbCenterX = mRight;
                    }
                    mProgress = (mThumbCenterX - mLeft) * mDelta / mTrackLength + mMin;

                    mBubbleCenterRawX = mBubbleCenterRawSolidX + (mProgress - mMin) * 1f / mDelta * mTrackLength;
                    mLayoutParams.x = (int) mBubbleCenterRawX;
                    mWindowManager.updateViewLayout(mBubbleView, mLayoutParams);
                    mBubbleView.setProgressText(String.valueOf(isShowProgressInFloat ?
                            getProgressInFloat() : getProgress()));

                    invalidate();

                    if (mOnProgressChangedListener != null) {
                        mOnProgressChangedListener.onProgressChanged(getProgress());
                        mOnProgressChangedListener.onProgressChanged(getProgressInFloat());
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (isThumbOnDragging) {
                    mBubbleView.animate().alpha(0f).setDuration(200)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    mBubbleView.setVisibility(GONE); // 防闪烁
                                    mWindowManager.removeView(mBubbleView);
                                    isThumbOnDragging = false;

                                    invalidate();
                                }
                            }).start();
                }

                if (isAutoAdjustSectionMark) {
                    autoAdjustSection();
                }

                break;
        }

        return isThumbOnDragging || super.onTouchEvent(event);
    }

    /**
     * 识别thumb是否被有效点击
     */
    private boolean isThumbTouched(MotionEvent event) {
        float x = mTrackLength / mDelta * (mProgress - mMin) + mLeft;
        float y = getHeight() / 2f;
        return (event.getX() - x) * (event.getX() - x) + (event.getY() - y) * (event.getY() - y)
                <= (mLeft + dp2px(4)) * (mLeft + dp2px(4));
    }

    /**
     * 显示气泡
     * 原理是利用WindowManager动态添加一个与Toast相同类型的BubbleView，消失时再移除
     */
    private void showBubble() {
        if (mLayoutParams == null) {
            mLayoutParams = new WindowManager.LayoutParams();
            mLayoutParams.gravity = Gravity.START | Gravity.TOP;
            mLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            mLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            mLayoutParams.format = PixelFormat.TRANSLUCENT;
            mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        }
        mLayoutParams.x = (int) mBubbleCenterRawX;
        mLayoutParams.y = (int) mBubbleCenterRawSolidY;

        mBubbleView.setAlpha(0);
        mBubbleView.setVisibility(VISIBLE);
        mBubbleView.animate().alpha(1f).setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        mWindowManager.addView(mBubbleView, mLayoutParams);
                    }
                }).start();
    }

    /**
     * 自动滚向最近的分段处
     */
    private void autoAdjustSection() {
        int i;
        float x = 0;
        for (i = 0; i <= mSectionCount; i++) {
            x = i * mSectionOffset + mLeft;
            if (x <= mThumbCenterX && mThumbCenterX - x <= mSectionOffset) {
                break;
            }
        }

        AnimatorSet animatorSet = new AnimatorSet();

        ValueAnimator valueAnim;
        if (mThumbCenterX - x <= mSectionOffset / 2f) {
            valueAnim = ValueAnimator.ofFloat(mThumbCenterX, x);
        } else {
            valueAnim = ValueAnimator.ofFloat(mThumbCenterX, (i + 1) * mSectionOffset + mLeft);
        }
        valueAnim.setInterpolator(new LinearInterpolator());
        valueAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mThumbCenterX = (float) animation.getAnimatedValue();

                mBubbleCenterRawX = mBubbleCenterRawSolidX + mThumbCenterX - mLeft;
                mLayoutParams.x = (int) mBubbleCenterRawX;
                mWindowManager.updateViewLayout(mBubbleView, mLayoutParams);
                mBubbleView.setProgressText(String.valueOf(isShowProgressInFloat ?
                        getProgressInFloat() : getProgress()));

                invalidate();
            }
        });

        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(mBubbleView, View.ALPHA, 0);

        animatorSet.setDuration(200).playTogether(valueAnim, alphaAnim);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mBubbleView.setVisibility(GONE); // 防闪烁
                mWindowManager.removeView(mBubbleView);

                mProgress = (mThumbCenterX - mLeft) * mDelta / mTrackLength + mMin;
                isThumbOnDragging = false;
            }
        });
        animatorSet.start();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }

    public int getMin() {
        return mMin;
    }

    public void setMin(int min) {
        if (mMin == min || min > mMax) {
            return;
        }

        mMin = min;
        mDelta = mMax - mMin;
        postInvalidate();
    }

    public int getMax() {
        return mMax;
    }

    public void setMax(int max) {
        mMax = max;
    }

    public int getProgress() {
        return Math.round(mProgress);
    }

    public float getProgressInFloat() {
        BigDecimal bigDecimal = BigDecimal.valueOf(mProgress);
        return bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public void setProgress(int progress) {
        if (mProgress == progress || progress < mMin || progress > mMax) {
            return;
        }

        mProgress = progress;
        mBubbleCenterRawX = mBubbleCenterRawSolidX + (mProgress - mMin) * 1f / mDelta * mTrackLength;

        postInvalidate();
    }

    public int getTrackSize() {
        return mTrackSize;
    }

    public void setTrackSize(int trackSize) {
        if (mTrackSize != trackSize) {
            mTrackSize = trackSize;
            if (mSecondTrackSize <= mTrackSize) {
                mSecondTrackSize = mTrackSize + dp2px(2);
            }
            if (mThumbRadius <= mSecondTrackSize) {
                mThumbRadius = mSecondTrackSize + dp2px(2);
            }
            if (mThumbRadiusOnDragging <= mSecondTrackSize) {
                mThumbRadiusOnDragging = mSecondTrackSize * 2;
            }

            postInvalidate();
        }
    }

    public int getSecondTrackSize() {
        return mSecondTrackSize;
    }

    public void setSecondTrackSize(int secondTrackSize) {
        if (mSecondTrackSize != secondTrackSize) {
            mSecondTrackSize = secondTrackSize;
            if (mSecondTrackSize <= mTrackSize) {
                mSecondTrackSize = mTrackSize + dp2px(2);
            }
            if (mThumbRadius <= mSecondTrackSize) {
                mThumbRadius = mSecondTrackSize + dp2px(2);
            }
            if (mThumbRadiusOnDragging <= mSecondTrackSize) {
                mThumbRadiusOnDragging = mSecondTrackSize * 2;
            }

            postInvalidate();
        }
    }

    public int getThumbRadius() {
        return mThumbRadius;
    }

    public void setThumbRadius(int thumbRadius) {
        if (mThumbRadius != thumbRadius) {
            mThumbRadius = thumbRadius;
            if (mThumbRadius <= mSecondTrackSize) {
                mThumbRadius = mSecondTrackSize + dp2px(2);
            }
            if (mThumbRadiusOnDragging <= mSecondTrackSize) {
                mThumbRadiusOnDragging = mSecondTrackSize * 2;
            }

            postInvalidate();
        }
    }

    public int getThumbRadiusOnDragging() {
        return mThumbRadiusOnDragging;
    }

    public void setThumbRadiusOnDragging(int thumbRadiusOnDragging) {
        if (mThumbRadiusOnDragging != thumbRadiusOnDragging) {
            mThumbRadiusOnDragging = thumbRadiusOnDragging;
            if (mThumbRadiusOnDragging <= mSecondTrackSize) {
                mThumbRadiusOnDragging = mSecondTrackSize * 2;
            }

            requestLayout();
        }
    }

    public int getSectionCount() {
        return mSectionCount;
    }

    public void setSectionCount(int sectionCount) {
        if (mSectionCount != sectionCount) {
            mSectionCount = sectionCount;
            if (mSectionCount <= 0 || mSectionCount > mMax - mMin) {
                mSectionCount = 10;
            }

            requestLayout();
        }
    }

    public int getTrackColor() {
        return mTrackColor;
    }

    public void setTrackColor(int trackColor) {
        if (mTrackColor != trackColor) {
            mTrackColor = trackColor;
            postInvalidate();
        }
    }

    public int getSecondTrackColor() {
        return mSecondTrackColor;
    }

    public void setSecondTrackColor(int secondTrackColor) {
        if (mSecondTrackColor != secondTrackColor) {
            mSecondTrackColor = secondTrackColor;
            postInvalidate();
        }
    }

    public int getBubbleColor() {
        return mBubbleColor;
    }

    public void setBubbleColor(int bubbleColor) {
        if (mBubbleColor != bubbleColor) {
            mBubbleColor = bubbleColor;
            mBubbleView.postInvalidate();
        }
    }

    public int getBubbleTextSize() {
        return mBubbleTextSize;
    }

    public void setBubbleTextSize(int bubbleTextSize) {
        if (mBubbleTextSize != bubbleTextSize) {
            mBubbleTextSize = bubbleTextSize;
            mBubbleView.postInvalidate();
        }
    }

    public int getBubbleTextColor() {
        return mBubbleTextColor;
    }

    public void setBubbleTextColor(int bubbleTextColor) {
        if (mBubbleTextColor != bubbleTextColor) {
            mBubbleTextColor = bubbleTextColor;
            mBubbleView.postInvalidate();
        }
    }

    public boolean isShowSectionMark() {
        return isShowSectionMark;
    }

    public void setShowSectionMark(boolean showSectionMark) {
        if (isShowSectionMark != showSectionMark) {
            isShowSectionMark = showSectionMark;
            postInvalidate();
        }
    }

    public boolean isAutoAdjustSectionMark() {
        return isAutoAdjustSectionMark;
    }

    public void setAutoAdjustSectionMark(boolean autoAdjustSectionMark) {
        if (isAutoAdjustSectionMark != autoAdjustSectionMark) {
            isAutoAdjustSectionMark = autoAdjustSectionMark;
            postInvalidate();
        }
    }

    public OnProgressChangedListener getOnProgressChangedListener() {
        return mOnProgressChangedListener;
    }

    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        mOnProgressChangedListener = onProgressChangedListener;
    }

    /**
     * progress改变监听器
     */
    public interface OnProgressChangedListener {

        void onProgressChanged(int progress);

        void onProgressChanged(float progress);
    }


    /*******************************************************************************************
     * ************************************  自定义气泡View  ************************************
     *******************************************************************************************/
    public class BubbleView extends View {

        private Paint mBubblePaint;
        private Path mBubblePath;
        private RectF mBubbleRectF;
        private Rect mRect;
        private String mProgressText = "";

        public BubbleView(Context context) {
            this(context, null);
        }

        public BubbleView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public BubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);

            mBubblePaint = new Paint();
            mBubblePaint.setAntiAlias(true);
            mBubblePaint.setTextAlign(Paint.Align.CENTER);

            mBubblePath = new Path();
            mBubbleRectF = new RectF();
            mRect = new Rect();
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            setMeasuredDimension(3 * mBubbleRadius, 3 * mBubbleRadius);

            mBubbleRectF.set(getWidth() / 2f - mBubbleRadius, 0, getWidth() / 2f + mBubbleRadius, 2 * mBubbleRadius);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            mBubblePath.reset();
            float x0 = getWidth() / 2f;
            float y0 = getHeight() - mBubbleRadius / 3f;
            mBubblePath.moveTo(x0, y0);
            float x1 = (float) (getWidth() / 2f - Math.sqrt(3) / 2f * mBubbleRadius);
            float y1 = 3 / 2f * mBubbleRadius;
            mBubblePath.quadTo(
                    x1 - dp2px(2), y1 - dp2px(2),
                    x1, y1
            );
            mBubblePath.arcTo(mBubbleRectF, 150, 240);

            float x2 = (float) (getWidth() / 2f + Math.sqrt(3) / 2f * mBubbleRadius);
            mBubblePath.quadTo(
                    x2 + dp2px(2), y1 - dp2px(2),
                    x0, y0
            );
            mBubblePath.close();

            mBubblePaint.setColor(mBubbleColor);
            canvas.drawPath(mBubblePath, mBubblePaint);

            mBubblePaint.setTextSize(mBubbleTextSize);
            mBubblePaint.setColor(mBubbleTextColor);
            mBubblePaint.getTextBounds(mProgressText, 0, mProgressText.length(), mRect);
            Paint.FontMetrics fontMetrics = mBubblePaint.getFontMetrics();
            float baseline = mBubbleRadius + (fontMetrics.descent - fontMetrics.ascent) / 2f - fontMetrics.descent;
            canvas.drawText(mProgressText, getWidth() / 2f, baseline, mBubblePaint);
        }

        public void setProgressText(String progressText) {
            if (progressText != null && !mProgressText.equals(progressText)) {
                mProgressText = progressText;
                invalidate();
            }
        }
    }

}