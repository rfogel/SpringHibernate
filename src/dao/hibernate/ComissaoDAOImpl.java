/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.hibernate;



import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import connection.ConnectionFactory;

import model.Commission;

import exception.DAOException;
import exception.Mensagens;


/**
 *
 * @author Fogel
 */
public class ComissaoDAOImpl implements ComissaoDAO{

      @Override
      public void salvarComissao(Commission comissao) throws DAOException{

        try {
                Conexao pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call atualizarcomissao(?,?,?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setString(1, comissao.getLoginPrimeiroMembro());
                ps.setString(2, comissao.getLoginSegundoMembro());
                ps.setString(3, comissao.getLoginPresidente());

                ResultSet rs = ps.executeQuery();

                pConexao.desconectar();

        } catch (ClassNotFoundException e){
                throw new DAOException(Mensagens.CLASS_EXCEPTION);
        } catch (SQLException e) {
                throw new DAOException(Mensagens.DAO_EXCEPTION + this.getClass().getSimpleName());
        }

	}

      @Override
      public Commission consultarComissao() throws DAOException {
            Commission comissao = null;
            try {
                Conexao pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
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
                      throw new DAOException(Mensagens.CLASS_EXCEPTION);
              } catch (SQLException e) {
                      throw new DAOException(Mensagens.DAO_EXCEPTION + this.getClass().getSimpleName());
              }
            
            return comissao;
      }

}
