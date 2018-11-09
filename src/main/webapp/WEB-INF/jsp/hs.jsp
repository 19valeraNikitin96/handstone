<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>HandStone</title>
</head>

<body style='background-image: url(http://365psd.ru/images/backgrounds/bg-light-4818.png)'>
<div id="playerInfo" style="width: 100%; height: auto; background-color: aquamarine; position: static">
    <h1>Player: ${u.login}</h1>
    <h1>Level: ${u.lvl}</h1>
    <h1>Class: ${u.cla$$}</h1>
    <h1>Date of registration: ${u.date}</h1>
    <h1>Money: ${u.money}</h1>
</div>
<div id="deck" style="width: 100%; height: auto; background-color: bisque">
    <h1>Cards quantity: ${u.quantityOfCards()}</h1>
    <a href='/deck'><input type='submit' value='Form your deck!'/></a>
    <c:forEach items="${cards}" var="card">
        <h1>Name: ${card.name}; Cost: ${card.cost}</h1>
    </c:forEach>
</div>
<div id="battle" style="width: 100%; height: 100px; background-color: blueviolet; position: static ">

    <c:choose>
        <c:when test="${u.quantityOfCards() != 10}">
            <a href='/deck'><input type='submit' value='Form your deck!'/></a>
        </c:when>
        <c:otherwise>
            <a href="/battle/wait">
                <input type="submit" value="TO BATTLE"/>
            </a>
        </c:otherwise>
    </c:choose>
</div>
</body>

</html>