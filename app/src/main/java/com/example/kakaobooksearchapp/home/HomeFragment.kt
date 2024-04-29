package com.example.kakaobooksearchapp.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kakaobooksearchapp.R
import com.example.kakaobooksearchapp.adapter.FragmentPagerAdapter
import com.example.kakaobooksearchapp.base.BaseFragment
import com.example.kakaobooksearchapp.base.ViewEvent
import com.example.kakaobooksearchapp.base.ViewState
import com.example.kakaobooksearchapp.databinding.FragmentHomeBinding
import com.example.kakaobooksearchapp.ui.bookmark.BookmarkFragment
import com.example.kakaobooksearchapp.ui.search.SearchFragment
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
        when(state){
            is HomeViewState.RouteBookInfo -> {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToBookInfoFragment(state.item)
                findNavController().navigate(action)
            }
        }
    }

    override fun onChangeViewEvent(event: ViewEvent) {
        TODO("Not yet implemented")
    }




}