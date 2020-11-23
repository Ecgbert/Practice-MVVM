package com.egbertwu.practiceapplication.ui.photo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.egbertwu.practiceapplication.GlideApp
import com.egbertwu.practiceapplication.GlideRequest
import com.egbertwu.practiceapplication.R
import com.egbertwu.practiceapplication.util.EventObserver
import kotlinx.android.synthetic.main.fragment_photo.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent


class PhotoFragment : Fragment(R.layout.fragment_photo), KoinComponent {
    private val glideRequests by lazy { GlideApp.with(this) }
    private val photoAdapter by lazy { PhotoAdapter(glideRequests) }
    private val viewModel: PhotoViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_photo_retry.setOnClickListener { photoAdapter.retry() }
        val gridLayoutManager = GridLayoutManager(requireContext(), 4).apply {
            orientation = LinearLayoutManager.VERTICAL
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == photoAdapter.itemCount) {
                        4
                    } else {
                        1
                    }
                }
            }
        }

        rv_photos.apply {
            adapter = photoAdapter.withLoadStateFooter(PhotoLoadStateAdapter {
                photoAdapter.retry()
            })
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
        }

        photoAdapter.addLoadStateListener { loadState ->
            rv_photos.isVisible = loadState.source.refresh is LoadState.NotLoading
            photo_progress_bar.isVisible = loadState.source.refresh is LoadState.Loading
            btn_photo_retry.isVisible = loadState.source.refresh is LoadState.Error
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
            }
        }

        photoAdapter.setOnItemClickListener {
            val args = PhotoFragmentDirections.actionPhotoFragmentToPhotoDetailFragment(it)
            view.findNavController().navigate(args)
        }

        viewModel.photoLiveData.observe(viewLifecycleOwner, Observer {
            photoAdapter.submitData(lifecycle, it)
        })
    }
}