package com.example.ministockbitapp.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.ministockbitapp.R
import com.kennyc.view.MultiStateView

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun emptyString() = ""

fun MultiStateView.showDefaultState() {
    this.viewState = MultiStateView.ViewState.CONTENT
}

fun MultiStateView.showEmptyState() {
    this.viewState = MultiStateView.ViewState.EMPTY
}

fun MultiStateView.showLoadingState() {
    this.viewState = MultiStateView.ViewState.LOADING
}

fun MultiStateView.showErrorState(
    errorMessage: String? = null,
    title: String? = null,
    drawable: Drawable? = null,
    errorAction: (() -> Unit)? = null
) {
    this.viewState = MultiStateView.ViewState.ERROR

    errorMessage?.let {
        val tvError =
            this.getView(MultiStateView.ViewState.ERROR)?.findViewById<TextView>(R.id.tv_error)
        tvError?.text = errorMessage
    }

    title?.let {
        val tvTitle =
            this.getView(MultiStateView.ViewState.ERROR)?.findViewById<TextView>(R.id.tv_title)
        tvTitle?.text = title
    }

    drawable?.let {
        val imgError =
            this.getView(MultiStateView.ViewState.ERROR)?.findViewById<ImageView>(R.id.img_error)
        imgError?.setImageDrawable(drawable)
    }

    val btnError =
        this.getView(MultiStateView.ViewState.ERROR)?.findViewById<Button>(R.id.btn_error)

    btnError?.setOnClickListener { errorAction?.invoke() }
}

fun trimTrailingZero(value: String?): String? {
    return if (!value.isNullOrEmpty()) {
        if (value.indexOf(".") < 0) {
            value

        } else {
            value.replace("0*$".toRegex(), "").replace("\\.$".toRegex(), "")
        }

    } else {
        value
    }
}

fun Context.showWhiteAlertDialog(
        title: String? = null,
        message: String? = null,
        positiveButton: Pair<String, () -> Unit>? = null,
        negativeButton: Pair<String, () -> Unit>? = null
) {

    val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                    this,
                    android.R.style.Theme_Material_Light_Dialog_Alert
            )
    )

    builder.apply {
        if (title != null) setTitle(title)

        if (message != null) setMessage(message)

        if (negativeButton != null) {
            setNegativeButton(
                    negativeButton.first
            ) { _, _ ->
                negativeButton.second.invoke()
            }
        }

        if (positiveButton != null) {
            setPositiveButton(
                    positiveButton.first
            ) { _, _ ->
                positiveButton.second.invoke()
            }
        }
        setCancelable(false)
    }

    val dialog = builder.create()
    dialog.show()

    if (negativeButton != null) {
        val buttonNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        buttonNegative.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        buttonNegative.setTextColor(ContextCompat.getColor(this, R.color.grey))
    }

    if (positiveButton != null) {
        val buttonPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        buttonPositive.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.green))
    }
}