package com.swjjang7.searchcontentbykakao.ui.favorite

import com.swjjang7.searchcontentbykakao.R
import com.swjjang7.searchcontentbykakao.databinding.FragmentFavoriteBinding
import com.swjjang7.searchcontentbykakao.ui.base.BaseFragment

class FavoriteListFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(
    R.layout.fragment_favorite,
    FavoriteViewModel::class.java
) {
    companion object {
        fun newInstance() = FavoriteListFragment()
    }

    private val listAdapter by lazy {
        FavoriteListAdapter(viewModel)
    }

    override fun initBind(binding: FragmentFavoriteBinding, viewModel: FavoriteViewModel) {
        with(binding) {
            adapter = listAdapter
        }
    }

    override fun initLayout(binding: FragmentFavoriteBinding) {
    }

    override fun initViewModel(binding: FragmentFavoriteBinding, viewModel: FavoriteViewModel) {
    }
}