package br.edu.ifpi.gerenciadorDanilo.repository


import br.edu.ifpi.gerenciadorDanilo.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface UsuarioBlogRepository: JpaRepository<Usuario, Long> {

//    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    fun findUsuarioByEmail(email : String): Optional<Usuario>

}