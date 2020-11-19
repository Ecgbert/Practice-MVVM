package com.egbertwu.practiceapplication.vo

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoVO(
    val mId: Int,
    val mTitle: String,
    val mUrl: String,
    val mThumbnailUrl: String
) : Parcelable {
    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<PhotoVO>() {
            override fun areItemsTheSame(oldItem: PhotoVO, newItem: PhotoVO): Boolean =
                oldItem.mId == newItem.mId

            override fun areContentsTheSame(
                oldItem: PhotoVO,
                newItem: PhotoVO
            ): Boolean =
                oldItem == newItem
        }
    }


}