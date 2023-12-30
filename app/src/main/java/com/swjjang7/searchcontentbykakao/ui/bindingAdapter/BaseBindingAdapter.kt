package com.swjjang7.searchcontentbykakao.ui.bindingAdapter

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.swjjang7.searchcontentbykakao.R
import com.swjjang7.searchcontentbykakao.ui.extension.dp
import java.text.SimpleDateFormat

@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .placeholder(R.drawable.baseline_downloading_50)
        .error(R.drawable.baseline_error_250)
        .into(view)
}

@BindingAdapter("roundCorner")
fun bindRoundCorner(
    view: ShapeableImageView,
    round: Double = 0.0,
) {
    val value = round.dp.toFloat()
    view.shapeAppearanceModel = view.shapeAppearanceModel
        .toBuilder()
        .setTopLeftCornerSize(value)
        .setTopRightCornerSize(value)
        .setBottomLeftCornerSize(value)
        .setBottomRightCornerSize(value)
        .build()
}

@BindingAdapter("submitList")
fun bindSubmitList(view: RecyclerView, list: List<Any>?) {
    (view.adapter as? ListAdapter<Any, *>)?.submitList(list)
}

@BindingAdapter("date")
fun bindDateFormat(view: TextView, dateString: String) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    val date = try {
        inputFormat.parse(dateString)
    } catch (e: Exception) {
        view.text = dateString
        return
    }

    val outputFormat = SimpleDateFormat("yyyy년 MM월 dd일\nHH시 mm분 ss초 SSS")
    view.text = outputFormat.format(date)
}