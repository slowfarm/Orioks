package ru.eva.oriokslive.ui.activity.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import ru.eva.oriokslive.databinding.ActivityNewsBinding
import ru.eva.oriokslive.ui.base.BaseActivity
import ru.eva.oriokslive.ui.fragment.news.NewsFragment.Companion.EXTRA_URL

class NewsActivity : BaseActivity<ActivityNewsBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityNewsBinding =
        ActivityNewsBinding::inflate

    private val url: String by lazy { intent.getStringExtra(EXTRA_URL) ?: "" }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupUI() {
        setSupportActionBar(binding.toolbar)
        binding.webView.webViewClient = getClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private fun getClient(): WebViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                title = view.title
                subtitle = view.url
            }
        }
    }
}
