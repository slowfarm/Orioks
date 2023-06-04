package ru.eva.oriokslive.ui.activity.events

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.ActivityEventsBinding
import ru.eva.oriokslive.ui.adapter.EventsAdapter
import ru.eva.oriokslive.ui.base.BaseActivity
import ru.eva.oriokslive.ui.dialog.DisciplineDialog

@AndroidEntryPoint
class EventsActivity : BaseActivity<ActivityEventsBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityEventsBinding =
        ActivityEventsBinding::inflate
    private val viewModel: EventsViewModel by viewModels()

    private val id: Int by lazy { intent.getIntExtra(EXTRA_ID, 0) }

    override fun setupUI() {
        viewModel.getTitle(id)
        viewModel.getEvents(id)

        viewModel.title.observe(this) {
            title = it
            setSupportActionBar(binding.toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }

        viewModel.events.observe(this) {
            binding.rvEvents.layoutManager = LinearLayoutManager(this)
            binding.rvEvents.adapter = EventsAdapter(it)
        }

        viewModel.discipline.observe(this) {
            DisciplineDialog(this, it).show()
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, R.string.no_connection, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.events, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.actionView -> viewModel.getDiscipline(id)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_ID = "ID"
    }
}