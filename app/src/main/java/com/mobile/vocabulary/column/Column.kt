package com.mobile.vocabulary.column

import java.util.*

data class Column(
    val id: UUID,
    val title: String,
    val nextUpdate: Date,
    val sprintOrder: Int
    ) {
}