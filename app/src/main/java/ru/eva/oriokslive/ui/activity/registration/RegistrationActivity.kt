package ru.eva.oriokslive.ui.activity.registration

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.ActivityRegistrationBinding
import ru.eva.oriokslive.network.exceptions.NetworkException
import ru.eva.oriokslive.ui.activity.main.MainActivity
import ru.eva.oriokslive.ui.base.BaseActivity
import ru.eva.oriokslive.utils.showToast
import java.util.concurrent.atomic.AtomicBoolean

@AndroidEntryPoint
class RegistrationActivity : BaseActivity<ActivityRegistrationBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityRegistrationBinding =
        ActivityRegistrationBinding::inflate

    private val viewModel: RegistrationViewModel by viewModels()

    private var needShowSplash = AtomicBoolean(true)

    override fun setupUI() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { needShowSplash.get() }

        binding.btnLogin.setOnClickListener {
            viewModel.getAccessToken(
                binding.etLogin.text.toString(),
                binding.etPassword.text.toString(),
            )
        }

        viewModel.checkAccessToken()
        viewModel.startMainActivity.observe(this) {
            needShowSplash.set(false)
            startMainActivity()
        }
        viewModel.noToken.observe(this) {
            needShowSplash.set(false)
        }
        viewModel.onError.observe(this) {
            needShowSplash.set(false)
            when (it) {
                is NetworkException -> {
                    startMainActivity()
                    showToast(it)
                }
                else -> {
                    viewModel.deleteToken()
                    showToast(R.string.wrong_credentials)
                }
            }
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
