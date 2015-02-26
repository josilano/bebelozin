/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.DAO;

import edu.br.bebelozin.Bean.Usuario;
import edu.br.bebelozin.Factory.ConnectionFactory;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author 1946810
 */
public class UsuarioDAO {
    
    private Connection connection;
    private Usuario livro;
    
    public UsuarioDAO() 
            throws ClassNotFoundException{
        this.connection = new ConnectionFactory().getConnection();
        System.out.println("Conectado!");
        livro = new Usuario();
    }
    

    public boolean cadastraLivro(Usuario livro){             
        String sql = "INSERT INTO livro (liv_titulo, liv_ano, liv_genero)"
            + "VALUES (?, ?, ?)";
                    
                try (PreparedStatement ps = connection.prepareStatement(sql)){
//                    ps.setString(1, livro.getTitulo());
//                    ps.setString(2, livro.getAno());
//                    ps.setString(3, livro.getGenero());
                                           
                        int retornos = ps.executeUpdate();
                            if(retornos == 1){
                                return true;
                            }
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return false;
    }
    
    public Usuario consultaLivro(Usuario livro){
        String sqls = "SELECT * FROM livro WHERE liv_titulo = ?";
         
        try (PreparedStatement pst = connection.prepareStatement(sqls)){
            //pst.setString(1, livro.getTitulo());
            
            ResultSet result = pst.executeQuery();
                if(result.next()){
//                    livro.setCodigo(result.getInt("liv_id"));
//                    livro.setTitulo(livro.getTitulo());
//                    livro.setGenero(result.getString("liv_genero"));
//                    livro.setAno(result.getString("liv_ano"));
                    
                    return livro;
                }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    
    public List<Usuario> listaLivro(){
        String sqls = "SELECT * FROM livro";
         
        List<Usuario> lista = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement(sqls)){
            
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Usuario livronew = new Usuario();
//                    livronew.setCodigo(result.getInt("liv_id"));
//                    livronew.setTitulo(result.getString("liv_titulo"));
//                    livronew.setGenero(result.getString("liv_genero"));
//                    livronew.setAno(result.getString("liv_ano"));
//                    livronew.setComentarios(result.getString("liv_coment"));
//                    livronew.setEditora(result.getString("liv_editora"));
//                    livronew.setEdicao(result.getString("liv_edicao"));
                    lista.add(livronew);
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    
    public boolean atualizaLivro(Usuario livro){
        String sql = "UPDATE livro SET liv_titulo = ?, liv_ano = ?, liv_genero = ? "
                + "WHERE liv_id = ?";
                    
                try (PreparedStatement ps = connection.prepareStatement(sql)){
//                    ps.setString(1, livro.getTitulo());
//                    ps.setString(2, livro.getAno());
//                    ps.setString(3, livro.getGenero());
//                    ps.setInt(4, livro.getCodigo());

                        int retornos = ps.executeUpdate();
                            if(retornos == 1){
                                return true;
                            }
                } catch (SQLException ex) {
                        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(connection != null){
                        try {
                            connection.close();
                            System.out.println("Desconectado com banco!");
                        } catch (SQLException ex) {
                            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
       
        return false;
    }
    
    public boolean excluiLivro(Usuario livro){
        String sql = "DELETE FROM livro WHERE liv_titulo = ?";
                    
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                //ps.setString(1, livro.getTitulo());
                
                    int retorno = ps.executeUpdate();
                        if(retorno == 1){
                            return true;
                        }
            } catch (SQLException ex) {
                        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                    if(connection != null){
                        try {
                            connection.close();
                            System.out.println("Desconectado com banco!");
                        } catch (SQLException ex) {
                            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
       
        return false;
    }
    
    public Usuario getUsuario(Usuario usuario){
        String sql = "SELECT * FROM usuario WHERE usu_nome = ? AND usu_senha = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getNomeUsuario());
            ps.setString(2, usuario.getSenha());
            ResultSet result = ps.executeQuery();
                if(result.next()){
                    Usuario usuarioLogado = new Usuario();
                    usuarioLogado.setNomeUsuario(usuario.getNomeUsuario());
                    usuarioLogado.setSenha(usuario.getSenha());
                    usuarioLogado.setIdUsuario(result.getInt("usu_id"));
                    usuarioLogado.setTel(result.getString("usu_tel"));
                    
                    return usuarioLogado;  
                }
        }catch(SQLException e){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Desconectado!");
            }
        }
        return null;
    }
}
