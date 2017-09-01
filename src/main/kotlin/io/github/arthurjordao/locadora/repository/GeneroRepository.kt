package io.github.arthurjordao.locadora.repository

import io.github.arthurjordao.locadora.model.Genero
import org.springframework.data.jpa.repository.JpaRepository

interface GeneroRepository : JpaRepository<Genero, Long>