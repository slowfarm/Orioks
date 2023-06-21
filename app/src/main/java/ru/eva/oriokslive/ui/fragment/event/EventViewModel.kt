package ru.eva.oriokslive.ui.fragment.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.entity.Semester
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.base.BaseViewModel
import ru.eva.oriokslive.ui.entity.DisciplineItem
import ru.eva.oriokslive.utils.mapper.mapDisciplines
import ru.eva.oriokslive.utils.mapper.mapSemester
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    val disciplines = MutableLiveData<List<DisciplineItem>>()
    val semesterItems = MutableLiveData<List<RFACLabelItem<Int>>>()
    val semesterChanged = MutableLiveData<Int>()

    private var semester: Semester? = null

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

    fun getSemester() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            remoteRepository.getSemester().let {
                semester = it
                semesterItems.postValue(mapSemester(it))
            }
        }
    }

    fun changeSemester(position: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            semester?.list?.get(position)?.id?.let {
                domainRepository.clearDisciplines()
                semesterChanged.postValue(remoteRepository.changeSemester(it).semester)
            }
        }
    }
}
