package com.mohsin.customerapikotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDate

@DataJpaTest
class MovieSpecificationTest{

    @Autowired lateinit var movieRepository: MovieRepository

    @Test
    fun getMoviesByCountryTest(){
        val movie1 = Movie(
            name = "movie 1",
            pays = "maroc",
            year = LocalDate.now(),
            language = "arabic"
        )

        val movie2 = Movie(
            name = "movie 2",
            pays = "maroc",
            year = LocalDate.now(),
            language = "arabic"
        )

        val movie3 =  Movie(
            name = "movie 3",
            pays = "algeria",
            year = LocalDate.now(),
            language = "arabic"
        )

        val movies : List<Movie>  = arrayListOf(movie1 , movie2 , movie3)

        movieRepository.saveAll(movies)

        val findFilterMovies = movieRepository.findAll(MovieSpecification.filterMovie(pays = "maroc"))

        assertThat(findFilterMovies).isNotEmpty

        assertThat(findFilterMovies).contains(movie1 , movie2)

        assertThat(findFilterMovies).doesNotContain(movie3)

    }


    @Test
    fun getMoviesByLanguageTest(){
        val language = "arabic"
        val movie1 = Movie(
            name = "movie 1",
            pays = "maroc",
            year = LocalDate.now(),
            language = language
        )

        val movie2 = Movie(
            name = "movie 2",
            pays = "maroc",
            year = LocalDate.now(),
            language = language
        )

        val movie3 =  Movie(
            name = "movie 3",
            pays = "algeria",
            year = LocalDate.now(),
            language = "english"
        )

        val movies : List<Movie>  = arrayListOf(movie1 , movie2 , movie3)

        movieRepository.saveAll(movies)

        val findFilterMovies = movieRepository.findAll(MovieSpecification.filterMovie(language = language))

        assertThat(findFilterMovies).isNotEmpty

        assertThat(findFilterMovies).contains(movie1 , movie2)

        assertThat(findFilterMovies).doesNotContain(movie3)

    }


    @Test
    fun getMoviesByYearBoundaryTest(){
        val movie1 = Movie(
            name = "movie 1",
            pays = "maroc",
            year = LocalDate.now(),
            language = "arabic"
        )

        val movie2 = Movie(
            name = "movie 2",
            pays = "maroc",
            year = LocalDate.of(2022 , 10  , 1),
            language = "arabic"
        )

        val movie3 =  Movie(
            name = "movie 3",
            pays = "algeria",
            year = LocalDate.of(2021 , 10  , 1),
            language = "arabic"
        )

        val movies : List<Movie>  = arrayListOf(movie1 , movie2 , movie3)

        movieRepository.saveAll(movies)

        val findFilterMovies = movieRepository.findAll(MovieSpecification.filterMovie(year = 2022))

        assertThat(findFilterMovies).isNotEmpty

        assertThat(findFilterMovies).contains(movie1 , movie2)

        assertThat(findFilterMovies).doesNotContain(movie3)

    }
}