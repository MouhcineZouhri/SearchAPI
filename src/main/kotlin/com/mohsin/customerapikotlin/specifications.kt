package com.mohsin.customerapikotlin

import org.springframework.data.jpa.domain.Specification
import java.time.LocalDate
import javax.persistence.criteria.Predicate

object MovieSpecification{

    fun filterMovie(name : String? = null , pays : String?=null , year : Int? = null
        ,language: String? = null , category: Category?
    ) : Specification<Movie>{
        return Specification { root, _, builder ->
                val predicates = mutableListOf<Predicate>()

                name?.let {
                    predicates.add(builder.like(root.get("name") , name))
                }

                pays?.let {
                    predicates.add(builder.like(root.get("pays") , pays))
                }

                language?.let {
                    predicates.add(builder.like(root.get("language") , language))
                }

                year?.let {
                    predicates.add(builder.between(root.get("year") ,LocalDate.of(year, 1,1),
                        LocalDate.of(year, 12,31)))
                }

                category?.let {
                    predicates.add(builder.isMember(category , root.get<Collection<Category>>("categories")))
                }

                builder.and(*predicates.toTypedArray())
        }
    }
}