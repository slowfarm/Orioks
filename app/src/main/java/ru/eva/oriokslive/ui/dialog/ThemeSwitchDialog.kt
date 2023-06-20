package ru.eva.oriokslive.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.DialogThemeSwitchBinding
import ru.eva.oriokslive.ui.base.BaseDialog

class ThemeSwitchDialog(
    context: Context,
    theme: Int,
    private val listener: (Int) -> Unit,
) :
    BaseDialog<DialogThemeSwitchBinding>(context) {

    override val bindingInflater: (LayoutInflater) -> DialogThemeSwitchBinding =
        DialogThemeSwitchBinding::inflate

    private val checkedId = when (theme) {
        MODE_NIGHT_NO -> R.id.rbLight
        MODE_NIGHT_YES -> R.id.rbDark
        else -> R.id.rbDefault
    }

    override fun setupUI() {
        binding.rgContainer.check(checkedId)
        binding.rgContainer.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.rbLight -> listener.invoke(MODE_NIGHT_NO)
                R.id.rbDark -> listener.invoke(MODE_NIGHT_YES)
                R.id.rbDefault -> listener.invoke(MODE_NIGHT_FOLLOW_SYSTEM)
            }
            dismiss()
        }
    }
}
