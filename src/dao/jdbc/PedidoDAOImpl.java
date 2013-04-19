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

import model.Order;

import exception.DaoException;
import exception.MessageException;


/**
 *
 * @author Fogel
 */
public class PedidoDAOImpl implements PedidoDAO{

      @Override
      public void inserirPedido(Order pedido) throws DaoException {
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call inserirpedido(?,?,?,?,?,?,?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setString(1, pedido.getCota());
                ps.setString(2, pedido.getNomeConferencia());
                ps.setString(3, pedido.getNomeArtigo());
                ps.setString(4, pedido.getFinanciamento());
                ps.setString(5, pedido.getQualificacaoConferencia());
                ps.setString(6, pedido.getAutor());
                ps.setInt(7, pedido.getNumeroArtigos());

                ResultSet rs = ps.executeQuery();

                pConexao.desconectar();

              } catch (ClassNotFoundException e){
                      throw new DaoException(MessageException.CLASS_EXCEPTION);
              } catch (SQLException e) {
                      throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
              }

	}

      @Override
      public void avaliarPedido(int id, boolean decisao, String login) throws DaoException {
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call avaliarpedido(?,?,?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setInt(1, id);
                ps.setBoolean(2, decisao);
                ps.setString(3, login);

                ResultSet rs = ps.executeQuery();

                pConexao.desconectar();

              } catch (ClassNotFoundException e){
                      throw new DaoException(MessageException.CLASS_EXCEPTION);
              } catch (SQLException e) {
                      throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
              }
      }

      @Override
      public List<Order> listarPedidoPorCargo() throws DaoException {
            List<Order> lisPedido = null;
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "select p.id_pedido, p.cota, p.financiamento, p.nome_artigo, p.nome_conferencia, "
                        + "p.num_artigos, p.qualificacao, p.status, u.nome from usuario u, pedido p where "
                        + "p.dono = u.id_usuario";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ResultSet rs = ps.executeQuery();

                if ( !rs.wasNull() )
                {
                      lisPedido = new ArrayList<Order>();
                      while ( rs.next() )
                      {
                            Order ped = new Order();
                            ped.setAutor(rs.getString("nome"));
                            ped.setId(rs.getInt("id_pedido"));
                            ped.setCota(rs.getString("cota"));
                            ped.setFinanciamento(rs.getString("financiamento"));
                            ped.setNomeArtigo(rs.getString("nome_artigo"));
                            ped.setNomeConferencia(rs.getString("nome_conferencia"));
                            ped.setNumeroArtigos(rs.getInt("num_artigos"));
                            ped.setQualificacaoConferencia(rs.getString("qualificacao"));
                            ped.setStatus(rs.getInt("status"));
                            lisPedido.add(ped);
                      }
                }

                pConexao.desconectar();

              } catch (ClassNotFoundException e){
                      throw new DaoException(MessageException.CLASS_EXCEPTION);
              } catch (SQLException e) {
                      throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
              }

