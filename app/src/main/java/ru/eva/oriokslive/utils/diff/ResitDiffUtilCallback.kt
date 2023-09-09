package ru.eva.oriokslive.utils.diff

import androidx.recyclerview.widget.DiffUtil
import ru.eva.oriokslive.ui.entity.ResitItem

class ResitDiffUtilCallback(
    private val oldList: List<ResitItem>,
    private val newList: List<ResitItem>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
