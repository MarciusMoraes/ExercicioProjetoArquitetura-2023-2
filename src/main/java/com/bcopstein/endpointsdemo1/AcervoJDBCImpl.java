package com.bcopstein.endpointsdemo1;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class AcervoJDBCImpl implements IAcervoRepository {
 
    private List<Livro> livros;

    public AcervoJDBCImpl(){
        livros = new LinkedList<>();

        livros.add(new Livro(10,"Introdução ao Java","Huguinho Pato",2022));
        livros.add(new Livro(20,"Introdução ao Spring-Boot","Zezinho Pato",2020));
        livros.add(new Livro(15,"Principios SOLID","Luizinho Pato",2023));
        livros.add(new Livro(17,"Padroes de Projeto","Lala Pato",2019));
    }

    @Override
    public List<Livro>getAll(){  return livros; }

    @Override
    public boolean cadastraLivroNovo(Livro livro) {
        livros.add(livro);
        return true;
    }

    @Override
    public boolean removeLivro(int codigo) {
        List<Livro> tmp = livros.stream()
            .filter(livro->livro.codigo() == codigo)
            .toList();
        return tmp.removeAll(tmp);
    }

    @Override
    public Livro getPorId(int id){
        return livros.get(id);
    };

    @Override
    public List<Livro> getAutor(String autor){
        return livros.stream()
            .filter(livro->livro.autor() == autor)
            .toList();
    };


    public Livro getTitulo(String titulo){
        return livros.stream()
            .filter(livro->livro.titulo() == titulo);
    }

    public List<Livro> getAno(int ano){
        return livros;
    }
}
