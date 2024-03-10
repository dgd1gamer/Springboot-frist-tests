package br.edu.ifpi.gerenciadorDanilo.repository

import br.edu.ifpi.gerenciadorDanilo.model.Empregado
import org.springframework.data.jpa.repository.JpaRepository

interface CadastroRepository : JpaRepository<Empregado, Long> {

}