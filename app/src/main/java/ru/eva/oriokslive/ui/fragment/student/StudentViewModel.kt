package ru.eva.oriokslive.ui.fragment.student

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.entity.orioks.Student
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    val student = MutableLiveData<Student>()
    val finishActivity = MutableLiveData<Unit>()

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            domainRepository.getAccessToken()?.let {
                remoteRepository.deleteAccessToken(it)
                domainRepository.clearAll()
                finishActivity.postValue(Unit)
            }
        }
    }

    fun getStudent() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            student.postValue(remoteRepository.getStudent())
        }
    }

    fun getLocalStudent() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            student.postValue(domainRepository.getStudent())
        }
    }
}
