<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Coffee list</title>
    <link href="${pageContext.request.contextPath}/styles.css" rel="stylesheet">

</head>
<body>
<div align="center">
    <h2>Choose your coffee</h2>

    <form action="${pageContext.request.contextPath}/orderlist" method="get">

        <table cellspacing="0px" cellpadding="0px" border="0px" style="border: 1px #B0B0B0 solid">
            <tr style="background-color: #C0C0C7">
                <th>Название</th>
                <th>Цена</th>
                <th>Количество</th>
            </tr>
            <c:forEach var="coffeeType" items="${coffeeTypes}">
                <tr style="background-color: #F0F0F0">
                    <td>${coffeeType.name}</td>
                    <td>${coffeeType.price}</td>
                    <td><input type="number" min="1" onchange="{this.name = ${coffeeType.id}}"></td>
                </tr>
            </c:forEach>
            <tr style="background-color: #F0F0F0">
                <th colspan="4" align="right"><input type="submit" value="Order"/></th>
            </tr>
            <tr>
                <th colspan="4" align="left"><span style="color: red; ">*</span> - Every ${freeCup} cup of every type is
                    free.
                </th>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
