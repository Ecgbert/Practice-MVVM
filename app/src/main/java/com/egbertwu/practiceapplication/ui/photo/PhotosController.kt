/*
package com.egbertwu.practiceapplication.ui.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagedListEpoxyController
import com.egbertwu.practiceapplication.util.Event
import com.egbertwu.practiceapplication.vo.PhotoVO

class PhotosController : PagedListEpoxyController<PhotoVO>() {

    private val _clickEvent = MutableLiveData<Event<PhotoVO>>()
    val clickEvent: LiveData<Event<PhotoVO>> get() = _clickEvent

    override fun buildItemModel(currentPosition: Int, item: PhotoVO?): EpoxyModel<*> {
        return if (item == null) {
            PhotosModel_().id("$currentPosition")
        } else {
            PhotosModel_().id("photo_item_$currentPosition")
                .imageId(item.mId.toString())
                .imageUrl(item.mThumbnailUrl)
                .clickListener {
                    _clickEvent.value = Event(item)
                }
        }
    }
}*/
