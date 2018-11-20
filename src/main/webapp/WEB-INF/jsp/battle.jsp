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
                    <h1>${b.login1}</h1>
                    <hr/>
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
                            <h1>Name: ${card.name}; About: ${card.about};
                                Attack: ${card.attack};Defence: ${card.defence}</h1>
                        </c:forEach>
                        <hr/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h3>YOUR TABLE</h3>
                        <c:forEach items="${b.onTable1}" var="card">
                            <h1>Name: ${card.name}; About: ${card.about};
                                Attack: ${card.attack};Defence: ${card.defence}</h1>
                        </c:forEach>
                    </td>
                </tr>
            </table>
        </td>

    </tr>
    <hr/>
    <hr/>
    <tr>

        <td>
            <table border='1' width='100%'>
                <tr>
                    <h1>${b.login2}</h1>
                    <hr/>
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
                            <h1>CARD</h1>
                        </c:forEach>
                        <hr/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h3>HIS TABLE</h3>
                        <c:forEach items="${b.onTable2}" var="card">
                            <h1>Name: ${card.name}; About: ${card.about};
                                Attack: ${card.attack};Defence: ${card.defence}</h1>
                        </c:forEach>
                    </td>
                </tr>
            </table>
        </td>

    </tr>


</table>


</body>
</html>
