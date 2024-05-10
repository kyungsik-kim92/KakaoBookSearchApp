package com.example.presenter.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.presenter.R
import com.example.presenter.adapter.FragmentPagerAdapter
import com.example.presenter.base.BaseFragment
import com.example.presenter.base.ViewEvent
import com.example.presenter.base.ViewState
import com.example.presenter.databinding.FragmentHomeBinding
import com.example.presenter.ui.bookmark.BookmarkFragment
import com.example.presenter.ui.search.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override val viewModel by viewModels<HomeViewModel>()

    private val tabConfigurationStrategy =
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.icon = resources.obtainTypedArray(R.array.array_main_tab_icon).getDrawable(position)
            tab.text = resources.getStringArray(R.array.array_main_tab_text)[position]
        }


    override fun initUi() {

        val list = listOf(
            SearchFragment(),
            BookmarkFragment(),

            )
        val pageAdapter = FragmentPagerAdapter(list, this)

        with(binding)
        {
            viewPager.adapter = pageAdapter
            TabLayoutMediator(tabLayout, viewPager, tabConfigurationStrategy).attach()
        }

    }

    override fun onChangedViewState(state: ViewState) {
        when (state) {
            is HomeViewState.RouteBookInfo -> {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToBookInfoFragment(state.item)
                findNavController().navigate(action)
            }
        }
    }

    override fun onChangeViewEvent(event: ViewEvent) {

    }


}