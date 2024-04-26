package com.example.kakaobooksearchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaobooksearchapp.databinding.ItemBookmarkBinding
import com.example.kakaobooksearchapp.room.BookMarkItem

class BookmarkAdapter :
    ListAdapter<BookMarkItem, BookmarkAdapter.BookmarkViewHolder>(BookmarkDiffCallback()) {


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

        fun bind(bookItem: BookMarkItem) {
            binding.bookItem = bookItem
            binding.author.text = bookItem.authors.toString()
        }

    }

}


class BookmarkDiffCallback : DiffUtil.ItemCallback<BookMarkItem>() {
    override fun areItemsTheSame(oldItem: BookMarkItem, newItem: BookMarkItem): Boolean {
        return oldItem.isbn == newItem.isbn
    }

    override fun areContentsTheSame(oldItem: BookMarkItem, newItem: BookMarkItem): Boolean {
        return oldItem == newItem
    }

}