/*
package com.egbertwu.practiceapplication.ui.photo

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.egbertwu.practiceapplication.R

@EpoxyModelClass
abstract class PhotosModel : EpoxyModelWithHolder<PhotosHolder>() {

    @EpoxyAttribute
    var imageUrl = ""

    @EpoxyAttribute
    var imageId = ""

    @EpoxyAttribute
    var title = ""

    @EpoxyAttribute
    var loading = false

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener:View.OnClickListener? = null

    override fun bind(holder: PhotosHolder) {
        super.bind(holder)
        Glide.with(holder.image).load(imageUrl).into(holder.image)
        holder.itemView.setOnClickListener(clickListener)
    }

    override fun unbind(holder: PhotosHolder) {
        super.unbind(holder)
        holder.itemView.setOnClickListener(null)
    }

    override fun getDefaultLayout() = R.layout.item_photo_lsit
}

class PhotosHolder : EpoxyHolder() {
    lateinit var itemView: View
    lateinit var image: ImageView
    lateinit var textId: TextView
    lateinit var textTitle: TextView
    lateinit var progress: ProgressBar

    override fun bindView(itemView: View) {
        this.itemView = itemView
        image = itemView.findViewById(R.id.iv_thumbnail)
        textId = itemView.findViewById(R.id.tv_id)
        textTitle = itemView.findViewById(R.id.tv_title)
    }

}*/
