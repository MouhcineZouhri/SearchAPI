package com.mohsin.customerapikotlin

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*


public interface MovieRepository : JpaRepository<Movie,Long> , JpaSpecificationExecutor<Movie>

public interface CategoryRepository : JpaRepository<Category  , Long>{
    fun findCategoryByName(name : String?) : Optional<Category>
}