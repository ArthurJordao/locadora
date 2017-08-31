package io.github.arthurjordao.locadora.repository

import io.github.arthurjordao.locadora.model.Filme
import org.springframework.data.jpa.repository.JpaRepository

interface FilmeRepository : JpaRepository<Filme, Long>