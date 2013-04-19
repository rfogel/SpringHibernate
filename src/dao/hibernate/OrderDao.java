/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.hibernate;


import java.util.List;

import model.Order;
import exception.DaoException;

/**
 *
 * @author Fogel
 */
public interface OrderDao 
{
    public void updateOrder(Order order) throws DaoException;
    public void deleteOrder(Order order) throws DaoException; 
    public void deleteOrderById(Integer orderId) throws DaoException;  
    public Order getOrderById(Integer id) throws DaoException;
    public List<Order> getOrderByUser(Integer userId) throws DaoException;
    public List<Order> getOrderByUserType(Integer usertypeId) throws DaoException;
       
    public List<String> getQualification() throws DaoException;
    public List<String> getFinancing() throws DaoException;
    public List<String> getQuotaByUser(Integer userId) throws DaoException;
    
    /*public void avaliarPedido (int id, boolean decisao, String login) throws BusinessException;
    public Order consultarPedido (int id) throws BusinessException;
    public boolean consultarPedidoPorLogin (int id) throws BusinessException;*/
}
