package br.edu.ifpi.gerenciadorDanilo.repository

import br.edu.ifpi.gerenciadorDanilo.model.Post
import org.springframework.data.jpa.repository.JpaRepository


interface PostRepository : JpaRepository<Post, Long>{

    fun findPostsByUsuarioEmail(email : String): List<Post>

}