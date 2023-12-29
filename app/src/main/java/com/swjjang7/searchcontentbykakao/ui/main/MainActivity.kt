package com.swjjang7.searchcontentbykakao.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.swjjang7.searchcontentbykakao.BR
import com.swjjang7.searchcontentbykakao.R
import com.swjjang7.searchcontentbykakao.databinding.ActivityMainBinding
import com.swjjang7.searchcontentbykakao.ui.favorite.FavoriteListFragment
import com.swjjang7.searchcontentbykakao.ui.search.SearchListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private val fragmentList = listOf(
        SearchListFragment.newInstance(),
        FavoriteListFragment.newInstance(),
    )
    private val listAdapter by lazy {
        MainPageAdapter(this, fragmentList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).also {
            it.lifecycleOwner = this
            it.setVariable(BR.vm, viewModel)
            it.setVariable(BR.adapter, listAdapter)
        }

        setContentView(binding.root)
        initLayout(binding)
    }

    private fun initLayout(binding: ActivityMainBinding) {
        with(binding) {
            viewPager.adapter = listAdapter

            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = getTabName(position)
            }.attach()
        }
    }

    private fun initViewModel(viewModel: MainViewModel, binding: ActivityMainBinding) {

    }

    private fun getTabName(position: Int): String {
        return when (position) {
            0 -> getString(R.string.tab_search)
            1 -> getString(R.string.tab_favorite)
            else -> "Unknown"
        }
    }
}