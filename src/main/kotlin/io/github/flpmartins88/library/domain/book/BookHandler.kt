package io.github.flpmartins88.library.domain.book

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import java.net.URI
import java.util.*

@Component
class BookHandler (
        private val bookService: BookService
) {

    fun save(serverRequest: ServerRequest)
        = bookService.save(serverRequest.bodyToMono())
            .flatMap { created(URI.create("/customers/${it._id}")).build() }

    fun get(serverRequest: ServerRequest)
            = bookService.get(serverRequest.pathVariable("id").toInt())
                .flatMap { ok().body(fromObject(it)) }
                .switchIfEmpty(notFound().build())

    fun delete(serverRequest: ServerRequest) {

    }

    fun search(serverRequest: ServerRequest) {

    }

}
