package customTools;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Cart;
import model.Lineitem;

public class Cartitem {

	public static void addToCart(Lineitem item) {
		EntityManager em = customTools.DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(item);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static Lineitem getCartItem (long orderid)
	{
		EntityManager em = customTools.DBUtil.getEmFactory().createEntityManager();
		Lineitem item  = em.find(Lineitem.class, orderid);
		return item;
	}
	
	public static void removeFromCart(Lineitem item) {
		EntityManager em = customTools.DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(item));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static List<Lineitem> viewCart (){
		EntityManager em = customTools.DBUtil.getEmFactory().createEntityManager();
		String qString = "select c from LineItem c";
		
		List<Lineitem> posts = null;
		try{
			Query query = em.createQuery(qString);
			posts = query.getResultList();

		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
				em.close();
			}
		return posts;
	}
	
}