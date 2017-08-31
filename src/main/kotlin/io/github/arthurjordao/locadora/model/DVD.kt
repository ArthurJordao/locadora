package io.github.arthurjordao.locadora.model

import javax.persistence.*

@Entity
data class DVD(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        @ManyToOne
        var filme: Filme = Filme(),
        var alugado: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DVD) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}