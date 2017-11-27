<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 23.10.2017
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Order</title>
    <link type="text/css" href="styles.css" rel="stylesheet">
</head>
<body>
<div align="center">
    <table cellspacing="0px" cellpadding="2px" border="0px" style="border: 1px #B0B0B0 solid">
        <tr style="background-color: #C0C0C7">
            <th colspan="2">Your order is accepted</th>
        </tr>
        <tr style="background-color: #F0F0F0">
            <td><b>Date</b></td>
            <td>${order.orderDate}</td>
        </tr>
        <tr style="background-color: #F0F0F0">
            <td><b>Name</b></td>
            <td>${order.name}</td>
        </tr>
        <tr style="background-color: #F0F0F0">
            <td><b>Address</b></td>
            <td>${order.deliveryAddress}</td>
        </tr>
    </table>

    <br/>
    <table cellspacing="0px" cellpadding="2px" border="0px" style="border: 1px #B0B0B0 solid">
        <tr style="background-color: #C0C0C7">
            <th>Название</th>
            <th>Цена</th>
            <th>Количество</th>
            <th>Всего</th>
        </tr>
        <c:set var="cost" value="0"/>
        <c:forEach var="item" items="${order.items}">
            <tr style="background-color: #F0F0F0">
                <td nowrap="true">${item.coffeeType.name}</td>
                <td nowrap="true">${item.coffeeType.price}</td>
                <td align="right">${item.quantity}</td>
                <td><span style="color: red; ">${item.itemCost}</span> TGR</td>
                <c:set var="cost" value="${cost + item.itemCost}"/>
            </tr>
        </c:forEach>
        <tr style="background-color: #E0E0E0">
            <td colspan="3" align="right"><b>Сумма:</b></td>
            <td align="right">${cost} TGR</td>
        </tr>
        <tr style="background-color: #F0F0F0">
            <td colspan="3" align="right"><b>Доставка:</b></td>
            <td align="right">${order.cost - cost} TGR</td>
        </tr>
        <tr style="background-color: #E0E0E0">
            <td colspan="3" align="right"><b>Всего:</b></td>
            <td align="right">${order.cost} TGR</td>
        </tr>
    </table>
    <br/>
    <a href="/coffeelist">Вернуться в магазин</a>
</div>
</body>
</html>
