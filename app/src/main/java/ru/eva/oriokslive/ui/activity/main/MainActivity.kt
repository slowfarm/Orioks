package ru.eva.oriokslive.ui.activity.main

import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.switchmaterial.SwitchMaterial
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.ActivityMainBinding
import ru.eva.oriokslive.network.exceptions.NetworkException
import ru.eva.oriokslive.ui.base.BaseActivity
import ru.eva.oriokslive.utils.showToast

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate
    private val viewModel: MainViewModel by viewModels()

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.nav_main,
                R.id.nav_student,
                R.id.nav_schedule,
                R.id.nav_news,
                R.id.nav_security
            ), binding.drawerLayout
        )
    }

    override fun setupUI() {
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.navigationHostFragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navigationView.setupWithNavController(navController)

        val menuItem = binding.navigationView.menu.findItem(R.id.nav_theme_switch) as MenuItem
        val themeSwitch = menuItem.actionView?.findViewById<SwitchMaterial>(R.id.theme_switch)
        themeSwitch?.setOnCheckedChangeListener { _, checked ->
            val mode = if (checked) MODE_NIGHT_YES else MODE_NIGHT_NO
            viewModel.setDefaultTheme(mode)
        }

        viewModel.getDefaultTheme()
        viewModel.getStudent()
        viewModel.header.observe(this) {
            with(binding.navigationView.getHeaderView(0)) {
                findViewById<TextView>(R.id.tvWeek).text = it.week
                findViewById<CircularProgressIndicator>(R.id.pbScore).progress = it.progress
                findViewById<TextView>(R.id.tvGrade).setText(it.value)
                findViewById<TextView>(R.id.tvUsername).text = it.username
                findViewById<TextView>(R.id.tvGroup).text = it.group
            }
        }
        viewModel.onError.observe(this) {
            if (it is NetworkException) viewModel.getLocalStudent()
            showToast(it)
        }
        viewModel.theme.observe(this) {
            themeSwitch?.isChecked = it == MODE_NIGHT_YES
            AppCompatDelegate.setDefaultNightMode(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navigationHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}