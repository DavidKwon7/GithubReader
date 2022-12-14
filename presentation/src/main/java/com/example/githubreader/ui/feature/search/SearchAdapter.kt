package com.example.githubreader.ui.feature.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubreader.databinding.ItemSearchBinding
import com.example.githubreader.model.UserPresentationModel

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val items = mutableListOf<UserPresentationModel>()
    private lateinit var itemClickListener: (UserPresentationModel) -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<UserPresentationModel>, itemClickListener: (UserPresentationModel) -> Unit) {
        this.items.clear()
        this.items.addAll(items)
        this.itemClickListener = itemClickListener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SearchViewHolder(ItemSearchBinding.inflate(layoutInflater), itemClickListener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class SearchViewHolder(
        private val binding:ItemSearchBinding,
        val itemClickListener: (UserPresentationModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: UserPresentationModel) = with(binding) {
            repoName.text = repo.name
            repoUrl.text = repo.url

            binding.root.setOnClickListener {
                itemClickListener(repo)
            }
        }
    }
}