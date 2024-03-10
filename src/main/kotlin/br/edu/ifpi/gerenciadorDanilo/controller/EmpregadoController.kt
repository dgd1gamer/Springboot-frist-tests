package br.edu.ifpi.gerenciadorDanilo.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class EmpregadoController {

    @GetMapping("/")
    fun abrirMenu (): String{
        return "menu"
    }
    @GetMapping("/reajuste")
    fun abrirTelaReajuste(): String{
        return "reajuste/reajuste"
    }
    @GetMapping("calcula/reajuste")
    fun calcularReajuste(model: Model, nome: String, salario: Double): String{


        var salarioReajustado: Double
        if(salario <= 1200){
            salarioReajustado = salario * 1.50
        }
        else if(salario > 1200 && salario <= 1300){
            salarioReajustado = salario * 1.20
        }
        else{
            salarioReajustado = salario * 1.15
        }

        model.addAttribute("novoSalario", salarioReajustado)
        model.addAttribute("nomeEmpregado", nome)
        return "reajuste/resultado"
    }
}