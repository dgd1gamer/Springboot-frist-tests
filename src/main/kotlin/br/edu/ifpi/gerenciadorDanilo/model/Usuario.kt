package br.edu.ifpi.gerenciadorDanilo.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
data class Usuario(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,

    val nome : String = "",

    val nick : String = "",

    val email : String = "",

    val senha : String = "",

    var imagemPerfil : String = ""
) {
}