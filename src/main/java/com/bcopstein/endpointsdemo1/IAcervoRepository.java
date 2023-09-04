package com.bcopstein.endpointsdemo1;

import java.util.List;

public interface IAcervoRepository {
    List<Livro> getAll();

    Livro getPorId(int codigo);

    List<Livro> getAutor(String autor);

    Livro getTitulo(String titulo);

    List<Livro> getAno(int ano);

    boolean cadastraLivroNovo(Livro livro);

    boolean removeLivro(int codigo);

    List<Livro> getDesatualizadoAno(int ano);

    int obrasAutor(String autor);

    int atualizadosAno(int ano);

    double mediaObrasAutor(String autor);
}
