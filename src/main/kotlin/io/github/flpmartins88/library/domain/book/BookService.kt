package io.github.flpmartins88.library.domain.book

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BookService (
        private val bookRepository: BookRepository
) {

    fun save(book: Mono<Book>) = bookRepository.save(book)
    fun delete(bookId: Int) = bookRepository.delete(bookId)
    fun get(bookId: Int) = bookRepository.findById(bookId)
    fun search(value: String) = bookRepository.search(value)

}
