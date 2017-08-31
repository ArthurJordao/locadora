package io.github.arthurjordao.locadora.model

import javax.persistence.*

@Entity
data class Filme(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        var titulo: String = "",
        @ManyToOne
        var genero: Genero = Genero(),
        @ManyToMany(mappedBy = "filmes")
        var estrelas: MutableList<Ator> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Filme) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}