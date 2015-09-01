import java.util.List;

import javax.persistence.EntityManager;

import DB.Product;
import customTools.DBUtil;
import model.Cart;
import model.Lineitem;
import DB.Cartitem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Shoppingcart")
public class Shoppingcart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Shoppingcart() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Lineitem> shopcart = DB.Cartitem.viewCart();
		String message = "";
		double total = 0;
		try{
			message += "<div class=\"container\">";
			message += "<table class=\"table table-bordered\"><thead><tr><th>Product</th><th>Price</th><th>Quantity</th><th>Remove?</th></tr></thead><tbody>";
			for (Lineitem cart : shopcart)
			{
				message += "<tr>";		
				message += "<td>";
				message += cart.getQuantity();
				message += "</td>";
				message += "<td>";
				message += "<a href=\"removeItem?OrdID=" + cart.getLineItemId() + "\">" +"<button type=\"button\" class=\"btn btn-info\">Remove</button>" + "</a>";
				message += "</td>";
				message += "</tr>";
				total += (cart.getQuantity()*cart.getId().getPrice());
			}
			message += "</tbody></table>";
			message += "</div>";
		}catch (Exception e){
			e.printStackTrace();;
		} finally {
			em.close();
		}
		request.setAttribute("message", message);
		request.setAttribute("total", total);
		getServletContext().getRequestDispatcher("/mycart.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String QTYstr = request.getParameter("QTY");
		long QTY = Long.parseLong(QTYstr);
		HttpSession session = request.getSession();
		String prodIDstr = (String) session.getAttribute("prodId");
		long prodID = Long.parseLong(prodIDstr);

		try{
			Lineitem mycart = new Lineitem();
			mycart.setQuantity(QTY);
			Product myprod = DBProduct.getDetails(prodID);
			mycart.setProduct(myprod);
			Cartitem.addToCart(mycart);

		}catch (Exception e){
			e.printStackTrace();;
		} finally {
			em.close();
		}

		response.sendRedirect(response.encodeRedirectURL("/ShoppingCart/ShopCart"));
	}

}