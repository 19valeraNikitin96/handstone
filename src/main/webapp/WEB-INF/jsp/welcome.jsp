<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
</head>

<body style='background-image: url(http://365psd.ru/images/backgrounds/bg-light-4818.png)'>

 <table align='center' style="background-image: url(http://365psd.ru/images/backgrounds/bg-light-4818.png)">

    <tr>
        <td align='right'>
            <form action='/' method='POST'>
                <p >
                    Login:<input type='text' name='login' placeholder='Input your login'>
                </p>
                <p >
                    password:<input type='password' name='pass' placeholder='Input your password'>
                </p>
                <p align='left'>
                    <input type='submit' value='SIGN IN'>
                </p>
            </form>
            <form action='/register' method='GET'>

                <p align='right'>
                    <input type='submit' value='SIGN UP'>
                </p>
            </form>
        </td>
    </tr>
 </table>
</body>

</html>
