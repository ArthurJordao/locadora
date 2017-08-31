package io.github.arthurjordao.locadora

import io.github.arthurjordao.locadora.model.Ator
import io.github.arthurjordao.locadora.model.Filme
import io.github.arthurjordao.locadora.repository.AtorRepository
import io.github.arthurjordao.locadora.repository.FilmeRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class LocadoraApplicationTests {

    @Autowired
    lateinit var atorRepository: AtorRepository
    @Autowired
    lateinit var filmeRepository: FilmeRepository

    @Test
    fun contextLoads() {
        val ator = Ator().apply {
            this.nome = "Arthur"
        }
        val filme = Filme().apply {
            this.titulo = "alo"
        }
        filmeRepository.save(filme)
        ator.filmes.add(filme)
        atorRepository.save(ator)
        println(filmeRepository.findOne(filme.id))
    }

}
