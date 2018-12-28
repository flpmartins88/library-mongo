package io.github.flpmartins88.library.domain.book

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
class BookRepository (
        private val mongoTemplate: ReactiveMongoTemplate
) {

    fun save(book: Mono<Book>) = mongoTemplate.save(book)
    fun delete(bookId: Int) = mongoTemplate.remove(Query(where("_id").isEqualTo(bookId)))

    fun findById(bookId: Int) = mongoTemplate.findById(bookId, Book::class.java)
    fun search(value: String) = mongoTemplate.find(
            Query(where("name").regex(".*$value.*", "i")), Book::class.java
    )


}
