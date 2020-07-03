package com.example.demo.repository

import com.example.demo.entity.ConfigParameter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*
import javax.persistence.Id

interface ConfigParameterRepository :JpaRepository<ConfigParameter,UUID> {
    @Query("select c from ConfigParameter c where c.parent = :parent")
    fun findConfigParameterByParent(parent: ConfigParameter):Set<ConfigParameter>
    fun findConfigParameterByName(nameOfConfig:String):ConfigParameter


}