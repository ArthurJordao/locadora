package io.github.arthurjordao.locadora.ui

import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Component
import io.github.arthurjordao.locadora.model.Cliente
import io.github.arthurjordao.locadora.repository.ClienteRepository
import org.vaadin.viritin.fields.MTextField
import org.vaadin.viritin.form.AbstractForm
import org.vaadin.viritin.grid.MGrid
import org.vaadin.viritin.layouts.MVerticalLayout

@SpringUI(path = "/clientes")
class GerenciadorDeClientesUI(val repository: ClienteRepository) : TabelaForm<Cliente>() {
    override val tabela: MGrid<Cliente> = MGrid<Cliente>(Cliente::class.java)
    override val formulario: AbstractForm<Cliente> = FormularioCliente(this)

    override fun salva(entity: Cliente) {
        repository.save(entity)
        atualizaTabela()
    }

    override fun remove(entity: Cliente) {
        if (repository.exists(entity.id))
            repository.delete(entity)
        atualizaTabela()
    }

    override fun atualizaTabela() {
        tabela.setItems(repository.findAll())
    }

    override fun getEntity(id: Long): Cliente {
        return if (repository.exists(id))
            return repository.findOne(id)
        else
            Cliente()
    }

    override fun configuraTabela() {
        super.configuraTabela()
        tabela.setColumnOrder("id", "nome", "telefone", "cpf", "endereco")
    }

}

class FormularioCliente(val tabela: Tabela<Cliente>) : AbstractForm<Cliente>(Cliente::class.java) {
    private val nome = MTextField("Nome")
    private val telefone = MTextField("Telefone")
    private val cpf = MTextField("Cpf")
    private val endereco = MTextField("Endereço")

    init {
        configuraBotoes()
        entity = Cliente()
    }

    private fun configuraBotoes() {
        setSavedHandler {
            tabela.salva(it)
            entity = Cliente()
        }
        setResetHandler {
            entity = tabela.getEntity(it.id)
        }
        setDeleteHandler {
            tabela.remove(it)
            entity = Cliente()
        }
    }

    override fun createContent(): Component {
        return MVerticalLayout(nome, telefone, cpf, endereco, toolbar)
    }

}
