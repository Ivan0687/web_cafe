<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<div align="center">
    <h1 align="center">Hello</h1>
    <h1 align="center">Welcome</h1>
    <form action="${pageContext.request.contextPath}/coffeelist" method="get">
        <button type="submit">Make an order</button>
    </form>
</div>
</body>
</html>
