package io.github.arthurjordao.locadora

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class LocadoraApplication

fun main(args: Array<String>) {
    SpringApplication.run(LocadoraApplication::class.java, *args)
}
