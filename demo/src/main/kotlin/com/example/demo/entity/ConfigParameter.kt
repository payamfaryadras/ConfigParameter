package com.example.demo.entity

import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@Entity
@Table(name = "configparameter", uniqueConstraints = [UniqueConstraint(columnNames = ["parent_id", "value"])])
data class ConfigParameter(var name: String,var value :Byte = -1 ) {
    @Id
    var id: UUID = UUID.randomUUID()

    @OneToMany(mappedBy = "parent",cascade = [CascadeType.ALL],orphanRemoval = true)
    var children:Set<ConfigParameter> = emptySet()

    @ManyToOne
    @JoinColumn(name="parent_id",nullable = true)
    var parent  : ConfigParameter ? = null

}