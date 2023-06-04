package ru.eva.oriokslive.ui.activity.group

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.network.repository.RemoteRepository
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
) : ViewModel() {

    val groups = MutableLiveData<List<String>>()
    val onError = MutableLiveData<Unit>()

    fun getGroups() {
        viewModelScope.launch(Dispatchers.IO) {
            remoteRepository.getGroups()?.let { groups.postValue(it) }
                ?: onError.postValue(Unit)
        }
    }
}