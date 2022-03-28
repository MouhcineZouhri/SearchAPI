package com.mohsin.customerapikotlin

import org.assertj.core.api.AssertionsForClassTypes
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDate

@DataJpaTest
class MovieTest{

    @Autowired lateinit var movieRepository : MovieRepository
    
    @Test
    fun PersistedMovieEntityTest(){
        val movie  = Movie(
            name = "movie 1",
            pays = "maroc",
            language = "arabic",
            year = LocalDate.now()
        )
        val save = movieRepository.save(movie)
        assertThat(save.id).isNotNull
    }
}