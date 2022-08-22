package com.example.githubreader.ui.feature.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubreader.databinding.ItemFavoriteBinding
import com.example.githubreader.model.UserPresentationModel

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val items = mutableListOf<UserPresentationModel>()
    private lateinit var deleteItemClickListener: (UserPresentationModel) -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(
        items: List<UserPresentationModel>,
        deleteItemClickListener: (UserPresentationModel) -> Unit
    ) {
        this.items.clear()
        this.items.addAll(items)
        this.deleteItemClickListener = deleteItemClickListener
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(
        private val binding: ItemFavoriteBinding,
        private val deleteItemClickListener: (UserPresentationModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(repo: UserPresentationModel) = with(binding) {
            repoName.text = repo.name
            repoUrl.text = repo.url

            binding.ivDelete.setOnClickListener {
                deleteItemClickListener(repo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoriteViewHolder(ItemFavoriteBinding.inflate(layoutInflater), deleteItemClickListener)    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}