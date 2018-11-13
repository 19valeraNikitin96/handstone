<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>HandStone</title>
</head>

<body style='background-image: url(https://i.pinimg.com/originals/04/3c/2d/043c2db6ce558080824e1db878b33882.gif)'>

<h1>Waiting opponent...</h1>
<form action="/battle/wait" method="GET">
    <input type="submit" value="Refresh"/>
</form>
<a href="/battle/wait?exit=true" method="GET">
    <input type="submit" value="Leave WaitList"/>
</a>
</body>

</html>
