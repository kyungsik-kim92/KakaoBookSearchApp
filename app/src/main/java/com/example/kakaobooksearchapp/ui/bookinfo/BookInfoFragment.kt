package com.example.kakaobooksearchapp.ui.bookinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kakaobooksearchapp.databinding.FragmentBookinfoBinding
import com.example.kakaobooksearchapp.databinding.FragmentBookmarkBinding

class BookInfoFragment : Fragment() {
    private lateinit var binding: FragmentBookinfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookinfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}