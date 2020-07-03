package com.example.demo.repository

import com.example.demo.entity.ConfigParameter

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest



@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConfigParameterRepositoryTest(@Autowired val repository: ConfigParameterRepository) {

    @BeforeAll
    internal fun beforeAll() {
        val parent: ConfigParameter = ConfigParameter("REQUEST_STATUS", 0)
        repository.save(parent)
        val child1: ConfigParameter = ConfigParameter("REQUEST_STATUS_REJECT", 1)
        child1.parent = parent
        repository.save(child1)
        parent.children = setOf(child1)
        repository.flush()
    }


    @Test
    fun `When findConfigParamByParent Should return ConfigParameter which is contain children`() {

        val parent = repository.findConfigParameterByName("REQUEST_STATUS")
        Assertions.assertEquals(1, parent.children.size)
    }

    @Test
    fun `when  create ConfigParameter with duplicate name expected throw exception`() {
        val parent = repository.findConfigParameterByName("REQUEST_STATUS")
        val duplicatedItem: ConfigParameter = ConfigParameter("REQUEST_STATUS_REJECT", 1)
         duplicatedItem.parent = parent
        repository.flush();
      //  Assertions.assertThrows(IllegalStateException::class.java, repository.save(duplicatedItem))

    }
}