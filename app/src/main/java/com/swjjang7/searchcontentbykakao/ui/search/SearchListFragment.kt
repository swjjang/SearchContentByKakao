package com.swjjang7.searchcontentbykakao.ui.search

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.swjjang7.searchcontentbykakao.R
import com.swjjang7.searchcontentbykakao.databinding.FragmentSearchBinding
import com.swjjang7.searchcontentbykakao.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchListFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(
    R.layout.fragment_search,
    SearchViewModel::class.java
) {
    companion object {
        fun newInstance() = SearchListFragment()
    }

    private val listAdapter by lazy {
        SearchListAdapter(viewModel)
    }

    override fun initBind(binding: FragmentSearchBinding, viewModel: SearchViewModel) {
        with(binding) {
            adapter = listAdapter
        }
    }

    override fun initLayout(binding: FragmentSearchBinding) {
        with(binding.recyclerView) {
            itemAnimator = null
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    (layoutManager as? LinearLayoutManager)?.run {
                        if (dy == 0) {
                            return
                        }

                        val lastVisiblePosition = findLastVisibleItemPosition()
                        if (viewModel.nextPageLoadable(lastVisiblePosition)) {
                            viewModel.fetchNextPage()
                        }
                    }
                }
            })
        }
    }

    override fun initViewModel(binding: FragmentSearchBinding, viewModel: SearchViewModel) {
//        TODO("Not yet implemented")
    }
}