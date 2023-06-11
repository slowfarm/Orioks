package ru.eva.oriokslive.ui.activity.resit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.entity.orioks.Debt
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.base.BaseViewModel
import ru.eva.oriokslive.ui.entity.ResitItem
import ru.eva.oriokslive.utils.mapper.mapResits
import javax.inject.Inject

@HiltViewModel
class ResitViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    val title = MutableLiveData<String>()
    val resits = MutableLiveData<List<ResitItem>>()
    val debt = MutableLiveData<Debt>()

    fun getTitle(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            title.postValue(domainRepository.getDebtsById(id).name)
        }
    }

    fun getResits(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            remoteRepository.getResits(id).let {
                domainRepository.setResits(it)
                resits.postValue(mapResits(it))
            }
        }
    }

    fun getDebt(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            debt.postValue(domainRepository.getDebtsById(id))
        }
    }

    fun getLocalResit(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            domainRepository.getResitsById(id)?.let { resits.postValue(mapResits(it)) }
        }
    }
}
