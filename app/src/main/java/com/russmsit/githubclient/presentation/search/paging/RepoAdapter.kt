package com.russmsit.githubclient.presentation.search.paging

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.russmsit.githubclient.R
import com.russmsit.githubclient.databinding.RepoLayoutBinding
import com.russmsit.githubclient.entities.RepoModel

class RepoAdapter(private val clickHandler: (model: RepoModel) -> Unit, diffCallback: DiffUtil.ItemCallback<RepoModel> = RepoComparator) :
    PagingDataAdapter<RepoModel, RepoAdapter.RepoViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepoViewHolder {
        return RepoViewHolder(
            RepoLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    private fun onItemClick(model: RepoModel) = clickHandler(model)

    @SuppressLint("SetTextI18n")
    inner class RepoViewHolder(private val binding: RepoLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RepoModel?) {
            with(binding) {
                if (item == null) progressBar.visibility = View.VISIBLE

                item?.let {
                    root.setOnClickListener {
                        onItemClick(item)
                    }

                    progressBar.visibility = View.GONE
                    repoDescription.text = item.description
                    repoTitle.text = "${item.userName}/${item.name}"
                    ownerAvatar.load(item.userAvatarUrl) {
                        transformations(CircleCropTransformation())
                        crossfade(true)
                        placeholder(ColorDrawable(ContextCompat.getColor(binding.root.context, R.color.green_200)))
                    }
                }
            }
        }
    }

    object RepoComparator : DiffUtil.ItemCallback<RepoModel>() {
        override fun areItemsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean {
            return "${oldItem.userName}/${oldItem.name}" == "${oldItem.userName}/${oldItem.name}"
        }

        override fun areContentsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean {
            return oldItem == newItem
        }
    }
}