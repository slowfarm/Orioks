package ru.eva.oriokslive.ui.dialog

import android.content.Context
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import ru.eva.oriokslive.BuildConfig.VERSION_CODE
import ru.eva.oriokslive.BuildConfig.VERSION_NAME
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.DialogAboutAppBinding
import ru.eva.oriokslive.ui.base.BaseDialog

class AboutAppDialog(context: Context) : BaseDialog<DialogAboutAppBinding>(context) {

    override val bindingInflater: (LayoutInflater) -> DialogAboutAppBinding =
        DialogAboutAppBinding::inflate

    override fun setupUI() {
        binding.tvGithubLink.movementMethod = LinkMovementMethod.getInstance()
        binding.tvVersion.text = context.getString(R.string.version, VERSION_NAME, VERSION_CODE)
    }
}
