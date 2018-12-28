package io.github.flpmartins88.library.rest

import io.github.flpmartins88.library.domain.book.BookService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.bodyToMono
import java.net.URI

@Component
class BookHandler (
        private val bookService: BookService
) {

    fun save(serverRequest: ServerRequest)
        = bookService.save(serverRequest.bodyToMono())
            .flatMap { created(URI.create("/customers/${it}")).build() }

    fun get(serverRequest: ServerRequest)
            = bookService.get(serverRequest.pathVariable("id").toInt())
                .flatMap { ok().body(fromObject(it)) }
                .switchIfEmpty(notFound().build())

    fun delete(serverRequest: ServerRequest)
        = bookService.delete(serverRequest.pathVariable("/id").toInt())
            .flatMap {
                if (it)
                    ok().build()
                else
                    notFound().build()
            }

    fun search(serverRequest: ServerRequest)
        = ok().body(fromObject(bookService.search(serverRequest.queryParam("name").orElse(""))))

}
