package com.swjjang7.searchcontentbykakao.ui.favorite

import androidx.fragment.app.activityViewModels
import com.swjjang7.searchcontentbykakao.R
import com.swjjang7.searchcontentbykakao.databinding.FragmentFavoriteBinding
import com.swjjang7.searchcontentbykakao.domain.model.FavoriteContent
import com.swjjang7.searchcontentbykakao.ui.base.BaseFragment
import com.swjjang7.searchcontentbykakao.ui.base.ShareViewModel
import com.swjjang7.searchcontentbykakao.ui.extension.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FavoriteListFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(
    R.layout.fragment_favorite,
    FavoriteViewModel::class.java
) {
    companion object {
        fun newInstance() = FavoriteListFragment()
    }

    private val shareViewModel: ShareViewModel by activityViewModels()

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
        repeatOnStarted {
            shareViewModel.share.collectLatest {
                (it as? List<FavoriteContent>)?.let { list ->
                    viewModel.updateFavoriteContents(list)
                }
            }
        }

        repeatOnStarted {
            viewModel.favoriteUpdate.collectLatest {
                shareViewModel.invokeUpdateNotify()
            }
        }
    }
}