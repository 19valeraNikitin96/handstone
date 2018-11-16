<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>"${battle.player1.login}" vs "${battle.player2.login}"</title>
</head>
<body>

<table border='2' width='100%'>

    <tr>
        <td style="background-image: url(https://st2.depositphotos.com/2632879/5643/i/950/depositphotos_56430513-stock-photo-background-old-city-wall.jpg)">
            <h1>"${battle.player1.login}"</h1>
            <c:if test="${currentUser.equals(battle.player1)}">
                <c:forEach items="${battle.player1_cards}" var="card">

                    <h1>Name: ${card.name}; Cost: ${card.cost}</h1>
                    <br>
                </c:forEach>
            </c:if>
            <c:if test="${!currentUser.equals(battle.player1)}">
                <c:forEach items="${battle.player1_cards}" var="card">

                    <h1>Opponent card</h1>
                    <br>
                </c:forEach>
            </c:if>
        </td>
        <td width='40%' style="background-image: url(https://images.wallpaperscraft.ru/image/derevyannyj_poverkhnost_tekstura_doski_118443_300x168.jpg)">

        </td>
        <td style="background-image: url(https://st2.depositphotos.com/2632879/5643/i/950/depositphotos_56430513-stock-photo-background-old-city-wall.jpg)">
            <h1>"${battle.player2.login}"</h1>

            <c:if test="${currentUser.equals(battle.player2)}">
                <c:forEach items="${battle.player2_cards}" var="card">

                    <h1>Name: ${card.name}; Cost: ${card.cost}</h1>
                    <br>
                </c:forEach>
            </c:if>
            <c:if test="${!currentUser.equals(battle.player2)}">
                <c:forEach items="${battle.player2_cards}" var="card">

                    <h1>Opponent card</h1>
                    <br>
                </c:forEach>
            </c:if>
        </td>
    </tr>
</table>
</body>
</html>
