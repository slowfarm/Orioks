package ru.eva.oriokslive.ui.fragment.debt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.base.BaseViewModel
import ru.eva.oriokslive.ui.entity.DisciplineItem
import ru.eva.oriokslive.utils.mapDebts
import javax.inject.Inject

@HiltViewModel
class DebtViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    val debts = MutableLiveData<List<DisciplineItem>>()

    fun getDebtList() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            remoteRepository.getDebts().let {
                domainRepository.setDebts(it)
                debts.postValue(mapDebts(it))
            }
        }
    }

    fun getLocalDebts() {
        viewModelScope.launch(Dispatchers.IO) {
            domainRepository.getDebts()?.let { debts.postValue(mapDebts(it)) }
        }
    }
}