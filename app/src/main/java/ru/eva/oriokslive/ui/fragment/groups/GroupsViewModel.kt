package ru.eva.oriokslive.ui.fragment.groups

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.repository.RemoteRepository
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : ViewModel() {

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
        viewModelScope.launch(Dispatchers.IO) {
            remoteRepository.getSchedule(group) ?.let {
                domainRepository.setSchedule(it.data)
                getGroups()
            }
        }
    }
}