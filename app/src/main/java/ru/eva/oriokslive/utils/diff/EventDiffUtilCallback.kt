package ru.eva.oriokslive.utils.diff

import androidx.recyclerview.widget.DiffUtil
import ru.eva.oriokslive.ui.entity.EventItem

class EventDiffUtilCallback(
    private val oldList: List<EventItem>,
    private val newList: List<EventItem>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].name == newList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
