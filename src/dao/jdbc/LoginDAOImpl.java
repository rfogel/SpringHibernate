/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.jdbc;



import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.connection.ConnectionFactory;
import dao.connection.Connetion;

import model.Login;

import exception.DaoException;
import exception.MessageException;


/**
 *
 * @author Fogel
 */
public class LoginDAOImpl implements LoginDAO{

      @Override
      public Login logar(Login login) throws DaoException
      {
            Login log = null;
            try {
                  Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                  pConexao.conectar();

                  String sql = "select u.tipo as tipoUsuario, l.id_login as id, "
                        + "l.username as username, l.password as password "
                        + "from login l, usuario u "
                        + "where l.username = ? and l.password = ? "
                        + "and l.id_login = u.login";

                  PreparedStatement ps = pConexao.getConnection().prepareStatement(sql);

                  ps.setString(1, login.getUsername());
                  ps.setString(2, login.getPassword());

                  ResultSet rs = ps.executeQuery();

                  while ( rs.next() )
                  {
                        log = new Login();
                        log.setUsername(rs.getString("username"));
                        log.setPassword(rs.getString("password"));
                        log.setTipoUsuario(rs.getInt("tipoUsuario"));
                        log.setId(rs.getInt("id"));
                  }

                  ps.close();
                  pConexao.desconectar();

            } catch (ClassNotFoundException e){
                  throw new DaoException(MessageException.CLASS_EXCEPTION);
            } catch (SQLException e) {
                  throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
            }

            return log;
   }

      @Override
      public List<Login> listarLogin() throws DaoException
      {
            List<Login> lisLog = null;
            try {
                  Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                  pConexao.conectar();

                  String sql = "{call listarlogin()}";

                  CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                  ResultSet rs = ps.executeQuery();

                  Login log = null;
                  if ( !rs.wasNull() )
                  {
                      lisLog = new ArrayList<Login>();
                      while ( rs.next() )
                      {
                            log = new Login();
                            log.setUsername(rs.getString("username"));
                            log.setPassword(rs.getString("password"));
                            log.setId(rs.getInt("id_login"));
                            lisLog.add(log);
                      }
                  }

                  ps.close();
                  pConexao.desconectar();

            } catch (ClassNotFoundException e){
                throw new DaoException(MessageException.CLASS_EXCEPTION);
            } catch (SQLException e) {
                throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
            }

            return lisLog;
   }

      @Override
      public void inserirLogin(Login login) throws DaoException {
        try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call inserirlogin(?,?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setString(1, login.getUsername());
                ps.setString(2, login.getPassword());

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
      public void removerLogin(int id) throws DaoException {
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call removerlogin(?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setInt(1, id);

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
      public void editarLogin(Login login) throws DaoException {
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call alterarlogin(?,?,?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setInt(1, login.getId());
                ps.setString(2, login.getUsername());
                ps.setString(3, login.getPassword());

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
      public Login consultarLogin(int id) throws DaoException {
            Login log = null;
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call consultarlogin(?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if ( !rs.wasNull() )
                {
                      log = new Login();
                      while ( rs.next() )
                      {
                            log.setUsername(rs.getString("username"));
                            log.setPassword(rs.getString("password"));
                            log.setId(rs.getInt("id_login"));
                      }
                }

                ps.close();
                pConexao.desconectar();

            } catch (ClassNotFoundException e){
                throw new DaoException(MessageException.CLASS_EXCEPTION);
            } catch (SQLException e) {
                throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
            }

            return log;
      }

      @Override
      public boolean consultarLogin(String username) throws DaoException {
            boolean jaExiste = false;
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call consultarlogin(?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setString(1, username);

                ResultSet rs = ps.executeQuery();

                String nomeUsuario = null;
                while ( rs.next() )
                      nomeUsuario = rs.getString("username");

                if ( nomeUsuario != null )
                      jaExiste = true;
                
                ps.close();
                pConexao.desconectar();

            } catch (ClassNotFoundException e){
                throw new DaoException(MessageException.CLASS_EXCEPTION);
            } catch (SQLException e) {
                throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
            }

            return jaExiste;
      }
}
