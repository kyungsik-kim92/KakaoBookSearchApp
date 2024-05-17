package com.example.presenter.adapter


import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.KakaoBook
import com.example.presenter.databinding.ItemSearchBinding
import com.example.presenter.ui.search.component.KakaoSearchItem

class SearchBookAdapter(
    val onItemClick: (KakaoBook) -> Unit,
    val onBookmarkInsertClick: (KakaoBook) -> Unit,
    val onBookmarkDeleteClick: (KakaoBook) -> Unit,
) : ListAdapter<KakaoBook, SearchBookAdapter.SearchViewHolder>(SearchDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick, onBookmarkInsertClick, onBookmarkDeleteClick)
    }

    class SearchViewHolder(
        private val binding: ItemSearchBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private val statusCheckBox = SparseBooleanArray()
        fun bind(
            kakaoBook: KakaoBook,
            onItemClick: (KakaoBook) -> Unit,
            onBookmarkInsertClick: (KakaoBook) -> Unit,
            onBookmarkDeleteClick: (KakaoBook) -> Unit

        ) {

            binding.search.setContent {
                KakaoSearchItem(
                    item = kakaoBook,
                    onClick = onItemClick,
                    onDeleteBookmark = onBookmarkDeleteClick,
                    onInsertBookmark = onBookmarkInsertClick
                )
            }

        }

    }


    class SearchDiffCallback : DiffUtil.ItemCallback<KakaoBook>() {
        override fun areItemsTheSame(oldItem: KakaoBook, newItem: KakaoBook): Boolean {
            return oldItem.isbn == newItem.isbn
        }

        override fun areContentsTheSame(oldItem: KakaoBook, newItem: KakaoBook): Boolean {
            return oldItem == newItem
        }
    }
}