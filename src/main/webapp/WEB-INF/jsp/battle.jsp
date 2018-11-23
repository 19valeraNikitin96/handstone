<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${b.login1} vs ${b.login2}</title>
</head>
<body>
<table>
    <tr>
        <td>
            <table border='1' width='100%'>
                <tr>
                    <td>
                        <h1>${b.login1} ${b.hp1}</h1>
                    </td>
                    <td>
                        <h1>YOUR MANA:</h1>
                        <h1>${b.mana1}</h1>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h1>YOU HAVE</h1>
                        <h1>${b.deck1.size()}</h1>
                        <h1>CARDS</h1>
                        <h1>IN DECK</h1>
                    </td>
                    <td>
                        <h3>YOUR HAND:</h3>
                        <c:forEach items="${b.inHand1}" var="card">
                            <h2>Name: ${card.name};Attack: ${card.attack};
                                Defence: ${card.defence}; Mana: ${card.cost};</h2>
                            <c:if test="${b.isMove1() && b.mana1 >= card.cost}">
                                <form action="/battle" method="POST">
                                    <input type="submit" value="PLAY"/>
                                    <input type="hidden" name="play" value="${card.id}"/>
                                </form>
                            </c:if>
                        </c:forEach>
                        <hr/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h3>YOUR TABLE</h3>
                        <c:forEach items="${b.onTable1}" var="card">
                            <h2>Name: ${card.name}; About: ${card.about};
                                Attack: ${card.attack};Defence: ${card.defence}</h2>
                            <c:if test="${b.isMove1() && b.cardChoosen == null}">
                                <form action="/battle" method="POST">
                                    <input type="submit" value="CHOOSE"/>
                                    <input type="hidden" name="choice" value="${card.id}"/>
                                </form>
                            </c:if>
                            <c:if test="${b.isMove1() && b.cardChoosen != null}">
                                <form action="/battle" method="POST">
                                    <input type="submit" value="UNCHOOSE"/>
                                    <input type="hidden" name="unchoice"/>
                                </form>
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <h1>TURN IS ${b.turn}</h1>
        </td>
        <td>
            <c:if test="${b.isMove1()}">
                <c:if test="${b.cardChoosen == null}">
                    <form action="/battle" method="POST">
                        <input type="hidden" name="end" value="true"/>
                        <input type="submit" value="END TURN"/>
                    </form>
                </c:if>
            </c:if>
            <c:if test="${!b.isMove1()}">
                <form action="/battle" method="GET">
                    <input type="submit" value="REFRESH"/>
                </form>
            </c:if>
        </td>
    </tr>
    <tr>
        <td>
            <table border='1' width='100%'>
                <tr>
                    <td>
                        <h1>${b.login2} ${b.hp2}</h1>
                        <c:if test="${b.cardChoosen != null}">
                            <form action="/battle" method="POST">
                                <input type="hidden" name="attack" value="0"/>
                                <input type="submit" value="ATTACK"/>
                            </form>
                        </c:if>
                    </td>
                    <td>
                        <h1>HIS MANA:</h1>
                        <h1>${b.mana2}</h1>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h1>HE HAS</h1>
                        <h1>${b.deck2.size()}</h1>
                        <h1>CARDS</h1>
                        <h1>IN DECK</h1>
                    </td>
                    <td>
                        <h3>HIS HAND:</h3>
                        <c:forEach items="${b.inHand2}" var="card">
                            <h2>CARD</h2>
                        </c:forEach>
                        <hr/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h3>HIS TABLE</h3>
                        <c:forEach items="${b.onTable2}" var="card">
                            <h2>Name: ${card.name}; About: ${card.about};
                                Attack: ${card.attack};Defence: ${card.defence}</h2>
                            <c:if test="${b.cardChoosen != null}">
                                <form action="/battle" method="POST">
                                    <input type="hidden" name="attack" value="${card.id}"/>
                                    <input type="submit" value="ATTACK"/>
                                </form>
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
            </table>
        </td>
    </tr>


</table>


</body>
</html>
