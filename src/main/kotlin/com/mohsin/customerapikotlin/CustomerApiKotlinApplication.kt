package com.mohsin.customerapikotlin

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
import java.util.*
import javax.persistence.*
import javax.persistence.criteria.Predicate
import kotlin.collections.HashMap

@SpringBootApplication
class CustomerApiKotlinApplication{
    @Bean
    fun runner(movieRepository: MovieRepository , categoryRepository: CategoryRepository) = ApplicationRunner {
        val movie = movieRepository.save(Movie(name = "movie1", language = "arab", pays = "egypt"))
        val movie1 =movieRepository.save(Movie(name = "movie2" , language = "arab" ,  pays = "egypt"))
        val movie2 =movieRepository.save(Movie(name = "movie3" , language = "spanish" ,pays = "spain"))
        val movie3 = movieRepository.save(Movie(name = "movie4" , language = "english" ,  pays = "usa"))
        val movie4 = movieRepository.save(Movie(name = "movie5" , language = "frensh" ,pays = "france"))

        val cat1= categoryRepository.save(Category(name = "DRAMA"))
        val cat2=categoryRepository.save(Category(name = "ROMANCE"))
        val cat3=categoryRepository.save(Category(name = "COMEDY"))
        val cat4=categoryRepository.save(Category(name = "ACTION"))

        movie.categories.add(cat1)
        movie.categories.add(cat2)

        movie1.categories.add(cat3)

        movie2.categories.add(cat4)

        movie3.categories.add(cat3)

        movie4.categories.add(cat1)

        movieRepository.saveAll(mutableListOf(movie , movie1,movie2 , movie3 , movie4))


    }
}

fun main(args: Array<String>) {
    runApplication<CustomerApiKotlinApplication>(*args)
}
