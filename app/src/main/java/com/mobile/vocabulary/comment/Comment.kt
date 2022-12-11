package com.mobile.vocabulary.comment

import com.mobile.vocabulary.vocabulary.Vocabulary
import java.util.*

data class Comment(
    val id: UUID?,
    val comment: String,
    val vocabulary: Vocabulary
    ) {}