/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.hibernate;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connection.Conexao;
import connection.ConnectionFactory;

import model.Login;
import model.User;

import exception.DAOException;
import exception.Mensagens;


/**
 *
 * @author Fogel
 */
public class UsuarioDAOImpl implements UsuarioDAO{

    public Login logar(Login login) throws DAOException{

        Login log = null;
        try {
                Conexao pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "select u.tipo as tipoUsuario,"
                        + "l.username as username, l.password as password from login l, usuario u,"
                        + "tipo_usuario tu where l.username = ? and l.password = ? "
                        + "and l.id_login = u.login and u.id_usuario = tu.id_tipo_usuario";

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
                }

                ps.close();
                pConexao.desconectar();

        } catch (ClassNotFoundException e){
                throw new DAOException(Mensagens.CLASS_EXCEPTION);
        } catch (SQLException e) {
                throw new DAOException(Mensagens.DAO_EXCEPTION + this.getClass().getSimpleName());
        }

        return log;
	}

      @Override
      public List<User> listarUsuario() throws DAOException {
            throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public void removerUsuario(int id) throws DAOException {
            throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public void inserirUsuario(int id) throws DAOException {
            throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public void alterarUsuario(int id, User usuario) throws DAOException {
            throw new UnsupportedOperationException("Not supported yet.");
      }
}
