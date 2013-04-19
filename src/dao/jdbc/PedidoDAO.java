/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.jdbc;


import java.util.ArrayList;
import java.util.List;

import model.Order;

import exception.DaoException;

/**
 *
 * @author Fogel
 */
public interface PedidoDAO {

    public void inserirPedido(Order pedido) throws DaoException;
    public void removerPedido(int id) throws DaoException;
    public List<Order> listarPedidoPorCargo() throws DaoException;
    public List<Order> listarPedidoPorLogin(String login) throws DaoException;
    public void avaliarPedido(int id, boolean decisao, String login) throws DaoException;
    public Order consultarPedido(int id) throws DaoException;
    public boolean consultarPedidoPorLogin(int id) throws DaoException;

    public ArrayList<String> listarQualificacao() throws DaoException;
    public ArrayList<String> listarFinanciamento() throws DaoException;
    public ArrayList<String> listarCota(String login) throws DaoException;
}
