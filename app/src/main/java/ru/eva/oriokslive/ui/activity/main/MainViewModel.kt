package ru.eva.oriokslive.ui.activity.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.entity.Header
import ru.eva.oriokslive.utils.mapStudent
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : ViewModel() {
    val header = MutableLiveData<Header>()
    val errorMessage = MutableLiveData<Unit>()

    fun getStudent() {
        viewModelScope.launch(Dispatchers.IO) {
            remoteRepository.getStudent()?.let { domainRepository.setStudent(it) }
                ?: errorMessage.postValue(Unit)
            setHeader()
        }
    }

    private fun setHeader() {
        viewModelScope.launch(Dispatchers.IO) {
            domainRepository.getStudent()?.let { header.postValue(mapStudent(it)) }
        }
    }
}