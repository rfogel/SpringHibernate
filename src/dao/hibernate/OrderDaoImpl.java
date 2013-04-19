/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.hibernate;


import java.util.List;

import model.Order;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import exception.DaoException;
import exception.MessageException;


/**
 *
 * @author Fogel
 */
@SuppressWarnings("unchecked")
public class OrderDaoImpl extends Dao implements OrderDao
{

	@Override
	public void updateOrder(Order order) throws DaoException {	
	}

	@Override
	public void deleteOrder(Order order) throws DaoException {	
	}
	
	@Override
	public void deleteOrderById(Integer orderId) throws DaoException {	
	}

	@Override
	public List<Order> getOrderByUser(Integer userId) throws DaoException 
	{
		Session session = null;
		List<Order> result = null;
		
		try
		{
			session = getSessionFactory().openSession();
			Query query = session.createQuery("from Order where userId = '" + userId + "'");
			result = query.list();
			session.close();
			
		} catch (HibernateException e) {
			session.close();
			throw new DaoException(MessageException.DAO_EXCEPTION + ": " + e.getMessage());
		}

		return result;
	}

	@Override
	public List<Order> getOrderByUserType(Integer usertypeId) throws DaoException 
	{
		Session session = null;
		List<Order> result = null;
		
		try
		{
			session = getSessionFactory().openSession();
			Query query = session.createQuery("from Order where userId in (select userId from User where userTypeId = " + usertypeId + ")");
			result = query.list();
			session.close();
			
		} catch (HibernateException e) {
			session.close();
			throw new DaoException(MessageException.DAO_EXCEPTION + ": " + e.getMessage());
		}

		return result;
	}

	@Override
	public Order getOrderById(Integer id) throws DaoException {
		return null;
	}

	@Override
	public List<String> getQualification() throws DaoException {
		return null;
	}

	@Override
	public List<String> getFinancing() throws DaoException {
		return null;
	}

	@Override
	public List<String> getQuotaByUser(Integer userId) throws DaoException {
		return null;
	}

}


/*@Override
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
}*/