package io.github.flpmartins88.library.domain.book

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class BookRoutes (
        private val bookHandler: BookHandler
) {

    @Bean
    fun bookRoutes() = router {
        "/books".nest {
            GET("/", bookHandler::search)
            GET("/{id}", bookHandler::get)
            POST("/", bookHandler::save)
            DELETE("/{id}", bookHandler::delete)
        }
    }

}