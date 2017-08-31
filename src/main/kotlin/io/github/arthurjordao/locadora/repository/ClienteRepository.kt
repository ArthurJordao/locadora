package io.github.arthurjordao.locadora.repository

import io.github.arthurjordao.locadora.model.Cliente
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository : JpaRepository<Cliente, Long>