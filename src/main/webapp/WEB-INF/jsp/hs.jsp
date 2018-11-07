<%@ page import="entity.User" %>
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
    <% if( ((User)request.getAttribute("u")).quantityOfCards() < 10){ %>

    <a href='/deck'><input type='submit' value='Form your deck!'/></a>

    <% } %>
</div>
<div id="battle" style="width: 100%; height: 100px; background-color: blueviolet; position: static ">

    <% if( ((User)request.getAttribute("u")).quantityOfCards() < 10){ %>

    <a href='/deck'><input type='submit' value='Form your deck!'/></a>

    <% }else{ %>

    <a href="/battle/wait">
        <input type="submit" value="TO BATTLE"/>
    </a>

    <% } %>
</div>
</body>

</html>