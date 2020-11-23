package com.egbertwu.practiceapplication.ui.photo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.egbertwu.practiceapplication.GlideRequests
import com.egbertwu.practiceapplication.R
import com.egbertwu.practiceapplication.vo.PhotoVO

class PhotoAdapter(
        private val glideRequests: GlideRequests
) : PagingDataAdapter<PhotoVO, PhotoAdapter.PhotoViewHolder>(PhotoVO.diffCallback) {

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

    override fun onViewRecycled(holder: PhotoViewHolder) {
        super.onViewRecycled(holder)
        Log.d("PhotoAdapter", "onViewRecycled: ${holder.layoutPosition}")
        holder.parentView.setOnClickListener(null)
        glideRequests.clear(holder.imageView)
    }

    inner class PhotoViewHolder(itemVIew: View) : RecyclerView.ViewHolder(itemVIew) {
        val parentView = itemView
        val imageView = itemView.findViewById<ImageView>(R.id.iv_thumbnail)
        val id = itemView.findViewById<TextView>(R.id.tv_id)
        val title = itemView.findViewById<TextView>(R.id.tv_title)

        fun bind(photoVO: PhotoVO?) {
            photoVO?.let {
                id.text = it.mId.toString()
                title.text = it.mTitle
                glideRequests.asBitmap().load(it.mThumbnailUrl)
                        .transition(withCrossFade())
                        .into(imageView)
                parentView.setOnClickListener {
                    onItemClickListener?.invoke(photoVO)
                }
            }
        }
    }
}