
import javax.persistence.EntityManager;

import DB.Product;
import customTools.DBUtil;
import model.Cart;
import model.Lineitem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Detail() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String prodIDstr = request.getParameter("ProdID");
		long prodID = Long.parseLong(prodIDstr);
		HttpSession session = request.getSession();
		session.setAttribute("prodId", prodIDstr);
		
		String message = "";
		try
		{
			Cart prod = Product.getDetails(prodID);
			message += "<div class=\"container\"><div class=\"jumbotron\"><h1>" + prod.getProdname() + "</h1><br>"
					+ "<img src=" + prod.getPicture() + " class=\"img-rounded\" alt=\"Cinque Terre\" width=\"608\" height=\"472\"><br><h4> Description: </h4>" + prod.getDescription() + "</div>";
			message += "<div class=\"alert alert-success col-sm-3 col-sm-offset-9\"><strong>Price:</strong> $"+ prod.getPrice() + "</div>";

			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/proddetail.jsp").forward(request, response);
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}