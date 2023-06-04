package ru.eva.oriokslive.ui.activity.main

import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.progressindicator.CircularProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.ActivityMainBinding
import ru.eva.oriokslive.ui.base.BaseActivity

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
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, R.string.no_connection, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navigationHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}