package com.example.kakaobooksearchapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kakaobooksearchapp.adapter.FragmentPagerAdapter
import com.example.kakaobooksearchapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val tabConfigurationStrategy =
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.icon = resources.obtainTypedArray(R.array.array_main_tab_icon).getDrawable(position)
            tab.text = resources.getStringArray(R.array.array_main_tab_text)[position]
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

    }


}