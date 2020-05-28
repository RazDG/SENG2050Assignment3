<%@page contentType="text/html" pageEncoding="UTF-8" %>

<html>
  <head>
    <meta charset="UTF-8">
    <title>User Log In</title>
    <script type="text/javascript">
      function validateLogin()
      {
        if (document.loginForm.username.value == "")
        {
          alert("Please enter a username");
          return false;
        }
        else if (document.loginForm.password.value == "")
        {
          alert("Please enter a password");
          return false;
        }
        else return true;
      }
    </script>
  </head>

  <body>
    <div>
      <h1>User Log In</h1>
    </div>
    <div>
      <form name="loginForm" method="post" action="">
        Username: <input type="text" name="username"> </br>
        Password: <input type="text" name="password"> </br>
        <input type="submit" value="Log In" onClick="return validateLogin()" />
      </form>
    </div>
  </body>
</html>
