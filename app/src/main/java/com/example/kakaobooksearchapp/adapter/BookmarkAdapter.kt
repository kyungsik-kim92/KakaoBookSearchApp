package com.example.kakaobooksearchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaobooksearchapp.databinding.ItemBookmarkBinding
import com.example.data.api.response.BookmarkItem
import com.example.domain.model.KakaoBookmark

class BookmarkAdapter :
    ListAdapter<KakaoBookmark, BookmarkAdapter.BookmarkViewHolder>(BookmarkDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkAdapter.BookmarkViewHolder(
            ItemBookmarkBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookmarkViewHolder(private val binding: ItemBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bookItem: KakaoBookmark) {
            binding.bookItem = bookItem
            binding.author.text = bookItem.authors.toString()
        }

    }

}


class BookmarkDiffCallback : DiffUtil.ItemCallback<KakaoBookmark>() {
    override fun areItemsTheSame(oldItem: KakaoBookmark, newItem: KakaoBookmark): Boolean {
        return oldItem.isbn == newItem.isbn
    }

    override fun areContentsTheSame(oldItem: KakaoBookmark, newItem: KakaoBookmark): Boolean {
        return oldItem == newItem
    }

}