package com.egbertwu.practiceapplication.ui.photo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.egbertwu.practiceapplication.R

class PhotoLoadStateViewHolder(itemVIew: View, retry: () -> Unit) :
    RecyclerView.ViewHolder(itemVIew) {

    private val retryButton = itemView.findViewById<Button>(R.id.btn_footer_retry)
    private val progressBar = itemView.findViewById<ProgressBar>(R.id.footer_progress_bar)
    private val errorMsg = itemView.findViewById<TextView>(R.id.footer_error_msg)

    init {
        retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            errorMsg.text = loadState.error.localizedMessage
        }
        progressBar.isVisible = loadState is LoadState.Loading
        retryButton.isVisible = loadState !is LoadState.Loading
        errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PhotoLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_load_state_footer, parent, false)
            return PhotoLoadStateViewHolder(view, retry)
        }
    }
}