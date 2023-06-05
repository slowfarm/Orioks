package ru.eva.oriokslive.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class BaseDialog<Binding : ViewBinding>(context: Context) : Dialog(context) {
    protected val binding: Binding
        get() = _binding as Binding
    private var _binding: Binding? = null
    abstract val bindingInflater: (LayoutInflater) -> Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)

        window?.attributes?.width = (context.resources.displayMetrics.widthPixels * SCALE).toInt()

        setupUI()
    }

    abstract fun setupUI()

    companion object {
        private const val SCALE = 0.9
    }
}
