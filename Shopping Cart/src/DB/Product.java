package DB;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Cart;

public class Product {

	public static List<Cart> getProducts()
	{
		EntityManager em = customTools.DBUtil.getEmFactory().createEntityManager();
		String qString = "select b from cart b";
		
		List<Product> products = null;
		try{
			Query q = em.createQuery(qString);
			products = q.getResultList();
		}catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			em.close();
		} return products;
	}
	
	public static Product getDetails (long prodID)
	{
		EntityManager em = customTools.DBUtil.getEmFactory().createEntityManager();
		Product details  = em.find(Product.class, prodID);
		return details;
	}
	
}