package io.github.arthurjordao.locadora.model

import javax.persistence.*

@Entity
data class Genero(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        var nome: String = "",
        @ManyToMany(mappedBy = "genero")
        var filmes: MutableSet<Filme> = mutableSetOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Genero) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}