package br.edu.ifpi.gerenciadorDanilo.model

import jakarta.persistence.*

@Entity
class Comentario(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    val texto : String = "",


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    var usuario: Usuario? = null

) {
}