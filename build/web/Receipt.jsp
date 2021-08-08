<%@page import="dao.OrderDao" %>
<%@page import="model.Order" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Receipt Page </title>
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
                    <a href="https://www.javaguides.net" class="navbar-brand"> Receipt Page </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/Receipt.jsp"
                           class="nav-link">Receipt</a></li>
                </ul>
            </nav>
        </header>
        <br>

        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">List of Products Ordered</h3>
                <hr>

                <br>
                

                <%
                    OrderDao orderDao = new OrderDao();
                    List<Order> oList = orderDao.selectAllOrder();
                    String username = (String) session.getAttribute("username");
                    double total = 0.0;
                %>
                
                <h3>Customer Name: <%=username%></h3>

                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>						
                            <th>Price</th>

                        </tr>
                    </thead>
                    <% for (int i = 0; i < oList.size(); i++) {

                    %>

                    <tr>              
                        <td><%= (i+1) %> </td>
                        <td><%= oList.get(i).getName()%></td>            
                        <td><%= oList.get(i).getPrice()%></td>            
                    </tr>

                    <%
                        total += oList.get(i).getPrice();
                        }

                    %>

                </table>
                <br>
                <h4><%out.println("Total = RM" + total);%></h4>

                <br>
                <form method="get" action="exit.jsp">            

                    <button class= "btn btn-primary"  type="submit">Confirm</button>

                </form>



                </body>
                </html>