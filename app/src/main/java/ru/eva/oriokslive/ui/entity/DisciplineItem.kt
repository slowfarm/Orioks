package ru.eva.oriokslive.ui.entity

data class DisciplineItem(
    val id: Int,
    val progress: Int,
    val name: String,
    val grade: String = "-",
    val maxGrade: String,
    val color: Int,
)
