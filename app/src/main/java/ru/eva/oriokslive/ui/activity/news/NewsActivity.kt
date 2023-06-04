package ru.eva.oriokslive.ui.activity.news

import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.databinding.ActivityNewsBinding
import ru.eva.oriokslive.ui.base.BaseActivity

@AndroidEntryPoint
class NewsActivity : BaseActivity<ActivityNewsBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityNewsBinding =
        ActivityNewsBinding::inflate

    override fun setupUI() {
        TODO("Not yet implemented")
    }
}