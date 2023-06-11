package ru.eva.oriokslive.ui.activity.group

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    val groups = MutableLiveData<List<String>>()

    fun getGroups() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            groups.postValue(remoteRepository.getGroups())
        }
    }
}
