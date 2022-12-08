package com.mobile.vocabulary.vocabulary

import java.util.*

data class Vocabulary(
    var id: UUID?,
    var word: String,
    var description: String?
) {
}