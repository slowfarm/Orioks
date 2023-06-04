package ru.eva.oriokslive.ui.fragment.student

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.entity.orioks.Student
import ru.eva.oriokslive.network.repository.RemoteRepository
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : ViewModel() {

    val student = MutableLiveData<Student>()
    val errorMessage = MutableLiveData<Unit>()
    val finishActivity = MutableLiveData<Unit>()

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            domainRepository.getAccessToken()?.let {
                remoteRepository.deleteAccessToken(it)?.let {
                    domainRepository.clearAll()
                    finishActivity.postValue(Unit)
                } ?: errorMessage.postValue(Unit)
            }
        }
    }

    fun getStudent() {
        viewModelScope.launch(Dispatchers.IO) {
            remoteRepository.getStudent()?.let { student.postValue(it) }
                ?: errorMessage.postValue(Unit)
        }
    }
}