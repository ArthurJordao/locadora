package io.github.arthurjordao.locadora.model

import org.hibernate.validator.constraints.NotBlank
import org.hibernate.validator.constraints.br.CPF
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Pattern

@Entity
data class Cliente(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        @get:NotBlank(message = "Nome não pode ser vazio")
        var nome: String = "",
        @get:Pattern(regexp = "^[0-9]{11}\$", message = "Digite o DDD mais o número")
        var telefone: String = "",
        @get:CPF(message = "Precisa ser um CPF válido")
        var cpf: String = "",
        @get:NotBlank(message = "Endereço não pode ser vazio")
        var endereco: String = ""

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cliente) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}