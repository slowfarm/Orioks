package ru.eva.oriokslive.ui.fragment.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.ui.entity.ScheduleItem
import ru.eva.oriokslive.utils.dayNumber
import ru.eva.oriokslive.utils.getDayOfWeek
import ru.eva.oriokslive.utils.getNextDayOfWeek
import ru.eva.oriokslive.utils.mapper.mapDay
import ru.eva.oriokslive.utils.mapper.mapWeek
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
) : ViewModel() {

    val schedule = MutableLiveData<List<ScheduleItem>>()

    fun getSchedule(group: String, position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val dayNumber = dayNumber()
            val data = when (position) {
                0 -> mapDay(domainRepository.getSchedule(dayNumber, getDayOfWeek(), group))
                1 -> mapDay(domainRepository.getSchedule(dayNumber, getNextDayOfWeek(), group))
                else -> mapWeek(domainRepository.getSchedule(position - 2, group))
            }
            schedule.postValue(data)
        }
    }
}