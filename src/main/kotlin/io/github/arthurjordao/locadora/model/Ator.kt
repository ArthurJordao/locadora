package io.github.arthurjordao.locadora.model

import javax.persistence.*

@Entity
data class Ator(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        var nome: String = "",
        @ManyToMany
        var filmes: MutableList<Filme> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Ator) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}