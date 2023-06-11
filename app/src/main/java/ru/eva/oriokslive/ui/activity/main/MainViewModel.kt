package ru.eva.oriokslive.ui.activity.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.base.BaseViewModel
import ru.eva.oriokslive.ui.entity.Header
import ru.eva.oriokslive.utils.mapper.mapStudent
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {
    val header = MutableLiveData<Header>()
    val theme = MutableLiveData<Int>()

    fun getStudent() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            remoteRepository.getStudent().let {
                domainRepository.setStudent(it)
                header.postValue(mapStudent(it))
            }
        }
    }

    fun getLocalStudent() {
        viewModelScope.launch(Dispatchers.IO) {
            domainRepository.getStudent()?.let { header.postValue(mapStudent(it)) }
        }
    }

    fun setDefaultTheme(mode: Int) {
        domainRepository.setDefaultTheme(mode)
        theme.postValue(mode)
    }
}
