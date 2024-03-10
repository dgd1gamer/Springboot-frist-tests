package br.edu.ifpi.gerenciadorDanilo.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Post(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val titulo: String = "",
    val conteudo: String = "",
    var imagemUrl: String? = null,
    var data: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    var usuario: Usuario? = null,

    @OneToMany(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER )
    @JoinColumn(name = "post_id")
    val comentarios: MutableList<Comentario> = mutableListOf()
) {
}