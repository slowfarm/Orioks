package ru.eva.oriokslive.ui.fragment.groups

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    val groups = MutableLiveData<List<String>>()

    fun getGroups() {
        viewModelScope.launch(Dispatchers.IO) {
            domainRepository.getGroups()?.let { groups.postValue(it) }
        }
    }

    fun removeGroup(group: String) {
        viewModelScope.launch(Dispatchers.IO) {
            domainRepository.removeGroup(group)
        }
    }

    fun addSchedule(group: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            domainRepository.setSchedule(remoteRepository.getSchedule(group).data)
            getGroups()
        }
    }
}
