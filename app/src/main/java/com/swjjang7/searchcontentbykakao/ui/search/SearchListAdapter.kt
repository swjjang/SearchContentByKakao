package com.swjjang7.searchcontentbykakao.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.swjjang7.searchcontentbykakao.R
import com.swjjang7.searchcontentbykakao.domain.model.Content
import com.swjjang7.searchcontentbykakao.domain.model.ContentType
import com.swjjang7.searchcontentbykakao.ui.base.BaseListAdapter

class SearchListAdapter(
    viewModel: SearchViewModel
) : BaseListAdapter<Content>(viewModel, DiffCallback()) {
    private class DiffCallback : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem.type == newItem.type && oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(
            oldItem: Content,
            newItem: Content
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            ContentType.HEADER -> R.layout.list_item_header
            ContentType.IMAGE -> R.layout.list_item_content
            ContentType.VIDEO -> R.layout.list_item_content
        }
    }
}