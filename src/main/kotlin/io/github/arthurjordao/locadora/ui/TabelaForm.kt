package io.github.arthurjordao.locadora.ui

import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI
import org.vaadin.viritin.form.AbstractForm
import org.vaadin.viritin.grid.MGrid
import org.vaadin.viritin.layouts.MHorizontalLayout

abstract class TabelaForm<T> : UI(), Tabela<T> {
    val root = MHorizontalLayout()
    abstract val tabela: MGrid<T>
    abstract val formulario: AbstractForm<T>
    override fun init(p0: VaadinRequest?) {
        content = root
        configuraTabela()
        adicionaFormulario()
    }

    fun adicionaFormulario() {
        root.addComponent(formulario)
    }

    open fun configuraTabela() {
        root.addComponent(tabela)
        tabela.addItemClickListener {
            formulario.entity = it.item
        }
        atualizaTabela()
    }

    abstract fun atualizaTabela()
}


interface Tabela<T> {

    fun salva(entity: T)
    fun remove(entity: T)
    fun getEntity(id: Long): T

}