package br.edu.ifpi.gerenciadorDanilo.controller
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ConversorController {
    @GetMapping("/converter")
    fun abrirCoversao(): String{
        return "conversao/converter"
    }

    @GetMapping("final/conversao")
    fun conversao(model: Model, valor: Double, moeda: String): String{

        var valorFinal : Double = 0.0

        if(moeda == "EUA") {
            valorFinal = valor / 4.99
        }
        if(moeda == "EU"){
            valorFinal = valor / 5.95
        }
        if(moeda == "PESO") {
            valorFinal = valor / 0.015
        }

        model.addAttribute("valorFinal", valorFinal)

        return "conversao/conversao"
    }
}