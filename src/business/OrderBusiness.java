/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business;


import java.util.List;

import model.Order;
import exception.BusinessException;

/**
 *
 * @author Fogel
 */
public interface OrderBusiness
{
    public void updateOrder(Order order) throws BusinessException;
    public void deleteOrder(Order order) throws BusinessException;
    public void deleteOrderById(Integer orderId) throws BusinessException;   
    public Order getOrderById(Integer orderId) throws BusinessException;   
    public List<Order> getOrderByUser(Integer userId) throws BusinessException;
    public List<Order> getOrderByUserType(Integer usertypeId) throws BusinessException;

    public List<String> getQualification() throws BusinessException;
    public List<String> getFinancing() throws BusinessException;
    public List<String> getQuotaByUser(Integer userId) throws BusinessException;
    
    /*public void avaliarPedido (int id, boolean decisao, String login) throws BusinessException;
    public Order consultarPedido (int id) throws BusinessException;
    public boolean consultarPedidoPorLogin (int id) throws BusinessException;*/
}
