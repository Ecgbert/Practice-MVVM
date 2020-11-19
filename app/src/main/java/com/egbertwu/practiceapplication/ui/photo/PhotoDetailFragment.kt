package com.egbertwu.practiceapplication.ui.photo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.egbertwu.practiceapplication.R
import kotlinx.android.synthetic.main.fragment_photo_detail.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoDetailFragment : Fragment(R.layout.fragment_photo_detail) {

    private val viewModel by viewModel<PhotoViewModel>()
    private val args: PhotoDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(args.photo) {
            tv_id_detail.text = String.format("id: %s", this.mId)
            tv_title_detail.text = String.format("id: %s", this.mTitle)
        }
        /*lifecycleScope.launch {
            viewModel.loadBitmap(args.photo.mUrl)?.let {
                iv_photo_original.setImageBitmap(it)
            }
        }*/
    }
}