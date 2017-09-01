package io.github.arthurjordao.locadora.model

import java.util.*
import javax.persistence.*

@Entity
data class Aluguel(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        @Temporal(TemporalType.DATE)
        var dataDeRetirada: Date = Date(),
        @Temporal(TemporalType.DATE)
        var dataDeEntrega: Date = Date(),
        @ManyToMany
        var dvds: MutableSet<DVD> = mutableSetOf(),
        @ManyToOne
        var cliente: Cliente = Cliente()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Aluguel) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}