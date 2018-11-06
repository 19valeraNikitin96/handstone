<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
</head>

  <body style='background-image: url(http://i.piccy.info/i9/956ac4bcccdc6759946b476394372745/1539107322/12937/1226734/bgMain.png)'>

 <table align='center' style="background-image: url(http://365psd.ru/images/backgrounds/bg-light-4818.png)">

    <tr>
        <td align='right'>
            <form action='<c:url value="/"/>' method='GET'>
                <p >
                    Login:<input type='text' name='login' placeholder='Input your login'>
                </p>
                <p >
                    password:<input type='password' name='pass' placeholder='Input your password'>
                </p>
            </form>
            <form action='/' method='GET'>
                <p align='left'>
                    <input type='submit' value='SIGN IN'>
                </p>
                <p align='right'>
                    <input type='submit' value='SIGN UP'>
                </p>
            </form>
        </td>
    </tr>
 </table>
</body>

</html>
