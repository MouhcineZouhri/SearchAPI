package com.mohsin.customerapikotlin

import java.time.LocalDate
import javax.persistence.*

@Entity
data class Movie(
    @Id @GeneratedValue val id : Long? = null,
    var name : String,
    var pays : String,
    var language : String,
    var year : LocalDate = LocalDate.now(),
    @ManyToMany var categories : MutableSet<Category> = mutableSetOf()
)

@Entity
data class Category(
    @Id @GeneratedValue val id : Long? = null,
    var name :String,
)