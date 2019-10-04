package com.vira.echsan.util.iosdialog

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.annotation.LayoutRes
import android.support.annotation.UiThread
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.vira.echsan.R

object DialogInit {
    @LayoutRes
    internal fun getInflateLayout(builder: IOSDialog.Builder): Int {
        return if (builder.customView != null) {
            -1
            //return R.layout.md_dialog_custom;
        } else {
            R.layout.xml_ios_progress_dialog
        }
    }

    @UiThread
    fun init(dialog: IOSDialog) {
        val builder = dialog.builder
        dialog.titleFrame = dialog.rootView.findViewById(R.id.title_frame) as LinearLayout
        dialog.titleIcon = dialog.rootView.findViewById(R.id.title_icon) as ImageView
        dialog.title = dialog.rootView.findViewById(R.id.title_text) as TextView
        dialog.spinner = dialog.rootView.findViewById(R.id.spinner) as CamomileSpinner
        dialog.message = dialog.rootView.findViewById(R.id.message) as TextView
        dialog.setCancelable(builder.cancelable)
        if (builder.isBackgroundColorSet) {
            val drawable = GradientDrawable()
            drawable.cornerRadius = builder.context.resources.getDimension(R.dimen.ios_bg_corner_radius)
            drawable.setColor(builder.backgroundColor)
            dialog.window!!.setBackgroundDrawable(drawable)
        } else {
            builder.backgroundColor =
                DialogUtils.getColor(builder.context, R.color.transparantGrey)
        }
        if (!builder.isTitleColorSet) {
            builder.titleColor = DialogUtils.getColor(builder.context, R.color.white)
        }
        if (!builder.isMessageColorSet) {
            builder.messageColor = DialogUtils.getColor(builder.context, R.color.white)
        }
        setupTitle(dialog, builder)
        setupSpinner(dialog, builder)
        setupMessageContent(dialog, builder)
        //setupListeners(dialog, builder)
        dialog.setContentView(dialog.rootView)
    }

    private fun setupTitle(dialog: IOSDialog?, builder: IOSDialog.Builder) {
        if (dialog!!.title != null) {
            dialog.title!!.setTextColor(builder.titleColor)
            dialog.title!!.gravity = builder.titleGravity
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                dialog.title!!.textAlignment = builder.titleGravity
            }
            if (builder.title == null) {
                dialog.titleFrame!!.visibility = View.GONE
            } else {
                dialog.title!!.text = builder.title
                dialog.titleFrame!!.visibility = View.VISIBLE
            }
        }
    }

    private fun setupSpinner(dialog: IOSDialog, builder: IOSDialog.Builder) {
        if (builder.spinnerColor == 0) builder.spinnerColor =
            CamomileSpinner.DEFAULT_COLOR
        if (builder.spinnerDuration == 0) builder.spinnerDuration =
            CamomileSpinner.DEFAULT_DURATION
        dialog.spinner!!.recreateWithParams(
            builder.context,
            builder.spinnerColor,
            builder.spinnerDuration,
            builder.spinnerClockwise
        )
    }

    private fun setupListeners(dialog: IOSDialog, builder: IOSDialog.Builder) {
        // Setup user listeners
        if (builder.showListener != null) {
            dialog.setOnShowListener(builder.showListener)
        }
        if (builder.cancelListener != null) {
            dialog.setOnCancelListener(builder.cancelListener)
        }
        if (builder.dismissListener != null) {
            dialog.setOnDismissListener(builder.dismissListener)
        }
        if (builder.keyListener != null) {
            dialog.setOnKeyListener(builder.keyListener)
        }
    }

    private fun setupMessageContent(dialog: IOSDialog, builder: IOSDialog.Builder) {
        if (dialog.message != null) {
            dialog.message!!.movementMethod = LinkMovementMethod()
            //            dialog.setTypeface(dialog.message, builder.regularFont);
            //            dialog.message.setLineSpacing(0f, builder.contentLineSpacingMultiplier);
            //            if (builder.linkColor == null) {
            //                dialog.message.setLinkTextColor(
            //                        DialogUtils.resolveColor(dialog.getContext(), android.R.attr.textColorPrimary));
            //            } else {
            //                dialog.message.setLinkTextColor(builder.linkColor);
            //            }
            dialog.message!!.setTextColor(builder.messageColor)
            dialog.message!!.gravity = builder.messageGravity
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                dialog.message!!.textAlignment = builder.messageGravity
            }
            if (builder.message != null) {
                dialog.message!!.text = builder.message
                dialog.message!!.visibility = View.VISIBLE
            } else {
                dialog.message!!.visibility = View.GONE
            }
        }
    }
}