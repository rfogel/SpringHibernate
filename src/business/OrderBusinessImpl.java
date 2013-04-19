package business;

import java.util.List;

import model.Order;
import dao.hibernate.DaoFactory;
import dao.hibernate.DaoFactoryImpl;
import dao.hibernate.OrderDao;
import exception.BusinessException;
import exception.DaoException;

public class OrderBusinessImpl implements OrderBusiness
{

	@Override
	public void updateOrder(Order Order) throws BusinessException 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void deleteOrder(Order order) throws BusinessException 
	{
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void deleteOrderById(Integer orderId) throws BusinessException 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public List<Order> getOrderByUserType(Integer usertypeId) throws BusinessException 
	{
		OrderDao orderDao = DaoFactory.getInstance(DaoFactoryImpl.class).getOrderDao();
		
        List<Order> lisOrder = null;
        
        try
        {
              lisOrder = orderDao.getOrderByUserType(usertypeId);
        } catch (DaoException e) {
              throw new BusinessException(e.getErrorMessage());
        }

        return lisOrder;
	}

	@Override
	public List<Order> getOrderByUser(Integer userId) throws BusinessException 
	{
		OrderDao orderDao = DaoFactory.getInstance(DaoFactoryImpl.class).getOrderDao();
		
        List<Order> lisOrder = null;
        
        try
        {
              lisOrder = orderDao.getOrderByUser(userId);
        } catch (DaoException e) {
              throw new BusinessException(e.getErrorMessage());
        }

        return lisOrder;
	}

	@Override
	public Order getOrderById(Integer orderId) throws BusinessException {
		return null;
	}

	@Override
	public List<String> getQualification() throws BusinessException {
		return null;
	}

	@Override
	public List<String> getFinancing() throws BusinessException {
		return null;
	}

	@Override
	public List<String> getQuotaByUser(Integer userId) throws BusinessException {
		return null;
	}

}
