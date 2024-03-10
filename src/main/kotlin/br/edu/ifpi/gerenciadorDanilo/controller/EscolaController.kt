package br.edu.ifpi.gerenciadorDanilo.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class EscolaController {

    @GetMapping("/media")
    fun abrirFormulario(): String{
        return "notamedia/formulario-notas"
    }

    @GetMapping("calcula/situacao")
    fun situacao(model: Model, nome: String, nota1: Double, nota2:Double): String{

        var media: Double = (nota1 + nota2) / 2
        var situacao: String = ""
        var link: String = ""

        if(media >= 7){
            situacao = "Aluno Aprovado"
            link = "https://www.hubpng.com/files/preview/960x1066/png-transparent-thumbs-up-emoji-meme-png-hd-116816957902fsepemwkleenbh35o528lvji5u1zsi5wwlzu39mpd1pqwi63z6wq8t0qmdxilpkprmgeigj6q2egzzborv1g.png"
        }
        else if(media < 7 && media >= 4){
            situacao = "Aluno de Recuperação"
            link = "https://i.pinimg.com/736x/0a/dd/bc/0addbc976e13e746f1273343457ff654.jpg"
        }
        else{
            situacao = "Aluno Reprovado"
            link = "https://media.tenor.com/-wrmUJrUbeoAAAAC/emoji-disintergrating.gif"
        }

        model.addAttribute("nome", nome)
        model.addAttribute("situacao", situacao)
        model.addAttribute("link", link)

        return "notamedia/resultado"
    }
}