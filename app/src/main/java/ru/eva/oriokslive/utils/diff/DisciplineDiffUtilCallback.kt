package ru.eva.oriokslive.utils.diff

import androidx.recyclerview.widget.DiffUtil
import ru.eva.oriokslive.ui.entity.DisciplineItem

class DisciplineDiffUtilCallback(
    private val oldList: List<DisciplineItem>,
    private val newList: List<DisciplineItem>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
