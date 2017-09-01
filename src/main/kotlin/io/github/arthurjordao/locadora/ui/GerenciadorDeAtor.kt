package io.github.arthurjordao.locadora.ui

import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Component
import io.github.arthurjordao.locadora.model.Ator
import io.github.arthurjordao.locadora.repository.AtorRepository
import org.vaadin.viritin.fields.MTextField
import org.vaadin.viritin.form.AbstractForm
import org.vaadin.viritin.grid.MGrid
import org.vaadin.viritin.layouts.MVerticalLayout

@SpringUI(path = "/ator")
class GerenciadorDeAtor(val repository: AtorRepository) : TabelaForm<Ator>() {

    override val tabela: MGrid<Ator> = MGrid(Ator::class.java)

    override val formulario: AbstractForm<Ator> = FormularioDeAtor(this)

    override fun atualizaTabela() {
        tabela.setItems(repository.findAll())
    }

    override fun salva(entity: Ator) {
        repository.save(entity)
        atualizaTabela()
    }

    override fun getEntity(id: Long): Ator {
        return if (repository.exists(id))
            repository.findOne(id)
        else
            Ator()
    }

    override fun remove(entity: Ator) {
        if (repository.exists(entity.id))
            repository.delete(entity)
        atualizaTabela()
    }

    override fun configuraTabela() {
        super.configuraTabela()
        tabela.removeColumn("filmes")
        tabela.setColumnOrder("id", "nome")
    }

}

class FormularioDeAtor(val tabela: Tabela<Ator>) : AbstractForm<Ator>(Ator::class.java) {

    private val nome = MTextField("Nome")

    init {
        configuraBotoes()
        entity = Ator()
    }

    private fun configuraBotoes() {
        setSavedHandler {
            tabela.salva(it)
            entity = Ator()
        }
        setResetHandler {
            entity = tabela.getEntity(it.id)
        }
        setDeleteHandler {
            tabela.remove(it)
            entity = Ator()
        }
    }

    override fun createContent(): Component {
        return MVerticalLayout(nome, toolbar)
    }

}
