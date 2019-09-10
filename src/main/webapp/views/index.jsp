<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <title>Welcome page!!!</title>
</head>
<body>
<div style="text-align: center">
    <p>Welcome, please config your app's info</p>
    <table align="center" border="1">
        <tr>
            <td>
                <p>To use this helper, you have to set up your Slack and Zoom as follows:</p>
                <p>1) add a Slash Command with Request URL:</p>
                <p>   https://video-from-zoom.herokuapp.com/recordings - will give you video URL</p>
                <p>   https://video-from-zoom.herokuapp.com/user - will give you some User info</p>
                <p>2) build a JWT type app in Zoom and set its vales here</p>
                <p>   - email is an email you logged on Zoom</p>
                <p>3) use slash command in Slack with email address used in Zoom</p>
                <p>   (for example "/getuser test@test.com")</p>
            </td>
            <td>
                ${message}
                <form method="post">
                    <p>Zoom API key <input name="zoom_api_key" type="text"></p>
                    <p>Zoom API secret <input name="zoom_api_secret" type="text"></p>
                    <p>Zoom email <input name="email" type="text"></p>
                    <p>Days for Zoom token <input name="duration" type="number"></p>
                    <button type="submit" formaction="/init/zoom">Set Zoom</button>
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
