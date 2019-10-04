package com.vira.echsan.util.iosdialog

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.vira.echsan.R

class CamomileSpinner : AppCompatImageView {

    private var spinnerColor: Int = 0
    private var duration: Int = 0
    private var clockwise = DEFAULT_CLOCKWISE

    constructor(context: Context) : super(context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.background = ContextCompat.getDrawable(context, R.drawable.spinner)
        } else {
            this.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.spinner))
        }
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initCustomAttr(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initCustomAttr(context, attrs)
    }

    @JvmOverloads
    constructor(
        context: Context, @ColorInt spinnerColor: Int,
        duration: Int = DEFAULT_DURATION,
        clockwise: Boolean = DEFAULT_CLOCKWISE
    ) : super(context) {
        this.spinnerColor = spinnerColor
        this.duration = duration
        this.clockwise = clockwise
        val spinnerAnimation = createSpinner(context, spinnerColor, duration, clockwise)
        updateSpinner(spinnerAnimation)
    }

    private fun initCustomAttr(context: Context, attrs: AttributeSet) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.CamomileSpinner,
            0, 0
        )
        try {
            duration = typedArray.getInteger(R.styleable.CamomileSpinner_duration, DEFAULT_DURATION)
            spinnerColor = typedArray.getColor(R.styleable.CamomileSpinner_spinnerColor, DEFAULT_COLOR)
            clockwise = typedArray.getBoolean(R.styleable.CamomileSpinner_clockwise, DEFAULT_CLOCKWISE)
            val spinnerAnimation = createSpinner(context, spinnerColor, duration, clockwise)
            updateSpinner(spinnerAnimation)
        } finally {
            typedArray.recycle()
        }
    }

    private fun createSpinner(
        context: Context, @ColorInt spinnerColor: Int,
        duration: Int,
        clockwise: Boolean
    ): AnimationDrawable? {
        return if (spinnerColor == DEFAULT_COLOR && duration == DEFAULT_DURATION && clockwise == CamomileSpinner.Companion.DEFAULT_CLOCKWISE) {
            //create from xml if default params
            DialogUtils.createAnimation(context)
        } else {
            //create programmatically if params is not default
            DialogUtils.createAnimation(context, spinnerColor, duration, clockwise)
        }
    }

    private fun updateSpinner(spinnerAnimation: AnimationDrawable?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.background = spinnerAnimation
        } else {
            this.setBackgroundDrawable(spinnerAnimation)
        }
    }

    fun start() {
        (this.background as AnimationDrawable).start()
    }

    fun stop() {
        (this.background as AnimationDrawable).stop()
    }

    fun recreateWithParams(context: Context, @ColorInt spinnerColor: Int, duration: Int, clockwise: Boolean) {
        var wasRunning = false
        if ((this.background as AnimationDrawable).isRunning) {
            wasRunning = true
            stop()
        }
        val newSpinner = createSpinner(context, spinnerColor, duration, clockwise)
        if (newSpinner != null) {
            this.spinnerColor = spinnerColor
            this.duration = duration
            this.clockwise = clockwise
            updateSpinner(newSpinner)
        }
        if (wasRunning) start()
    }

    companion object {
        val DEFAULT_DURATION = 60
        @ColorInt
        val DEFAULT_COLOR: Int

        init {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                DEFAULT_COLOR = Resources.getSystem().getColor(android.R.color.white, null)
            } else {
                DEFAULT_COLOR = Resources.getSystem().getColor(android.R.color.white)
            }
        }

        val DEFAULT_CLOCKWISE = true
    }
}//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    public CamomileSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        initCustomAttr(context, attrs);
//    }