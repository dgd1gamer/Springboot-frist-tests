package br.edu.ifpi.gerenciadorDanilo.controller

import br.edu.ifpi.gerenciadorDanilo.model.Empregado
import br.edu.ifpi.gerenciadorDanilo.repository.CadastroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.bind.BindResult
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.naming.Binding

@Controller
class CadastroController {

    @Autowired
    lateinit var repositorio : CadastroRepository

    @GetMapping("/cadastro")
    fun abrirFormulario(model : Model) : String{

        val empregado = Empregado()

        model.addAttribute("empregado", empregado)

        return "cadastro/cadastro-empregado"
    }

    @PostMapping("/cadastrar")
    fun cadastrarEmpregado(@Validated empregado: Empregado, result: BindingResult): String{

        if(result.hasErrors()) {
            return "cadastro/cadastro-empregado"
        }

        println(empregado)
        repositorio.save(empregado)

        return "redirect:/empregadohome"
    }

    @GetMapping("empregadohome")
    fun abrirCrud(model : Model): String{

        val usuario = repositorio.findAll()

        model.addAttribute("usuarios", usuario)

        return "cadastro/home"
    }

    @GetMapping("/formulario/edicao/{id}")
    fun abrirEditor(@PathVariable("id") id : Long, model: Model): String{

        val usuario = repositorio.findById(id).orElse(null)

        model.addAttribute("usuario", usuario)

        return "cadastro/edicao-empregado"
    }


    @PostMapping("/editar/{id}")
    fun editarUsario(@PathVariable("id") id : Long, usuario : Empregado): String{

        usuario.id = id

        repositorio.save(usuario)

        return "redirect:/empregadohome"
    }

    @GetMapping("/excluir/{id}")
    fun excluirUsuario(@PathVariable("id") id : Long, usuario : Empregado): String{

        repositorio.deleteById(id)

        return "redirect:/empregadohome"
    }
}