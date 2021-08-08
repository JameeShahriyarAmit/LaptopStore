<%@page import="dao.ProductDAO" %>
<%@page import="model.Product" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Product List </title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Product
					List </a>
			</div>
                    <%String username = (String) session.getAttribute("username");%>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/OrderingMenu.jsp"
                                       class="nav-link">Welcome,<%=username%></a></li>
			</ul>
                        <a href="<%=request.getContextPath()%>/Exit" class="btn btn-success">Logout</a>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Products</h3>
			<hr>
			<div class="container text-left">
			</div>
			<br>
                        <form method="get" action="Cart">
                            <label>Enter Product ID</label>
                            <input type="text" name="id" required> 
                            <button type="submit" class= "btn btn-primary">Add Product to cart</button>
                        </form>
                        <br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
                                                <th>Image</th>
					</tr>
				</thead>
				<tbody>
                                    
                                    <%
                                        ProductDAO productDao = new ProductDAO();
                                        List<Product> pList = productDao.selectAllProduct();
                                    %>
                                    
                                    <% for (int i = 0; i < pList.size(); i++) {

                                    %>



                                    <tr>

                                        <td><%= pList.get(i).getId()%></td>
                                        <td><%= pList.get(i).getName()%></td>
                                        <td><%= pList.get(i).getDescription()%></td>
                                        <td><%= pList.get(i).getPrice()%></td>
                                        <td><img SRC="${pageContext.request.contextPath}/images/<%= pList.get(i).getImage()%>" width="100" height="100"></img></td>
                                    </tr>

                                    <%
                                        }

                                    %>
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>

