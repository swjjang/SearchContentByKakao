package com.swjjang7.searchcontentbykakao.ui.base

import android.app.Dialog
import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar

class LoadingDialog(private val context: Context) {

    private val mDialog: Dialog by lazy {
        Dialog(context).apply {
            setContentView(
                ProgressBar(
                    context,
                    null,
                    android.R.attr.progressBarStyleLarge
                ).apply {
                    isIndeterminate = true
                    visibility = View.VISIBLE
                }
            )

            setCancelable(false)
            window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setOnCancelListener {}

            setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP && !event.isCanceled) {
                }

                false
            }
        }
    }

    fun show() {
        if (!mDialog.isShowing) {
            mDialog.show()
        }
    }

    fun dismiss() {
        if (mDialog.isShowing) {
            mDialog.dismiss()
        }
    }

    fun isShow(): Boolean = mDialog.isShowing

}