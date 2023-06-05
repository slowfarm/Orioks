package ru.eva.oriokslive.ui.activity.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.entity.orioks.Discipline
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.base.BaseViewModel
import ru.eva.oriokslive.ui.entity.EventItem
import ru.eva.oriokslive.utils.mapEvents
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    val title = MutableLiveData<String>()
    val events = MutableLiveData<List<EventItem>>()
    val discipline = MutableLiveData<Discipline>()

    fun getTitle(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            title.postValue(domainRepository.getDisciplineById(id).name)
        }
    }

    fun getEvents(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            remoteRepository.getEvents(id).let {
                domainRepository.setEvents(it)
                events.postValue(mapEvents(it))
            }
        }
    }

    fun getDiscipline(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            discipline.postValue(domainRepository.getDisciplineById(id))
        }
    }

    fun getLocalEvent(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            domainRepository.getEventsById(id)?.let { events.postValue(mapEvents(it)) }
        }
    }
}
