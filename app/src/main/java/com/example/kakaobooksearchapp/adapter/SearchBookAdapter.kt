package com.example.kakaobooksearchapp.adapter


import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaobooksearchapp.databinding.ItemSearchBinding
import com.example.kakaobooksearchapp.network.response.KakaoBookItem
import com.google.android.material.snackbar.Snackbar

class SearchBookAdapter(
    val onBookmarkInsertClick: (KakaoBookItem) -> Unit,
    val onBookmarkDeleteClick: (KakaoBookItem) -> Unit,
) :
    ListAdapter<KakaoBookItem, SearchBookAdapter.SearchViewHolder>(SearchDiffCallback()) {
    private val kakaoBookList = mutableListOf<KakaoBookItem>()

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
        holder.bind(getItem(position), onBookmarkInsertClick, onBookmarkDeleteClick)
    }

    class SearchViewHolder(
        private val binding: ItemSearchBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private val statusCheckBox = SparseBooleanArray()
        fun bind(
            kakaoBookItem: KakaoBookItem,
            onBookMarkInsertClick: (KakaoBookItem) -> Unit,
            onBookMarkDeleteClick: (KakaoBookItem) -> Unit
        ) {
            binding.bookItem = kakaoBookItem
            binding.author.text = kakaoBookItem.authors.toString()
            binding.bookmark.isChecked = statusCheckBox[adapterPosition]
            binding.bookmark.setOnClickListener {

                if (!binding.bookmark.isChecked) {
                    onBookMarkDeleteClick(kakaoBookItem)
                    statusCheckBox.put(adapterPosition, true)
                    Snackbar.make(binding.root, "북마크가 해제 되었습니다.", Snackbar.LENGTH_SHORT).show()


                } else {
                    onBookMarkInsertClick(kakaoBookItem)
                    statusCheckBox.put(adapterPosition, false)
                    Snackbar.make(binding.root, "북마크에 추가 되었습니다.", Snackbar.LENGTH_SHORT).show()

                }
            }


        }

    }

    fun addBookmark(item: KakaoBookItem) {
        if (kakaoBookList.contains(item)) {
            val position = kakaoBookList.indexOf(item)
            kakaoBookList[position].isBookmark = true
            submitList(kakaoBookList)
        }
    }

    fun deleteBookmark(item: KakaoBookItem) {
        if (kakaoBookList.contains(item)) {
            val position = kakaoBookList.indexOf(item)
            kakaoBookList[position].isBookmark = false
            submitList(kakaoBookList)
        }
    }
}


class SearchDiffCallback : DiffUtil.ItemCallback<KakaoBookItem>() {
    override fun areItemsTheSame(oldItem: KakaoBookItem, newItem: KakaoBookItem): Boolean {
        return oldItem.isbn == newItem.isbn
    }

    override fun areContentsTheSame(oldItem: KakaoBookItem, newItem: KakaoBookItem): Boolean {
        return oldItem == newItem
    }
}