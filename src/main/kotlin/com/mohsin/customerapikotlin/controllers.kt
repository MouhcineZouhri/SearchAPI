package com.mohsin.customerapikotlin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/movies")
class MovieController(
    private  val movieRepository: MovieRepository,
    private  val categoryRepository: CategoryRepository,
){

    @GetMapping
    fun filterMovie(
        @RequestParam name : String? = null,
        @RequestParam language : String? = null,
        @RequestParam pays : String? = null,
        @RequestParam year : Int? = null,
        @RequestParam category: String?=null
    ) : Collection<Movie>{

        var category = categoryRepository.findCategoryByName(category).orElse(null)

        return movieRepository.findAll(MovieSpecification.filterMovie(
            name = name , language = language , pays =  pays , year = year,
            category = category
        ))
    }
}