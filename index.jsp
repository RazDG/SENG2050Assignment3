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
      <h2>User Log In</h2>
    </div>
    <div>
      <form name="loginForm" method="post" action="login">
        Username: <input type="text" name="username"> </br>
        Password: <input type="text" name="password"> </br>
        <input type="submit" value="Log In" onClick="return validateLogin()" />
      </form>
    </div>
    <p>
      LOGINS FOR TESTING: <br />
      Username: Student1 Password: abc <br />
      Username: Student2 Password: abc <br />
    </p>
  </body>
</html>
