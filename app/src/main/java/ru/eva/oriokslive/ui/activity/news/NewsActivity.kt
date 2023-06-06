package ru.eva.oriokslive.ui.activity.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MenuItem
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.databinding.ActivityNewsBinding
import ru.eva.oriokslive.ui.base.BaseActivity
import ru.eva.oriokslive.ui.fragment.news.NewsFragment.Companion.EXTRA_URL


@AndroidEntryPoint
class NewsActivity : BaseActivity<ActivityNewsBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityNewsBinding =
        ActivityNewsBinding::inflate

    val viewModel: NewsViewModel by viewModels()

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupUI() {
        setSupportActionBar(binding.toolbar)
        viewModel.getCookie()
        viewModel.cookie.observe(this) { cookie ->
            intent.getStringExtra(EXTRA_URL)?.let { url ->
                CookieManager.getInstance().apply {
                    setAcceptCookie(true)
                    setCookie(url, cookie)
                    flush()
                }
                binding.webView.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView, url: String) {
                        supportActionBar?.apply {
                            setDisplayHomeAsUpEnabled(true)
                            title = view.title
                            subtitle = view.url
                        }
                    }
                }
                binding.webView.loadUrl(url)
                binding.webView.settings.javaScriptEnabled = true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}