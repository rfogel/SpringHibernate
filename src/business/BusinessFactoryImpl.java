/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

/**
 *
 * @author Fogel
 */
public class BusinessFactoryImpl extends BusinessFactory
{
	@Override
	public UserBusiness getUserBusiness() {
		return new UserBusinessBean();
	}

	@Override
	public OrderBusiness getOrderBusiness() {
		return new OrderBusinessImpl();
	}
}
