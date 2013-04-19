/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.hibernate;

/**
 *
 * @author Fogel
 */
public abstract class DaoFactory 
{
	public static DaoFactory getInstance(@SuppressWarnings("rawtypes") Class factory)
	{
		DaoFactory daoFactory = null;
		try {
			daoFactory = (DaoFactory)factory.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return daoFactory;
	}

	public abstract UserDao getUserDao();
	public abstract OrderDao getOrderDao();
	public abstract CommissionDao getCommissionDao();

}
