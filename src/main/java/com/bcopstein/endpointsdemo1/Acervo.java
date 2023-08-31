package com.bcopstein.endpointsdemo1;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
public class Acervo {
    private List<Livro> livros;

    public Acervo() {
        livros = new LinkedList<>();

        livros.add(new Livro(10, "Introdução ao Java", "Huguinho Pato", 2022));
        livros.add(new Livro(20, "Introdução ao Spring-Boot", "Zezinho Pato", 2020));
        livros.add(new Livro(15, "Principios SOLID", "Luizinho Pato", 2023));
        livros.add(new Livro(17, "Padroes de Projeto", "Lala Pato", 2019));
    }

    public List<Livro> getAll() {
        return livros;
    }

    public List<String> getTitulos() {
        return livros.stream()
                .map(livro -> livro.titulo())
                .toList();
    }

    public Set<String> getAutores() {
        return livros.stream()
                .map(livro -> livro.autor())
                .collect(Collectors.toSet());
    }

    public Set<String> getAutoresAno(int ano) {
        return livros.stream()
                .filter(livro -> livro.ano() == ano)
                .map(livro -> livro.autor())
                .collect(Collectors.toSet());
    }

    public List<String> getTitulosAutorAno(@PathVariable(value = "autor") String autor,
                                           @PathVariable(value = "ano") int ano) {
        return livros.stream()
                .filter(livro -> livro.ano() == ano)
                .filter(livro -> livro.autor().equals(autor))
                .map(livro -> livro.titulo())
                .toList();
    }

    public boolean criaLivroNovo(@RequestBody() Livro livro) {
        livros.add(livro);
        return true;
    }

    public Set<Livro> getDesatualizadoAno(@PathVariable(value = "ano") int ano) {
        return livros.stream()
                .filter(livro -> livro.ano() < ano)
                .collect(Collectors.toSet());
    }

}

  

    

