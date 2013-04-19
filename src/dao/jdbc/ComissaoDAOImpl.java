/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.jdbc;



import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.connection.ConnectionFactory;
import dao.connection.Connetion;

import model.Commission;

import exception.DaoException;
import exception.MessageException;


/**
 *
 * @author Fogel
 */
public class ComissaoDAOImpl implements ComissaoDAO{

      @Override
      public void salvarComissao(Commission comissao) throws DaoException{

        try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call atualizarcomissao(?,?,?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setString(1, comissao.getLoginPrimeiroMembro());
                ps.setString(2, comissao.getLoginSegundoMembro());
                ps.setString(3, comissao.getLoginPresidente());

                ResultSet rs = ps.executeQuery();

                pConexao.desconectar();

        } catch (ClassNotFoundException e){
                throw new DaoException(MessageException.CLASS_EXCEPTION);
        } catch (SQLException e) {
                throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
        }

	}

      @Override
      public Commission consultarComissao() throws DaoException {
            Commission comissao = null;
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call consultar1membro()}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ResultSet rs = ps.executeQuery();

                comissao = new Commission();

                if ( !rs.wasNull() )
                      while ( rs.next() )
                              comissao.setLoginPrimeiroMembro(rs.getString(1));

                sql = "{call consultar2membro()}";

                ps = pConexao.getConnection().prepareCall(sql);

                rs = ps.executeQuery();

                if ( !rs.wasNull() )
                      while ( rs.next() )
                              comissao.setLoginSegundoMembro(rs.getString(1));

                sql = "{call consultarpresidente()}";

                ps = pConexao.getConnection().prepareCall(sql);

                rs = ps.executeQuery();

                if ( !rs.wasNull() )
                      while ( rs.next() )
                              comissao.setLoginPresidente(rs.getString(1));

                pConexao.desconectar();

              } catch (ClassNotFoundException e){
                      throw new DaoException(MessageException.CLASS_EXCEPTION);
              } catch (SQLException e) {
                      throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
              }
            
            return comissao;
      }

}