            return lisPedido;
      }

      @Override
      public List<Order> listarPedidoPorLogin(String login) throws DaoException {
            List<Order> lisPedido = null;
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call listarpedidoporlogin(?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setString(1, login);

                ResultSet rs = ps.executeQuery();

                if ( !rs.wasNull() )
                {
                      lisPedido = new ArrayList<Order>();
                      while ( rs.next() )
                      {
                            Order ped = new Order();
                            ped.setAutor(rs.getString("dono"));
                            ped.setId(rs.getInt("id_pedido"));
                            ped.setCota(rs.getString("cota"));
                            ped.setFinanciamento(rs.getString("financiamento"));
                            ped.setNomeArtigo(rs.getString("nome_artigo"));
                            ped.setNomeConferencia(rs.getString("nome_conferencia"));
                            ped.setNumeroArtigos(rs.getInt("num_artigos"));
                            ped.setQualificacaoConferencia(rs.getString("qualificacao"));
                            ped.setStatus(rs.getInt("status"));
                            lisPedido.add(ped);
                      }
                }

                pConexao.desconectar();

              } catch (ClassNotFoundException e){
                      throw new DaoException(MessageException.CLASS_EXCEPTION);
              } catch (SQLException e) {
                      throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
              }

            return lisPedido;
      }

      @Override
      public ArrayList<String> listarQualificacao() throws DaoException {
            ArrayList<String> qualificacao = null;
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call listarqualificacao()}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ResultSet rs = ps.executeQuery();

                if ( !rs.wasNull() )
                {
                      qualificacao = new ArrayList<String>();
                      while ( rs.next() )
                              qualificacao.add(rs.getString(1));
                }

                pConexao.desconectar();

              } catch (ClassNotFoundException e){
                      throw new DaoException(MessageException.CLASS_EXCEPTION);
              } catch (SQLException e) {
                      throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
              }

            return qualificacao;
      }

      @Override
      public ArrayList<String> listarFinanciamento() throws DaoException {
            ArrayList<String> financiamento = null;
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call listarfinanciamento()}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ResultSet rs = ps.executeQuery();

                if ( !rs.wasNull() )
                {
                      financiamento = new ArrayList<String>();
                      while ( rs.next() )
                              financiamento.add(rs.getString(1));
                }

                pConexao.desconectar();

              } catch (ClassNotFoundException e){
                      throw new DaoException(MessageException.CLASS_EXCEPTION);
              } catch (SQLException e) {
                      throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
              }

            return financiamento;
      }

      @Override
      public ArrayList<String> listarCota(String login) throws DaoException {
            ArrayList<String> cota = null;
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call listarcota(?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setString(1, login);

                ResultSet rs = ps.executeQuery();       

                if ( !rs.wasNull() )
                {
                      cota = new ArrayList<String>();
                      while ( rs.next() )
                              cota.add(rs.getString(1));
                }

                pConexao.desconectar();

              } catch (ClassNotFoundException e){
                      throw new DaoException(MessageException.CLASS_EXCEPTION);
              } catch (SQLException e) {
                      throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
              }

            return cota;
      }

      @Override
      public Order consultarPedido(int id) throws DaoException {
            Order ped = null;
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "select p.nome_artigo, p.nome_conferencia, p.id_pedido, p.num_artigos,"
                        + " f.tipo as Financiamento,"
                        + " q.tipo as Qualificacao, p.status, u.nome as Usuario, c.nome as Cota"
                        + " from pedido p, financiamento f, qualificacao q,  cota c, usuario u"
                        + " where p.id_pedido = ?"
                        + " and p.dono = u.id_usuario"
                        + " and p.financiamento = f.id_financiamento"
                        + " and p.qualificacao = q.id_qualificacao"
                        + " and p.cota = c.id_cota";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if ( !rs.wasNull() )
                {
                      ped = new Order();
                      while ( rs.next() )
                      {
                            ped.setAutor(rs.getString("Usuario"));
                            ped.setId(rs.getInt("id_pedido"));
                            ped.setCota(rs.getString("Cota"));
                            ped.setFinanciamento(rs.getString("Financiamento"));
                            ped.setNomeArtigo(rs.getString("nome_artigo"));
                            ped.setNomeConferencia(rs.getString("nome_conferencia"));
                            ped.setNumeroArtigos(rs.getInt("num_artigos"));
                            ped.setQualificacaoConferencia(rs.getString("Qualificacao"));
                            ped.setStatus(rs.getInt("status"));
                            switch ( ped.getStatus() )
                            {
                                  case 1:
                                        ped.setStatusString("Pendente");
                                        break;
                                  case 2:
                                        ped.setStatusString("Avaliado pelo Presidente da Comissão");
                                        break;
                                  case 3:
                                        ped.setStatusString("Encaminhado");
                                        break;
                                  case 4:
                                        ped.setStatusString("Encerrado");
                                        break;
                                  case 5:
                                        ped.setStatusString("Aceito pelo 2º Membro da Comissão");
                                        break;
                                  case 6:
                                        ped.setStatusString("Rejeitado pelo 2º Membro da Comissão");
                                        break;
                                  case 7:
                                        ped.setStatusString("Aceito pelo 1º Membro da Comissão");
                                        break;
                                  case 8:
                                        ped.setStatusString("Rejeitado pelo 1º Membro da Comissão");
                                        break;
                                  case 9:
                                        ped.setStatusString("Aceito pelos Membros da Comissão");
                                        break;
                                  case 10:
                                        ped.setStatusString("Aceito pelo 1º Membro da Comissão e rejeitado pelo 2º Membro");
                                        break;
                                  case 11:
                                        ped.setStatusString("Rejeitado pelo 1º Membro da Comissão e aceito pelo 2º Membro");
                                        break;
                                  case 12:
                                        ped.setStatusString("Rejeitado pelos Membros da Comissão");
                            }
                      }
                }

                pConexao.desconectar();

              } catch (ClassNotFoundException e){
                      throw new DaoException(MessageException.CLASS_EXCEPTION);
              } catch (SQLException e) {
                      throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
              }

            return ped;
      }

      @Override
      public boolean consultarPedidoPorLogin(int id) throws DaoException {
            boolean possuiPedido = false;
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call consultarpedidoporlogin(?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if ( rs.next() )
                      possuiPedido = rs.getBoolean(1);

                pConexao.desconectar();

              } catch (ClassNotFoundException e){
                      throw new DaoException(MessageException.CLASS_EXCEPTION);
              } catch (SQLException e) {
                      throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
              }

            return possuiPedido;
      }

      @Override
      public void removerPedido(int id) throws DaoException {
            try {
                Connetion pConexao = new ConnectionFactory().getInstance().getConexaoPostgres();
                pConexao.conectar();

                String sql = "{call removerpedido(?)}";

                CallableStatement ps = pConexao.getConnection().prepareCall(sql);

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                pConexao.desconectar();

              } catch (ClassNotFoundException e){
                      throw new DaoException(MessageException.CLASS_EXCEPTION);
              } catch (SQLException e) {
                      throw new DaoException(MessageException.DAO_EXCEPTION + this.getClass().getSimpleName());
              }
      }
}


