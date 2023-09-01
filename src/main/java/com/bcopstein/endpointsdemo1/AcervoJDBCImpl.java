package com.bcopstein.endpointsdemo1;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


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
                (rs, rowNum) ->
                        new Livro(rs.getLong("codigo"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano")));
        return resp;
    }

    @Override
    public boolean removeLivro(long codigo) {
        String sql = "DELETE FROM livros WHERE id = " + codigo;
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
}
