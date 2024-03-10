package br.edu.ifpi.gerenciadorDanilo.controller
import br.edu.ifpi.gerenciadorDanilo.model.Comentario
import br.edu.ifpi.gerenciadorDanilo.model.Post
import br.edu.ifpi.gerenciadorDanilo.model.Usuario
import br.edu.ifpi.gerenciadorDanilo.repository.PostRepository
import br.edu.ifpi.gerenciadorDanilo.repository.UsuarioBlogRepository
import br.edu.ifpi.gerenciadorDanilo.util.FileUtil
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime


@Controller
class BlogController {

    @Autowired
    private lateinit var usuarioRepository: UsuarioBlogRepository

    @Autowired
    private lateinit var postRepository: PostRepository


//    PARTE DE REGISTRO!!!!

    @GetMapping("/registro")
    fun abrirRegistro(model: Model): String{

        val usuario = Usuario()
        model.addAttribute("usuario", usuario)

        return "blog/registro"
    }

    @PostMapping("/cadastrar/usuario")
    fun registrarUsuario(
        usuario: Usuario,
        @RequestParam("image") file : MultipartFile,
    ): String{

        val imagemUrl = FileUtil.create().saveFile(file)

        usuario.imagemPerfil = imagemUrl

        usuarioRepository.save(usuario)

        println(usuario)

        return "redirect:/login"
    }

//    PARTE DE LOGIN

    @GetMapping("/login")
    fun abrirLogin(model: Model, request : HttpServletRequest): String{

        val session = request.session

        if (session.getAttribute("usuarioLogado") != null){
            return "redirect:/home"
        }

        val usuario = Usuario()
        model.addAttribute("usuario", usuario)

        return "blog/login"
    }


    @PostMapping("/logar")
    fun logarUsuario(usuario: Usuario, request : HttpServletRequest): String{

        val session = request.session

        val usuarioDB = usuarioRepository.findUsuarioByEmail(usuario.email).orElseGet { null }
            ?: return "redirect:/login?error"

        return if(usuarioDB.senha == usuario.senha){
            session.setAttribute("usuarioLogado", usuarioDB)
            "redirect:/home"
        } else {
            "redirect:/login?error"
        }

    }

//    PARTE DA HOME

    @GetMapping("/home")
    fun abrirHome(model: Model): String{

        val posts = postRepository.findAll().reversed()

        model.addAttribute("posts", posts)

        return "/blog/home"
    }

//    PARTE LOGOUT

    @GetMapping("/logout")
    fun logoutUser(request : HttpServletRequest): String {

        val session = request.session

        session.invalidate()

        return "redirect:/login"
    }

//    CRIAR POSTAGENS

    @GetMapping("/new/post")
    fun abrirNewPost(model: Model): String{

        val post = Post()

        model.addAttribute("post", post)

        return "/blog/novo-post"
    }

    @PostMapping("/postar")
    fun criarPost(
        post: Post,
        @RequestParam("image") imagem : MultipartFile,
        request: HttpServletRequest
    ) : String{

        val imagemUrl = FileUtil.create().saveFile(imagem)

        post.imagemUrl = imagemUrl.toString()
        post.data = LocalDateTime.now()

        post.usuario = request.session.getAttribute("usuarioLogado") as Usuario

        postRepository.save(post)

        println(post)

        return "redirect:/home"
    }

//    PARTE DOS MEUS POSTS


    @GetMapping("/meus/post")
    fun abrirMeusPosts(request: HttpServletRequest, model: Model): String{

        val usuarioLogado = request.session.getAttribute("usuarioLogado") as Usuario

        val posts = postRepository.findPostsByUsuarioEmail(usuarioLogado.email)

        model.addAttribute("posts", posts)

        return "blog/meus-posts"
    }

//    PARTE DOS COMENTARIOS


    @GetMapping("/post/comentarios/{idPost}")
    fun abrirComentarios(@PathVariable("idPost") id : Long, model: Model, request: HttpServletRequest) : String{

        val post = postRepository.findById(id).orElse(null)

        val comentario = Comentario()



        model.addAttribute("post", post)
        model.addAttribute("comentario", comentario)

        return "blog/comentarios-post"
    }


    @PostMapping("/comentar/{idPost}")
    fun adicionarComentario(@PathVariable("idPost") id : Long, comentario: Comentario, request: HttpServletRequest) : String {

        if(comentario.id != null) comentario.id = null;

        comentario.usuario = request.session.getAttribute("usuarioLogado") as Usuario

        val post = postRepository.findById(id).orElse(null)

        post.comentarios.add(comentario)

        postRepository.save(post)

        return "redirect:/post/comentarios/${id}"
    }

}