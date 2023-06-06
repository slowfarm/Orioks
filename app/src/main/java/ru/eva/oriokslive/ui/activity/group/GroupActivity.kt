package ru.eva.oriokslive.ui.activity.group

import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.ActivityGroupBinding
import ru.eva.oriokslive.ui.adapter.GroupAddAdapter
import ru.eva.oriokslive.ui.base.BaseActivity
import ru.eva.oriokslive.ui.fragment.groups.GroupsFragment.Companion.EXTRA_GROUP
import ru.eva.oriokslive.utils.setOnQueryChangeListener
import ru.eva.oriokslive.utils.showToast

@AndroidEntryPoint
class GroupActivity : BaseActivity<ActivityGroupBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityGroupBinding =
        ActivityGroupBinding::inflate
    private val viewModel: GroupViewModel by viewModels()

    private val adapter: GroupAddAdapter by lazy {
        GroupAddAdapter {
            setResult(RESULT_OK, Intent().putExtra(EXTRA_GROUP, it))
            finish()
        }
    }

    override fun setupUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        binding.rvGroups.layoutManager = LinearLayoutManager(this)
        binding.rvGroups.adapter = adapter

        viewModel.getGroups()
        viewModel.groups.observe(this) { adapter.addItems(it) }
        viewModel.onError.observe(this) { showToast(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.group_menu, menu)
        val searchView = menu?.findItem(R.id.searchView)?.actionView as? SearchView
        searchView?.setOnQueryChangeListener { query ->
            viewModel.groups.value?.filter { it.lowercase().contains(query.lowercase()) }
                ?.let { adapter.addItems(it) }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        onBackPressed()
        return true
    }
}
