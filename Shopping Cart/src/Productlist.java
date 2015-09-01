import java.util.List;

import javax.persistence.EntityManager;

import DB.Product;
import customTools.DBUtil;
import model.Cart;
import model.Lineitem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Productlist")
public class Productlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Productlist() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try{
			List<Cart> products = Product.getProducts();
			message += "<div class=\"container\">";
			message += "<table class=\"table table-bordered\"><thead><tr><th>Product Name</th></tr></thead><tbody>";
			for (Cart prod : products)
			{
				message += "<tr>";
				message += "<td>"
						+ "<img src="+ prod.getPicture() + " class=\"img-rounded\" alt=\"Cinque Terre\" width=\"304\" height=\"236\"><br>"
						+ prod.getProdname() + "<br><a href=\"details?ProdID=" + prod.getProdid() + "\">" + "<button type=\"button\" class=\"btn btn-info\">Description</button>" + "</a>" + "</td>";
				message += "</tr>";
			}
			message += "</tbody></table>";
			message += "</div>";
			
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/OurProducts.jsp").forward(request, response);
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			em.close();
		}
		
	}

}