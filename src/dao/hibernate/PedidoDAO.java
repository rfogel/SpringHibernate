/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.hibernate;


import java.util.ArrayList;
import java.util.List;

import model.Order;

import exception.DAOException;

/**
 *
 * @author Fogel
 */
public interface PedidoDAO {

    public void inserirPedido(Order pedido) throws DAOException;
    public void removerPedido(int id) throws DAOException;
    public List<Order> listarPedidoPorCargo() throws DAOException;
    public List<Order> listarPedidoPorLogin(String login) throws DAOException;
    public void avaliarPedido(int id, boolean decisao, String login) throws DAOException;
    public Order consultarPedido(int id) throws DAOException;
    public boolean consultarPedidoPorLogin(int id) throws DAOException;

    public ArrayList<String> listarQualificacao() throws DAOException;
    public ArrayList<String> listarFinanciamento() throws DAOException;
    public ArrayList<String> listarCota(String login) throws DAOException;
}
