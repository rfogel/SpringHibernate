/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.jdbc;



import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.connection.ConnectionFactory;
import dao.connection.Connetion;

import model.Login;
import model.User;

import exception.DaoException;
import exception.MessageException;


/**
 *
 * @author Fogel
 */
public class PerfilDAOImpl implements PerfilDAO{

      @Override
      public void atualizarPerfil(User perfil) throws DaoException {
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call atualizarperfil(?,?,?,?,?,?,?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setInt(1, perfil.getId());
                ps.setString(2, perfil.getNome());
                ps.setString(3, perfil.getEmail());
                ps.setString(4, perfil.getNomeProjeto());

                if ( perfil.getValidadeProjetoMes() == null )
                      ps.setNull(5, java.sql.Types.INTEGER);
                else
                      ps.setInt(5, perfil.getValidadeProjetoMes());
                if ( perfil.getValidadeProjetoAno() == null )
                      ps.setNull(6, java.sql.Types.INTEGER);
                else
                      ps.setInt(6, perfil.getValidadeProjetoAno());
                
                ps.setString(7, perfil.getTipoProfessor());

                ResultSet rs = ps.executeQuery();

                ps.close();
                pConexao.desconectar();

            } catch (ClassNotFoundException e){
                throw new DaoException(MessageException.CLASS_EXCEPTION);
            } catch (SQLException e) {
                throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
            }
      }

      @Override
      public User consultarPerfil(int id) throws DaoException {
            User perfil = null;
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call consultarperfil(?)}";
                
                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if ( !rs.wasNull() )
                {
                      perfil= new User();
                      while ( rs.next() )
                      {
                            perfil.setId(rs.getInt("id_usuario"));
                            perfil.setNome(rs.getString("nome"));
                            perfil.setEmail(rs.getString("email"));
                            perfil.setNomeProjeto(rs.getString("nome_projeto"));
                            perfil.setValidadeProjetoMes(rs.getInt("validade_projeto_mes"));
                            perfil.setValidadeProjetoAno(rs.getInt("validade_projeto_ano"));
                      }
                }

                sql = "{call consultartipoprofessor(?)}";

                ps = pConexao.getConnection().prepareCall(sql);

                ps.setInt(1, id);

                rs = ps.executeQuery();

                if ( !rs.wasNull() )
                      while ( rs.next() )
                            perfil.setTipoProfessor(rs.getString(1));


                ps.close();
                pConexao.desconectar();

            } catch (ClassNotFoundException e){
                throw new DaoException(MessageException.CLASS_EXCEPTION);
            } catch (SQLException e) {
                throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
            }

            return perfil;
      }

      @Override
      public List<String> listarTipoProfessor() throws DaoException {
            List<String> lisTipoProfessor = null;
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call listartipoprofessor()}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ResultSet rs = ps.executeQuery();

                if ( !rs.wasNull() )
                {
                      lisTipoProfessor = new ArrayList<String>();
                      while ( rs.next() )
                            lisTipoProfessor.add(rs.getString(1));
                }

                ps.close();
                pConexao.desconectar();

            } catch (ClassNotFoundException e){
                throw new DaoException(MessageException.CLASS_EXCEPTION);
            } catch (SQLException e) {
                throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
            }

            return lisTipoProfessor;
      }

      @Override
      public List<User> listarPerfil() throws DaoException {
            List<User> lisPerfil = null;
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "select u.nome, u.email, l.id_login, l.username from usuario u, login l "
                        + "where u.login = l.id_login";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ResultSet rs = ps.executeQuery();

                if ( !rs.wasNull() )
                {
                      lisPerfil = new ArrayList<User>();
                      while ( rs.next() )
                      {
                            User perfil = new User();
                            Login login = new Login();

                            login.setId(rs.getInt("id_login"));
                            login.setUsername(rs.getString("username"));
                            perfil.setNome(rs.getString("nome"));
                            perfil.setEmail(rs.getString("email"));
                            perfil.setLogin(login);

                            lisPerfil.add(perfil);
                      }
                }

                ps.close();
                pConexao.desconectar();

            } catch (ClassNotFoundException e){
                throw new DaoException(MessageException.CLASS_EXCEPTION);
            } catch (SQLException e) {
                throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
            }

            return lisPerfil;
      }
}
