package br.edu.ifpi.gerenciadorDanilo.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.br.CPF
@Entity
data class Empregado(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,

    @field:Size(min=3, message = "Digite o nome completo!")
    @field:NotBlank(message = "Digite um nome!")
    val nome : String = "",

    @field:Email(message = "Digite um Email valido")
    @field:NotBlank(message = "Digite um nome!")
    val email : String = "",

    @field:NotBlank(message = "Digite um CPF!")
    @field:CPF(message = "Digite um CPF valido!")
    val cpf : String = "",

    val nascimento : String =  "",

    @field:Min(1200, message = "O salario Mininimo é de R$ 1200")
    val salario : Double = 0.0,

    val sexo : String = "",

    val setor : String = ""
)
