package com.example.presenter.adapter


import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.KakaoBook
import com.example.presenter.databinding.ItemSearchBinding
import com.google.android.material.snackbar.Snackbar

class SearchBookAdapter(
    val onItemClick: (KakaoBook) -> Unit,
    val onBookmarkInsertClick: (KakaoBook) -> Unit,
    val onBookmarkDeleteClick: (KakaoBook) -> Unit,
) :
    ListAdapter<KakaoBook, SearchBookAdapter.SearchViewHolder>(SearchDiffCallback()) {
    private val kakaoBookList = mutableListOf<KakaoBook>()

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
            onBookmarkDeleteClick: (KakaoBook) -> Unit,

            ) {
            binding.bookItem = kakaoBook
            binding.author.text = kakaoBook.authors.toString()
            binding.bookmark.isChecked = statusCheckBox[adapterPosition]

            binding.bookmark.setOnClickListener {

                if (!binding.bookmark.isChecked) {
                    onBookmarkDeleteClick(kakaoBook)
                    statusCheckBox.put(adapterPosition, true)
                    Snackbar.make(binding.root, "북마크가 해제 되었습니다.", Snackbar.LENGTH_SHORT).show()


                } else {
                    onBookmarkInsertClick(kakaoBook)
                    statusCheckBox.put(adapterPosition, false)
                    Snackbar.make(binding.root, "북마크에 추가 되었습니다.", Snackbar.LENGTH_SHORT).show()

                }
            }
            itemView.setOnClickListener {
                onItemClick(kakaoBook)

            }

        }

    }


//    fun addBookmark(item: KakaoBook) {
//        if (kakaoBookList.contains(item)) {
//            val position = kakaoBookList.indexOf(item)
//            kakaoBookList[position].isBookmark = true
//            submitList(kakaoBookList)
//        }
//    }
//
//    fun deleteBookmark(item: KakaoBook) {
//        if (kakaoBookList.contains(item)) {
//            val position = kakaoBookList.indexOf(item)
//            kakaoBookList[position].isBookmark = false
//            submitList(kakaoBookList)
//        }
//    }
}


class SearchDiffCallback : DiffUtil.ItemCallback<KakaoBook>() {
    override fun areItemsTheSame(oldItem: KakaoBook, newItem: KakaoBook): Boolean {
        return oldItem.isbn == newItem.isbn
    }

    override fun areContentsTheSame(oldItem: KakaoBook, newItem: KakaoBook): Boolean {
        return oldItem == newItem
    }
}