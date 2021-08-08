<%@page import="dao.OrderDao" %>
<%@page import="model.Order" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Checkout Page</title>
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
				<a href="https://www.javaguides.net" class="navbar-brand"> Checkout Page </a>
			</div>
                    <%String username = (String) session.getAttribute("username");%>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/CheckOut.jsp"
					class="nav-link">Checkout</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Products Added to Cart</h3>
			<hr>
			<div class="container text-left">
			</div>
			<br>
                        <form method="get" action="OrderingMenu.jsp">            

                            <button type="submit" >Continue Shopping</button>

                        </form>

                        <br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Name</th>
						<th>Price</th>
					</tr>
				</thead>
				<tbody>
                                    
                                    <%
                                        OrderDao orderDao = new OrderDao();
                                        List<Order> oList = orderDao.selectAllOrder();
                                    %>
                                    
                                    <% for (int i = 0; i < oList.size(); i++) {

                                    %>



                                    <tr>              

                                        <td><%= oList.get(i).getName()%></td>            
                                        <td><%= oList.get(i).getPrice()%></td>            
                                    </tr>

                                    <%
                                        }

                                    %>
				</tbody>

			</table>
                                <form method="get" action="Receipt.jsp">            

                                    <button type="submit" class= "btn btn-primary">CheckOut</button>

                                </form>
                                <br>
		</div>
	</div>
</body>
</html>
