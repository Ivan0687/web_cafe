
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Coffee list</title>
    <link type="text/css" href="styles.css" rel="stylesheet">
</head>
<body>
<div align="center">
    <form name="coffeeform">
        <table cellspacing="0px" cellpadding="0px" border="0px" style="border: 1px #B0B0B0 solid">
            <tr style="background-color: #C0C0C7">
                <th/>
                <th>Название</th>
                <th>Цена</th>
                <th>Количество</th>
            </tr>
            <c:forEach var="item" items="${coffeeItems}">
            <tr style="background-color: #F0F0F0">
            <td>галочка</td>
                <td>${item.name}</td>
                <td>${item.price}</td>
            </tr>
            </c:forEach>

            <tr style="background-color: #F0F0F0">
                <td><input type="checkbox" name="check1" onfocus="blur();" checked="true" onclick="document.forms.coffeeform.check1.checked='on';"/></td>
                <td nowrap="true">Очень крепкий и горячий кофе.</td>
                <td nowrap="true">20 TGR</td>
                <td align="right"><input class="field" type="text" size="5" onfocus="blur();" value="3"/></td>
            </tr>
            <tr style="background-color: #E0E0E0">
                <td><input type="checkbox" name="check2" onfocus="blur();" onclick="document.forms.coffeeform.check2.checked='';"/></td>
                <td nowrap="true">Вкусный кофе со сливками.</td>
                <td nowrap="true">15 TGR</td>
                <td align="right"><input class="field" type="text" size="5" onfocus="blur();"/></td>
            </tr>
            <tr style="background-color: #F0F0F0">
                <td colspan="4" align="right"><input type="button" value="Заказать" onclick="document.location.href='orderlist.html';"/></td>
            </tr>
        </table>
        <font color="red">*</font> - каждая третья чашка бесплатно.
    </form>
</div>
</body>
</html>
