package com.example.kakaobooksearchapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kakaobooksearchapp.adapter.FragmentPagerAdapter
import com.example.kakaobooksearchapp.databinding.FragmentHomeBinding
import com.example.kakaobooksearchapp.network.response.KakaoBookItem
import com.example.kakaobooksearchapp.ui.bookmark.BookmarkFragment
import com.example.kakaobooksearchapp.ui.search.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel by viewModels<HomeViewModel>()


    private val tabConfigurationStrategy =
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.icon = resources.obtainTypedArray(R.array.array_main_tab_icon).getDrawable(position)
            tab.text = resources.getStringArray(R.array.array_main_tab_text)[position]
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
//        homeViewModel.routeBookItem.observe(viewLifecycleOwner) { routeBookInfo ->
//            val action = HomeFragmentDirections.actionHomeFragmentToBookInfoFragment(routeBookInfo)
//            findNavController().navigate(action)
//
//        }
        homeViewModel.viewStateLiveData.observe(viewLifecycleOwner){
            onChangedViewState(it)
        }


    }
    fun onChangedViewState(viewState: HomeViewState) {
        when(viewState){
            is HomeViewState.RouteBookInfo -> {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToBookInfoFragment(viewState.item)
                findNavController().navigate(action)
            }
        }
    }

}