package com.swjjang7.searchcontentbykakao.ui.favorite

import androidx.recyclerview.widget.DiffUtil
import com.swjjang7.searchcontentbykakao.R
import com.swjjang7.searchcontentbykakao.domain.model.FavoriteContent
import com.swjjang7.searchcontentbykakao.ui.base.BaseListAdapter

class FavoriteListAdapter(
    viewModel: FavoriteViewModel
) : BaseListAdapter<FavoriteContent>(viewModel, DiffCallback()) {
    private class DiffCallback : DiffUtil.ItemCallback<FavoriteContent>() {
        override fun areItemsTheSame(oldItem: FavoriteContent, newItem: FavoriteContent): Boolean {
            return oldItem.type == newItem.type && oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(
            oldItem: FavoriteContent,
            newItem: FavoriteContent
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.list_item_favorite_content
    }
}