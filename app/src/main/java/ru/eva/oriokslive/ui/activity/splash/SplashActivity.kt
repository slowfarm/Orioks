package ru.eva.oriokslive.ui.activity.splash

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.ActivitySplashBinding
import ru.eva.oriokslive.ui.activity.main.MainActivity
import ru.eva.oriokslive.ui.activity.registration.RegistrationActivity
import ru.eva.oriokslive.ui.base.BaseActivity

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivitySplashBinding =
        ActivitySplashBinding::inflate

    private val viewModel: SplashViewModel by viewModels()

    override fun setupUI() {
        viewModel.checkAccessToken()
        viewModel.startActivity.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        viewModel.onError.observe(this) {
            Toast.makeText(this, R.string.token_deleted, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }
    }
}