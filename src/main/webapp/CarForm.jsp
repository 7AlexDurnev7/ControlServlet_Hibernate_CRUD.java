<%--
  Created by IntelliJ IDEA.
  User: aleksandr
  Date: 16.10.2022
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Каталог автомобилей</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
          integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
          crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
          integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
          crossorigin="anonymous"></script>
</head>
<body>
<header>
<%--  <nav class="navbar navbar-expand-md navbar-dark"--%>
<%--       style="background-color: gray">--%>
<%--    <div>--%>
<%--      <a href="<%=request.getContextPath()%>/list" class="navbar-brand">Главная</a>--%>
<%--    </div>--%>
<%--  </nav>--%>
</header>
<br>
<div class="container col-md-5">
  <div class="card">
    <div class="card-body">
      <c:if test="${car != null}">
      <form action="update" method="post">
        </c:if>
        <c:if test="${car == null}">
        <form action="insert" method="post">
          </c:if>

          <caption>
            <h2>
              <c:if test="${car != null}">
                Изменить данные
              </c:if>
              <c:if test="${car == null}">
                Добавить автомобиль
              </c:if>
            </h2>
          </caption>

          <c:if test="${car != null}">
            <input type="hidden" name="id" value="<c:out value='${car.id}' />"/>
          </c:if>

          <fieldset class="form-group">
            <label>Производитель</label> <input type="text"
                                                value="<c:out value='${car.manufacturer}' />"
                                                class="form-control"
                                                name="nameCar" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>Модель</label> <input type="text"
                                         value="<c:out value='${car.model}' />" class="form-control"
                                         name="modelF">
          </fieldset>

          <fieldset class="form-group">
            <label>Год выпуска</label> <input type="text"
                                              value="<c:out value='${car.year}' />" class="form-control"
                                              name="yearF">
          </fieldset>
          <fieldset class="form-group">
            <label>Цена</label> <input type="text"
                                       value="<c:out value='${car.price}' />" class="form-control"
                                       name="priceF">
          </fieldset>

          <button type="submit" class="btn btn-outline-success">Сохранить</button>
        </form>
    </div>
  </div>
</div>
</body>
</html>
