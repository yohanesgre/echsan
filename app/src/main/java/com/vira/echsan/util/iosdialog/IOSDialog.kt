package com.vira.echsan.util.iosdialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.*
import android.text.Html
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.vira.echsan.R

class IOSDialog : Dialog {
    lateinit var builder: Builder
    lateinit var rootView: ViewGroup
    var titleFrame: LinearLayout? = null
    var titleIcon: ImageView? = null
    var title: TextView? = null
    var spinner: CamomileSpinner? = null
    var message: TextView? = null

    private constructor(builder: Builder) : super(builder.context, builder.theme) {
        val inflater = LayoutInflater.from(builder.context)
        rootView = inflater.inflate(DialogInit.getInflateLayout(builder), null) as ViewGroup
        this.builder = builder
        DialogInit.init(this)
    }

    private constructor(context: Context, theme: Int) : super(context, theme) {}

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        val spinnerAnimation = spinner!!.background as AnimationDrawable
        spinnerAnimation.start()
    }

    class Builder(var context: Context) {
        var customView: View? = null
        lateinit var title: CharSequence
        lateinit var message: CharSequence
        var cancelable: Boolean = false
        var indeterminate: Boolean = false
        var dimAmount: Float = 0.toFloat()
        var spinnerDuration: Int = 0
        var titleGravity: Int = 0
        var messageGravity: Int = 0
        lateinit var showListener: DialogInterface.OnShowListener
        lateinit var cancelListener: DialogInterface.OnCancelListener
        lateinit var dismissListener: DialogInterface.OnDismissListener
        lateinit var keyListener: DialogInterface.OnKeyListener
        var regularFont: Typeface? = null
        var mediumFont: Typeface? = null
        var theme: Int = 0
        var titleColor: Int = 0
        var messageColor: Int = 0
        var spinnerColor: Int = 0
        var backgroundColor: Int = 0
        var spinnerClockwise = true
        var isTitleColorSet = false
        var isMessageColorSet = false
        var isSpinnerColorSet = false
        var isBackgroundColorSet = false

        init {
            this.theme = R.style.CamomileDialog
            this.cancelable = true
            this.titleGravity = Gravity.CENTER
            this.messageGravity = Gravity.CENTER
            this.dimAmount = 0.2f

            if (this.mediumFont == null) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        this.mediumFont = Typeface.create("sans-serif-medium", Typeface.NORMAL)
                    } else {
                        this.mediumFont = Typeface.create("sans-serif", Typeface.BOLD)
                    }
                } catch (ignored: Exception) {
                    this.mediumFont = Typeface.DEFAULT_BOLD
                }

            }
            if (this.regularFont == null) {
                try {
                    this.regularFont = Typeface.create("sans-serif", Typeface.NORMAL)
                } catch (ignored: Exception) {
                    this.regularFont = Typeface.SANS_SERIF
                    if (this.regularFont == null) {
                        this.regularFont = Typeface.DEFAULT
                    }
                }

            }
        }

        fun setTitle(@StringRes titleRes: Int): Builder {
            setTitle(this.context.getText(titleRes))
            return this
        }

        fun setTitle(title: CharSequence): Builder {
            this.title = title
            return this
        }

        fun setTitleGravity(gravity: Int): Builder {
            this.titleGravity = gravity
            return this
        }

        fun setMessageContent(@StringRes messageContent: Int): Builder {
            return setMessageContent(messageContent, false)
        }

        fun setMessageContent(@StringRes messageContentRes: Int, html: Boolean): Builder {
            var text = this.context.getText(messageContentRes)
            if (html) {
                text = Html.fromHtml(text.toString().replace("\n", "<br/>"))
            }
            return setMessageContent(text)
        }

        fun setMessageContent(@StringRes messageContentRes: Int, vararg formatArgs: Any): Builder {
            val str = String.format(this.context.getString(messageContentRes), *formatArgs)
                .replace("\n", "<br/>")

            return setMessageContent(Html.fromHtml(str))
        }

        fun setMessageContent(messageContent: CharSequence): Builder {
            if (this.customView != null) {
                throw IllegalStateException("You cannot setMessageContent() " + "when you're using a custom view.")
            }
            this.message = messageContent
            return this
        }

        fun setMessageContentGravity(gravity: Int): Builder {
            this.messageGravity = gravity
            return this
        }

        fun setMessageColor(@ColorInt color: Int): Builder {
            this.messageColor = color
            this.isMessageColorSet = true
            return this
        }

        fun setMessageColorRes(@ColorRes colorRes: Int): Builder {
            return setMessageColor(DialogUtils.getColor(this.context, colorRes))
        }

        fun setMessageColorAttr(@AttrRes colorAttr: Int): Builder {
            return setMessageColor(DialogUtils.resolveColor(this.context, colorAttr))
        }

        fun setCancelable(cancelable: Boolean): Builder {
            this.cancelable = cancelable
            return this
        }

        fun setOnCancelListener(cancelListener: DialogInterface.OnCancelListener): Builder {
            this.cancelListener = cancelListener
            return this
        }

        fun setOnShowListener(showListener: DialogInterface.OnShowListener): Builder {
            this.showListener = showListener
            return this
        }

        fun setOnDismissListener(dismissListener: DialogInterface.OnDismissListener): Builder {
            this.dismissListener = dismissListener
            return this
        }

        fun setOnKeyListener(keyListener: DialogInterface.OnKeyListener): Builder {
            this.keyListener = keyListener
            return this
        }

        @Deprecated("Do not use this method, it's not working now!")
        fun setIndeterminate(indeterminate: Boolean): Builder {
            this.indeterminate = indeterminate
            return this
        }

        @Deprecated("Do not use this method, it's not working now!")
        fun setDimAmount(dimAmount: Float): Builder {
            this.dimAmount = dimAmount
            return this
        }

        fun setSpinnerColor(@ColorInt color: Int): Builder {
            this.spinnerColor = color
            this.isSpinnerColorSet = true
            return this
        }

        fun setSpinnerColorRes(@ColorRes colorRes: Int): Builder {
            return setSpinnerColor(DialogUtils.getColor(this.context, colorRes))
        }

        fun setSpinnerColorAttr(@AttrRes colorAttr: Int): Builder {
            return setSpinnerColor(DialogUtils.resolveColor(this.context, colorAttr))
        }

        fun setSpinnerDuration(spinnerDuration: Int): Builder {
            this.spinnerDuration = spinnerDuration
            return this
        }

        fun setSpinnerClockwise(spinnerClockwise: Boolean): Builder {
            this.spinnerClockwise = spinnerClockwise
            return this
        }

        fun setTitleColor(@ColorInt color: Int): Builder {
            this.titleColor = color
            this.isTitleColorSet = true
            return this
        }

        fun setTitleColorRes(@ColorRes colorRes: Int): Builder {
            return setTitleColor(DialogUtils.getColor(this.context, colorRes))
        }

        fun setTitleColorAttr(@AttrRes colorAttr: Int): Builder {
            return setTitleColor(DialogUtils.resolveColor(this.context, colorAttr))
        }

        @Deprecated("Do not use this method, it's not working now!")
        fun setBackgroundColor(@ColorInt color: Int): Builder {
            this.backgroundColor = color
            this.isBackgroundColorSet = true
            return this
        }

        @Deprecated("Do not use this method, it's not working now!")
        fun setBackgroundColorRes(@ColorRes colorRes: Int): Builder {
            return setBackgroundColor(DialogUtils.getColor(this.context, colorRes))
        }

        @Deprecated("Do not use this method, it's not working now!")
        fun setBackgroundColorAttr(@AttrRes colorAttr: Int): Builder {
            return setBackgroundColor(DialogUtils.resolveColor(this.context, colorAttr))
        }

        fun setTheme(@StyleRes theme: Int): Builder {
            this.theme = theme
            return this
        }

        //TODO:: add setCustomView
        //TODO:: add set title icon

        @UiThread
        fun build(): IOSDialog {
            return IOSDialog(this)
        }

        @UiThread
        fun show(): IOSDialog {
            val dialog = build()
            dialog.show()
            return dialog
        }
    }

    private fun adjust(d: Drawable): Drawable {
        val to = Color.RED
        val src = (d as BitmapDrawable).bitmap
        val bitmap = src.copy(Bitmap.Config.ARGB_8888, true)
        for (x in 0 until bitmap.width)
            for (y in 0 until bitmap.height)
                if (match(bitmap.getPixel(x, y)))
                    bitmap.setPixel(x, y, to)
        return BitmapDrawable(bitmap)
    }

    private fun adjust(src: Bitmap): Bitmap {
        val to = Color.RED
        val bitmap = src.copy(Bitmap.Config.ARGB_8888, true)
        for (x in 0 until bitmap.width)
            for (y in 0 until bitmap.height)
                if (match(bitmap.getPixel(x, y)))
                    bitmap.setPixel(x, y, to)
        return bitmap
    }

    private fun match(pixel: Int): Boolean {
        return Math.abs(Color.red(pixel) - FROM_COLOR[0]) < THRESHOLD &&
                Math.abs(Color.green(pixel) - FROM_COLOR[1]) < THRESHOLD &&
                Math.abs(Color.blue(pixel) - FROM_COLOR[2]) < THRESHOLD
    }

    companion object {
        private val FROM_COLOR = intArrayOf(49, 179, 110)
        private val THRESHOLD = 3
    }

}