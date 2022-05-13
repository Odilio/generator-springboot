package <%= packageName %>.ports.out

import <%= packageName %>.adapters.dto.<%= entityName %>Mapper
import <%= packageName %>.adapters.model.<%= entityName %>Model
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface <%= entityName %>PersistencePort {

    fun findByIdOrNull(codigo: Long): <%= entityName %>Model?

    fun findAll(page: Pageable): Page<<%= entityName %>Model>

    fun findAllFields(search: String?, page: Pageable): Page<<%= entityName %>Model>

    fun save(<%= entityName %>: <%= entityName %>Mapper): <%= entityName %>Model

    fun deleteById(id: Long)
}
