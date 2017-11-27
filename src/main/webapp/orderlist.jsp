<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Order list</title>
    <link type="text/css" href="styles.css" rel="stylesheet">
</head>
<body>
<div align="center">
    <form action="/order" method="get">
        <table cellspacing="0px" cellpadding="2px" border="0px" style="border: 1px #B0B0B0 solid">
            <tr style="background-color: #C0C0C7">
                <th colspan="2">Доставка</th>
            </tr>
            <tr style="background-color: #F0F0F0">
                <td><b>ФИО</b></td>
                <td><input type="text" name="name" size="30" placeholder="Enter your name"/></td>
            </tr>
            <tr style="background-color: #F0F0F0">
                <td><b>Адрес</b></td>
                <td><input type="text" name="address" size="30" placeholder="Enter delivery address"/>
                </td>
            </tr>
            <tr style="background-color: #F0F0F0">
                <td colspan="2" align="right">
                    <button type="submit">Order</button>
                </td>
            </tr>
        </table>
    </form>

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
    <br>

</div>
</body>
</html>
