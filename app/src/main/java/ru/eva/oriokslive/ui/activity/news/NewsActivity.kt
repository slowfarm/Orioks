package ru.eva.oriokslive.ui.activity.news

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

    override fun setupUI() {
        setSupportActionBar(binding.toolbar)

        intent.getStringExtra(EXTRA_URL)?.let {
            binding.webView.apply {
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView, url: String) {
                        supportActionBar?.apply {
                            setDisplayHomeAsUpEnabled(true)
                            title = view.title
                            subtitle = view.url
                        }
                    }
                }
                loadUrl(it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}