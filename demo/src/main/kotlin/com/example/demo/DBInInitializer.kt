package com.example.demo

import com.example.demo.entity.ConfigParameter
import com.example.demo.repository.ConfigParameterRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


@Component
class DBInInitializer(val repository: ConfigParameterRepository) :CommandLineRunner {

    @ExperimentalStdlibApi
    override fun run(vararg args: String?) {
        val parent: ConfigParameter = ConfigParameter("REQUEST_STATUS", 0)
        repository.save(parent)
        val child1 :ConfigParameter = ConfigParameter("REQUEST_STATUS_REJECT",1)
        child1.parent = parent
        repository.save(child1)
        parent.children = buildSet { child1 }


    }
}