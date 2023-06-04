package ru.eva.oriokslive.ui.activity.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.utils.getCurrentWeek
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : ViewModel() {

    val scheduleExist = MutableLiveData<Unit>()
    val viewPagerPosition = MutableLiveData<Int>()
    val onError = MutableLiveData<Unit>()

    fun getSchedule(group: String) {
        viewModelScope.launch(Dispatchers.IO) {
            domainRepository.getSchedule(group)?.let {
                scheduleExist.postValue(Unit)
            } ?: remoteRepository.getSchedule(group)?.let {
                domainRepository.setSchedule(it.data)
                scheduleExist.postValue(Unit)
            } ?: onError.postValue(Unit)
        }
    }

    fun setViewPagerToCurrentWeek() {
        viewPagerPosition.postValue((getCurrentWeek() - 1) % 4 + 2)
    }
}