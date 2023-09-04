package com.bcopstein.endpointsdemo1;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/biblioteca")
public class DemoController {
    private IAcervoRepository acervo;

    @Autowired
    public DemoController(IAcervoRepository acervo) {
        this.acervo = acervo;
    }

    @GetMapping("/")
    @CrossOrigin(origins = "*")
    public String getSaudacao() {
        return "Bem vindo a biblioteca!";
    }

    @GetMapping("/livros")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivros() {
        return acervo.getAll();
    }

    @GetMapping("/tituloAutor")
    @CrossOrigin(origins = "*")
    public List<Livro> getAutor(@RequestParam(value = "autor") String autor) {
        return acervo.getAutor(autor);
    }

    @GetMapping("/removeLivro")
    @CrossOrigin(origins = "*")
    public Boolean removeLivro(@RequestParam(value = "codigo") int codigo) {
        return acervo.removeLivro(codigo);
    }

    @GetMapping("/autoresAno")
    @CrossOrigin(origins = "*")
    public List<Livro> getAno(@RequestParam(value = "ano") int ano) {
        return acervo.getAno(ano);
    }

    @GetMapping("/desatualizados")
    @CrossOrigin(origins = "*")
    public List<Livro> getDesatualizadoAno(@RequestParam(value = "ano") int ano) {
        return acervo.getDesatualizadoAno(ano);
    }

    @GetMapping("/getId")
    @CrossOrigin(origins = "*")
    public Livro getPorId(@RequestParam(value = "id") int id) {
        return acervo.getPorId(id);
    }

    @GetMapping("/titulo")
    @CrossOrigin(origins = "*")
    public Livro getTitulo(@RequestParam(value = "titulo") String titulo) {
        return acervo.getTitulo(titulo);
    }

    @GetMapping("/obrasPorAutor")
    @CrossOrigin(origins = "*")
    public int obrasAutor(@RequestParam(value = "autor") String autor) {
        return acervo.obrasAutor(autor);
    }

    @GetMapping("/atualizadosAno")
    @CrossOrigin(origins = "*")
    public int atualizadosAno(@RequestParam(value = "ano") int ano) {
        return acervo.atualizadosAno(ano);
    }

    @GetMapping("/mediaObrasAutor")
    @CrossOrigin(origins = "*")
    public double mediaObrasAutor(@RequestParam(value = "autor") String autor) {
        return acervo.mediaObrasAutor(autor);
    }
    /*
     * 
     * 
     * @GetMapping("/titulos")
     * 
     * @CrossOrigin(origins = "*")
     * public List<String> getTitulos(){
     * return acervo.getTitulos();
     * }
     * 
     * @GetMapping("/autoresAno")
     * 
     * @CrossOrigin(origins = "*")
     * public Set<String> getAutoresAno(@RequestParam(value = "ano") int ano) {
     * return acervo.getAutoresAno(ano);
     * }
     * 
     * @GetMapping("/tituloAutor/{autor}/ano/{ano}")
     * 
     * @CrossOrigin(origins = "*")
     * public List<String> getTitulosAutorAno(@PathVariable(value = "autor") String
     * autor,
     * 
     * @PathVariable(value = "ano") int ano) {
     * return acervo.getTitulosAutorAno(autor, ano);
     * }
     * 
     * @GetMapping("/criaLivro")
     * public boolean criaLivroNovo(@RequestBody() Livro livro) {
     * acervo.criaLivroNovo(livro);
     * return true;
     * }
     * 
     */
}