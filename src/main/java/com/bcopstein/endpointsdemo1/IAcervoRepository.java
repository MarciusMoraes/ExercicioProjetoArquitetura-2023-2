package com.bcopstein.endpointsdemo1;

import java.util.List;

public interface IAcervoRepository {
    List<Livro> getAll();
    Livro getPorId(int id);
    List<Livro> getAutor(String autor);
    List<Livro> getTitulo(String titulo);
    List<Livro> getAno(int ano);
    boolean cadastraLivroNovo(Livro livro);
    boolean removeLivro(int id);
}
