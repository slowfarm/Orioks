package ru.eva.oriokslive.ui.fragment.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.base.BaseViewModel
import ru.eva.oriokslive.ui.entity.DisciplineItem
import ru.eva.oriokslive.utils.mapper.mapDisciplines
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    val disciplines = MutableLiveData<List<DisciplineItem>>()

    fun getDisciplineList() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            remoteRepository.getDisciplines().let {
                domainRepository.setDisciplines(it)
                disciplines.postValue(mapDisciplines(it))
            }
        }
    }

    fun getLocalDisciplines() {
        viewModelScope.launch(Dispatchers.IO) {
            domainRepository.getDisciplines()?.let { disciplines.postValue(mapDisciplines(it)) }
        }
    }
}
