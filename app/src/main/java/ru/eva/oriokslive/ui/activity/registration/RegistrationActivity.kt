package ru.eva.oriokslive.ui.activity.registration

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.ActivityRegistrationBinding
import ru.eva.oriokslive.ui.activity.main.MainActivity
import ru.eva.oriokslive.ui.base.BaseActivity

@AndroidEntryPoint
class RegistrationActivity : BaseActivity<ActivityRegistrationBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityRegistrationBinding =
        ActivityRegistrationBinding::inflate

    private val viewModel: RegistrationViewModel by viewModels()

    override fun setupUI() {
        binding.btnLogin.setOnClickListener {
            viewModel.getAccessToken(
                binding.etLogin.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        viewModel.checkAccessToken()
        viewModel.startMainActivity.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, R.string.registration_activity_wrong_credentials, Toast.LENGTH_LONG).show()
        }
    }
}