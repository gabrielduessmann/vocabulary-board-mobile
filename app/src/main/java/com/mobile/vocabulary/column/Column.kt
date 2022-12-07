package com.mobile.vocabulary.column

import java.util.*

class Column(
    val id: UUID,
    val title: String,
    val nextUpdate: Date,
    val sprintOrder: Int
    ) {
}