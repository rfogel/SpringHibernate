/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.hibernate;

import utils.SpringUtil;

/**
 *
 * @author Fogel
 */
public class DaoFactoryImpl extends DaoFactory
{
	@Override
	public UserDao getUserDao() {
		return (UserDao) SpringUtil.getBean("userDao");
	}

	@Override
	public OrderDao getOrderDao() {
		return (OrderDao) SpringUtil.getBean("orderDao");
	}

	@Override
	public CommissionDao getCommissionDao() {
		return (CommissionDao) SpringUtil.getBean("commissionDao");
	}

	@Override
	public LoginDao getLoginDao() {
		return (LoginDao) SpringUtil.getBean("loginDao");
	}
}
