<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <tr>

        <td>
            <table border="1">
                <tr>
                    <td>
                        <c:forEach items="${b.deck1}" var="card">
                            <h1>Name: ${card.name}; About: ${card.about};
                                Attack: ${card.attack};Defence: ${card.defence}</h1>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>
                        <c:forEach items="${b.inHand1}" var="card">
                            <h1>Name: ${card.name}; About: ${card.about};
                                Attack: ${card.attack};Defence: ${card.defence}</h1>
                        </c:forEach>
                    </td>
                    <td>
                        <h1>"${b.login1}"</h1>
                    </td>
                    <td>

                    </td>


                </tr>
            </table>
        </td>

    </tr>

    <tr>

        <td>
            <table border="1">
                <tr>
                    <td>
                        <c:forEach items="${b.inHand2}" var="card">
                            <h1>Name: ${card.name}; About: ${card.about};
                                Attack: ${card.attack};Defence: ${card.defence}</h1>
                        </c:forEach>
                    </td>
                    <td>
                        <h1>"${b.login2}"</h1>
                    </td>
                    <td>

                    </td>
                </tr>
                <tr>

                    <td>
                        <c:forEach items="${b.deck2}" var="card">
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
