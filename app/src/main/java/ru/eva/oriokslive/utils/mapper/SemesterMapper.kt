package ru.eva.oriokslive.utils.mapper

import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem
import ru.eva.oriokslive.R
import ru.eva.oriokslive.network.entity.Semester

fun mapSemester(semester: Semester) = semester.list.map {
    RFACLabelItem<Int>().setLabel(it.name).apply {
        if (semester.current == it.id) resId = R.drawable.ic_checked
    }
}
