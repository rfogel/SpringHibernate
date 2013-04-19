/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.jdbc;

/**
 *
 * @author Fogel
 */
public class DAOFactoryImpl extends DAOFactory{

      @Override
      public LoginDAO getLoginDAO() {
            return new LoginDAOImpl();
      }

      @Override
      public ComissaoDAO getComissaoDAO() {
            return new ComissaoDAOImpl();
      }

      @Override
      public PedidoDAO getPedidoDAO() {
            return new PedidoDAOImpl();
      }

      @Override
      public UsuarioDAO getUsuarioDAO() {
            return new UsuarioDAOImpl();
      }

      @Override
      public PerfilDAO getPerfilDAO() {
            return new PerfilDAOImpl();
      }
}
