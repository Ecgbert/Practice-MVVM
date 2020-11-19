package com.egbertwu.practiceapplication.ui.photo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egbertwu.practiceapplication.R
import com.egbertwu.practiceapplication.vo.PhotoVO

class PhotoAdapter(
    private val lifecycleCoroutineScope: LifecycleCoroutineScope
) :
    PagingDataAdapter<PhotoVO, PhotoAdapter.PhotoViewHolder>(PhotoVO.diffCallback) {

    private var onItemClickListener: ((PhotoVO) -> Unit)? = null

    fun setOnItemClickListener(listener: (PhotoVO) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo_lsit, parent, false)
        )

    inner class PhotoViewHolder(itemVIew: View) :
        RecyclerView.ViewHolder(itemVIew) {
        private val parentView = itemView
        private val imageView = itemView.findViewById<ImageView>(R.id.iv_thumbnail)
        private val id = itemView.findViewById<TextView>(R.id.tv_id)
        private val title = itemView.findViewById<TextView>(R.id.tv_title)

        fun bind(photoVO: PhotoVO?) {
            photoVO?.let {
                id.text = it.mId.toString()
                title.text = it.mTitle
                Glide.with(imageView).asBitmap().load(it.mThumbnailUrl).into(imageView)
                parentView.setOnClickListener {
                    onItemClickListener?.invoke(photoVO)
                }
            }
        }
    }
}