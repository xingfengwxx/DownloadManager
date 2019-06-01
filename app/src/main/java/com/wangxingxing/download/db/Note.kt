package com.wangxingxing.download.db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.*

@Entity
data class Note(
    @Id var id: Long = 0,
    var text: String? = null,
    var comment: String? = null,
    var date: Date? = null
)