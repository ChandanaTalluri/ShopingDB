<%-- 
    Document   : shop
    Created on : Mar 5, 2020, 10:56:59 AM
    Author     : 0809379
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    
    <body>
        <h1>CD list</h1>
        <table>
            <tr>
                <th>Description</th>
                <th class="right">Price</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach var="item" items="${products}">
                <tr>
                    <td>${item.description}</td>
                    <td class = "right">${item.priceCurrencyFormat}</td>
                    <td>
                        <form action="" method="post">
                            <input type="hidden" name="option" value="add">
                            <input type="hidden" name="productCode" value="<c:out value='${item.code}'/>">
                            <input type="submit" value="Add to Cart">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
