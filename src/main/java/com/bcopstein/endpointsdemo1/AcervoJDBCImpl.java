package com.bcopstein.endpointsdemo1;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
@Primary
public class AcervoJDBCImpl implements IAcervoRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AcervoJDBCImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Livro> getAll() {
        List<Livro> resp = this.jdbcTemplate.query("SELECT * from livros",
                (rs, rowNum) -> new Livro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("autor"),
                        rs.getInt("ano")));
        return resp;
    }

    @Override
    public boolean removeLivro(int codigo) {
        String sql = "DELETE FROM livros WHERE codigo = " + codigo;
        this.jdbcTemplate.batchUpdate(sql);
        return true;
    }

    @Override
    public boolean cadastraLivroNovo(Livro livro) {
        this.jdbcTemplate.update(
                "INSERT INTO livros(codigo,titulo,autor,ano) VALUES (?,?,?,?)",
                livro.codigo(), livro.titulo(), livro.autor(), livro.ano());
        return true;
    }

    @Override
    public Livro getPorId(int codigo) {
        List<Livro> resp = this.jdbcTemplate.query("SELECT * from livros WHERE codigo=" + codigo,
                (rs, rowNum) -> new Livro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("autor"),
                        rs.getInt("ano")));
        return resp.get(0);
    }

    @Override
    public List<Livro> getAutor(String autor) {
        List<Livro> resp = this.jdbcTemplate.query("SELECT * from livros WHERE autor='" + autor + "'",
                (rs, rowNum) -> new Livro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("autor"),
                        rs.getInt("ano")));
        return resp;
    }

    @Override
    public List<Livro> getAno(int ano) {
        List<Livro> resp = this.jdbcTemplate.query("SELECT * from livros WHERE ano=" + ano,
                (rs, rowNum) -> new Livro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("autor"),
                        rs.getInt("ano")));
        return resp;
    }

    @Override
    public List<Livro> getDesatualizadoAno(int ano) {
        List<Livro> resp = this.jdbcTemplate.query("SELECT * from livros WHERE ano < " + ano,
                (rs, rowNum) -> new Livro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("autor"),
                        rs.getInt("ano")));
        return resp;
    }

    @Override
    public Livro getTitulo(String titulo) {
        List<Livro> resp = this.jdbcTemplate.query("SELECT * from livros WHERE titulo ='" + titulo + "'",
                (rs, rowNum) -> new Livro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("autor"),
                        rs.getInt("ano")));
        return resp.get(0);
    }

    @Override
    public int obrasAutor(String autor) {
        List<Livro> resp = this.jdbcTemplate.query("SELECT * from livros WHERE autor ='" + autor + "'",
                (rs, rowNum) -> new Livro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("autor"),
                        rs.getInt("ano")));
        return resp.size();
    }

    @Override
    public int atualizadosAno(int ano) {
        List<Livro> resp = this.jdbcTemplate.query("SELECT * from livros WHERE ano >= " + ano,
                (rs, rowNum) -> new Livro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("autor"),
                        rs.getInt("ano")));
        return resp.size();
    }

    @Override
    public double mediaObrasAutor(String autor) {
        int n = this.obrasAutor(autor);

        List<Livro> denominador = this.jdbcTemplate.query("SELECT * from livros",
                (rs, rowNum) -> new Livro(rs.getInt("codigo"), rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano")));

        double media = n / denominador.size();
        return media;
    }
    // @Override
    // public boolean removeLivro(int codigo) {
    // // Implemente a lógica para remover um livro por código do banco de dados
    // }

}
