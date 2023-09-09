package ru.eva.oriokslive.ui.activity.resit

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.ActivityResitBinding
import ru.eva.oriokslive.network.exceptions.NetworkException
import ru.eva.oriokslive.ui.adapter.ResitsAdapter
import ru.eva.oriokslive.ui.base.BaseActivity
import ru.eva.oriokslive.ui.dialog.DebtDialog
import ru.eva.oriokslive.utils.showToast

@AndroidEntryPoint
class ResitActivity : BaseActivity<ActivityResitBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityResitBinding =
        ActivityResitBinding::inflate
    private val viewModel: ResitViewModel by viewModels()

    private val adapter by lazy { ResitsAdapter() }
    private val id: Int by lazy { intent.getIntExtra(EXTRA_ID, 0) }

    override fun setupUI() {
        binding.rvResit.layoutManager = LinearLayoutManager(this)
        binding.rvResit.adapter = adapter

        viewModel.getTitle(id)
        viewModel.getResits(id)

        viewModel.title.observe(this) {
            title = it
            setSupportActionBar(binding.toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }

        viewModel.resits.observe(this) { adapter.setItems(it) }

        viewModel.debt.observe(this) {
            DebtDialog(this, it).show()
        }
        viewModel.onError.observe(this) {
            if (it is NetworkException) viewModel.getLocalResit(id)
            showToast(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.events, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.actionView -> viewModel.getDebt(id)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_ID = "ID"
    }
}
