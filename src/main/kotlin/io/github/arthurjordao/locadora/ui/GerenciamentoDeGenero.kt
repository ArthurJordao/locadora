package io.github.arthurjordao.locadora.ui

import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Component
import io.github.arthurjordao.locadora.model.Genero
import io.github.arthurjordao.locadora.repository.GeneroRepository
import org.vaadin.viritin.fields.MTextField
import org.vaadin.viritin.form.AbstractForm
import org.vaadin.viritin.grid.MGrid
import org.vaadin.viritin.layouts.MVerticalLayout

@SpringUI(path = "/generos")
class GerenciamentoDeGenero(val repository: GeneroRepository) : TabelaForm<Genero>() {
    override val tabela: MGrid<Genero> = MGrid(Genero::class.java)
    override val formulario: AbstractForm<Genero> = GeneroForm(this)

    override fun atualizaTabela() {
        tabela.setItems(repository.findAll())
    }

    override fun salva(entity: Genero) {
        repository.save(entity)
        atualizaTabela()
    }

    override fun remove(entity: Genero) {
        if (repository.exists(entity.id))
            repository.delete(entity.id)
        atualizaTabela()
    }

    override fun getEntity(id: Long): Genero {
        return if (repository.exists(id))
            repository.findOne(id)
        else
            Genero()
    }
}

class GeneroForm(val tabela: Tabela<Genero>) : AbstractForm<Genero>(Genero::class.java) {
    private val nome = MTextField("Nome")

    init {
        configuraBotoes()
        entity = Genero()
    }

    private fun configuraBotoes() {
        setSavedHandler {
            tabela.salva(it)
            entity = Genero()
        }
        setResetHandler {
            entity = tabela.getEntity(it.id)
        }
        setDeleteHandler {
            tabela.remove(it)
            entity = Genero()
        }
    }

    override fun createContent(): Component {
        return MVerticalLayout(nome, toolbar)
    }

}
