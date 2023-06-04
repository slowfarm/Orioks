package ru.eva.oriokslive.ui.activity.news

import android.view.LayoutInflater
import android.webkit.WebView
import android.webkit.WebViewClient
import ru.eva.oriokslive.databinding.ActivityNewsBinding
import ru.eva.oriokslive.ui.base.BaseActivity
import ru.eva.oriokslive.ui.fragment.news.NewsFragment.Companion.EXTRA_URL

class NewsActivity : BaseActivity<ActivityNewsBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityNewsBinding =
        ActivityNewsBinding::inflate

    override fun setupUI() {
        intent.getStringExtra(EXTRA_URL)?.let {
            binding.webView.apply {
                loadUrl(it)
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView, url: String) {
                        supportActionBar?.setDisplayHomeAsUpEnabled(true)
                        supportActionBar?.title = view.title
                        supportActionBar?.subtitle = view.url
                    }
                }
            }
        }
    }
}