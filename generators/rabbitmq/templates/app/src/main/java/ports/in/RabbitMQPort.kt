package <%= packageName %>.ports.`in`

import <%= packageName %>.adapters.dto.<%= entityName %>Mapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface <%= entityName %>ServicePort {

    fun buscarPorCodigo(codigo: Long): <%= entityName %>Mapper?

    fun listarTodos(page: Pageable): Page<<%= entityName %>Mapper>

    fun listarFiltrandoTodosCampos(search: String?, page: Pageable): Page<<%= entityName %>Mapper>

    fun salvar<%= entityName %>(<%= entityName %>: <%= entityName %>Mapper): <%= entityName %>Mapper

    fun remover<%= entityName %>(id: Long)
}

