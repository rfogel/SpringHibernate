/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.jdbc;

/**
 *
 * @author Fogel
 */
public abstract class DAOFactory 
{
    public static DAOFactory getInstance(@SuppressWarnings("rawtypes") Class factory)
    {

            DAOFactory daoFactory = null;
            try {
                    daoFactory = (DAOFactory)factory.newInstance();
            } catch (Exception e) {
                    e.printStackTrace();
            }
            return daoFactory;
    }

    public abstract LoginDAO getLoginDAO();
    public abstract ComissaoDAO getComissaoDAO();
    public abstract PedidoDAO getPedidoDAO();
    public abstract UsuarioDAO getUsuarioDAO();
    public abstract PerfilDAO getPerfilDAO();

}
