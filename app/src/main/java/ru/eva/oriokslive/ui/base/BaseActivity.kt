package ru.eva.oriokslive.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<Binding : ViewBinding>() :
    AppCompatActivity() {
    protected val binding: Binding
        get() = _binding as Binding
    private var _binding: Binding? = null
    abstract val bindingInflater: (LayoutInflater) -> Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        setupUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun setupUI()
}
