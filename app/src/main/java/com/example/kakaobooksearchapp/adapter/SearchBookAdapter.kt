package com.example.kakaobooksearchapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaobooksearchapp.databinding.ItemSearchBinding
import com.example.kakaopractice.network.response.KakaoBookItem

class SearchBookAdapter :
    ListAdapter<KakaoBookItem, SearchBookAdapter.SearchViewHolder>(SearchDiffCallback()) {


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
        holder.bind(getItem(position))
    }

    class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: KakaoBookItem) {

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