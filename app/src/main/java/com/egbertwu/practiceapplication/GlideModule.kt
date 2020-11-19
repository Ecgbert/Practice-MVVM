package com.egbertwu.practiceapplication

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.InputStream

@GlideModule
class GlideModule : AppGlideModule(), KoinComponent {
    private val httpClient: OkHttpClient by inject()

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        val factory = OkHttpUrlLoader.Factory(httpClient)
        glide.registry.replace(GlideUrl::class.java, InputStream::class.java, factory)
    }
}