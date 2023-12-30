package com.swjjang7.searchcontentbykakao.ui.base

import androidx.core.view.doOnAttach
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.swjjang7.searchcontentbykakao.BR

class BaseViewHolder<T>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
        itemView.doOnAttach {
            binding.lifecycleOwner = itemView.findViewTreeLifecycleOwner()
        }
    }

    fun bind(position: Int, item: T, viewModel: ViewModel?) {
        with(binding) {
            setVariable(BR.position, position)
            setVariable(BR.item, item)
            setVariable(BR.vm, viewModel)
            executePendingBindings()
        }
    }
}