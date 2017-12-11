<%@page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%
    request.getSession().removeAttribute("soundService");
    request.getSession().removeAttribute("stream");
%>
<html>
<head>
    <script type="text/javascript">
        function playSoundCaptcha() {
            var wavURL = '<%= request.getContextPath() %>' + '/SoundCaptchaServlet';
            var embedAudioPlayer = "<EMBED SRC='" + wavURL + "' HIDDEN='true' AUTOSTART='true'  />";
            var wavArea = document.getElementById("wavArea");
            wavArea.innerHTML = embedAudioPlayer;
        }
    </script>
</head>

<form action="CaptchaValidationServlet" method="post">
    <table>
        <tr>
            <td colspan="2"><img src="/ImageCaptchaServlet"/></td>
        </tr>
        <tr>
            <td>请输入您所看到或听到的字符:</td>
            <td><input type="text" name="captcha_input" value=""/>
                <a href="#" onFocus="playSoundCaptcha()">
                    <img src="image/wheelchair.jpg" height="18px" width="18px" alt="">
                </a>
                <%=request.getAttribute("ERROR") == null ? "" : request.getAttribute("ERROR")%>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"/></td>
        </tr>
        <div id="wavArea" style="display:none"></div>
    </table>
</form>
</html>
