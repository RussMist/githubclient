package com.russmsit.githubclient.presentation.detailed

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.russmsit.githubclient.databinding.IssueLayoutBinding
import com.russmsit.githubclient.entities.IssueModel

class IssuesAdapter(private val data: List<IssueModel>) :
    RecyclerView.Adapter<IssuesAdapter.IssueViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IssueViewHolder {
        return IssueViewHolder(
            IssueLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(data[position])
    }


    @SuppressLint("SetTextI18n")
    inner class IssueViewHolder(private val binding: IssueLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: IssueModel?) {
            with(binding) {
                item?.let {
                    issueId.text = "#${item.id}"
                    issueTitle.text = item.name
                    issueStatus.text = if (item.isClosed) "Closed" else "Open"
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size
}