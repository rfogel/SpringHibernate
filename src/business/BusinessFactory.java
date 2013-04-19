/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

/**
 *
 * @author Fogel
 */
public abstract class BusinessFactory {

	public static BusinessFactory Instance(@SuppressWarnings("rawtypes") Class factory)
	{
		BusinessFactory busFactory = null;
		try {
			busFactory = (BusinessFactory)factory.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return busFactory;
	}

	public abstract UserBusiness getUserBusiness();
	public abstract OrderBusiness getOrderBusiness();
}
