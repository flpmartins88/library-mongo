package io.github.flpmartins88.library.domain.book

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Book (
        var name: String
)