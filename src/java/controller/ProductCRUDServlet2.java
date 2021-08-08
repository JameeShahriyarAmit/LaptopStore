
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import model.Product;

/**
 *
 * @author shahr
 */
@WebServlet("/")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)

public class ProductCRUDServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	
	public void init() {
		productDAO = new ProductDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertProduct(request, response);
				break;
			case "/delete":
				deleteProduct(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateProduct(request, response);
				break;
			default:
				listProduct(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Product> listProduct = productDAO.selectAllProduct();
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-list2.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form2.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Product existingProduct = productDAO.selectProduct(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form2.jsp");
		request.setAttribute("product", existingProduct);
		dispatcher.forward(request, response);

	}

	private void insertProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
		String description = request.getParameter("description");
		double price = Double.parseDouble(request.getParameter("price"));
                Part part = request.getPart("image");
                String filename = extractFileName(part);
		Product newProduct = new Product(id, name, description, price, filename);
		productDAO.insertProduct(newProduct);
		response.sendRedirect("list");
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
                int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		double price = Double.parseDouble(request.getParameter("price"));
                Part part = request.getPart("image");
                String filename = extractFileName(part);
		Product book = new Product(id, name, description, price, filename);
		productDAO.updateProduct(book);
		response.sendRedirect("list");
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		productDAO.deleteProduct(id);
		response.sendRedirect("list");

	}
        
        private String extractFileName(Part part) {//This method will print the file name.
            String contentDisp = part.getHeader("content-disposition");
            String[] items = contentDisp.split(";");
            for (String s : items) {
                if (s.trim().startsWith("filename")) {
                    return s.substring(s.indexOf("=") + 2, s.length() - 1);
                }
            }
            return "";
        }

}
