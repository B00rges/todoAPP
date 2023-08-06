package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Tag;
import util.ConnectionFactory;


public class TagDAO {

    public void save(Tag tag) {
        String sql = "INSERT INTO tags(name, color, createdAt, updatedAt) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            
            conn = ConnectionFactory.getConnection();
           
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, tag.getName());
            stmt.setString(2, tag.getColor());
            stmt.setDate(3, new java.sql.Date(tag.getCreatedAt().getTime()));
            stmt.setDate(4, new java.sql.Date(tag.getUpdatedAt().getTime()));

           
            stmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tag", ex);
        } finally {
           
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }

    }

    public void update(Tag tag) {

        String sql = "UPDATE tags SET name = ?, color = ?, createdAt = ?, updatedAt = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
           
            conn = ConnectionFactory.getConnection();
       
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, tag.getName());
            stmt.setString(2, tag.getColor());
            stmt.setDate(3, new java.sql.Date(tag.getCreatedAt().getTime()));
            stmt.setDate(4, new java.sql.Date(tag.getUpdatedAt().getTime()));
            stmt.setInt(4, tag.getId());

            
            stmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro em atualizar a tag", ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
    }

    public List<Tag> getAll() {
        String sql = "SELECT * FROM tags";

        List<Tag> tags = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;

        
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            rset = stmt.executeQuery();

           
            while (rset.next()) {

                Tag tag = new Tag();

                tag.setId(rset.getInt("id"));
                tag.setName(rset.getString("name"));
                tag.setColor(rset.getString("color"));
                tag.setCreatedAt(rset.getDate("createdAt"));
                tag.setCreatedAt(rset.getDate("updatedAt"));

                
                tags.add(tag);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar os projetos", ex);
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
        return tags;
    }

    public void removeById(int id) {

        String sql = "DELETE FROM tags WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar a tag", ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }

    }

}
