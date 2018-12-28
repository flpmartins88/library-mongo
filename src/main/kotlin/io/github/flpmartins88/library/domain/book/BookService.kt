package io.github.flpmartins88.library.domain.book

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BookService (
        private val bookRepository: BookRepository
) {

    fun save(book: Mono<Book>) = bookRepository.save(book)
    fun delete(bookId: Int) = bookRepository.delete(bookId).map { it.deletedCount > 0 }
    fun get(bookId: Int) = bookRepository.findById(bookId)
    fun search(name: String) = bookRepository.search(name)

}
