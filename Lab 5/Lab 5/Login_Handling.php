<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns = "http://www.w3.org/1999/xhtml" xml:lang = "en">
    <head>
        <meta http-equiv = "content-type" content = "text/html; charset = utf-8" />
        <title> Sign In Handling </title>
    </head>
    <body>
        
        <?php
        
        //error management
        ini_set ('display_errors', 1);
        error_reporting (E_ALL | E_STRICT);
        
        //tracking success
        $okay = TRUE;
        
        //getting variables from Book_Login.html
        $fname = $_POST['fname'];
        $lname = $_POST['lname'];
        $uname = $_POST['uname'];
        $name = $fname . ' ' . $lname;
        $password = $_POST['password'];
        
        //fixing values of username and password
        $username = "SuperAdmin";
        $passwordd = "IamSuperAdmin";
        
        //first name validation
        if(empty($_POST['fname']) && (empty($_POST['lname']) && (empty($_POST['uname']) && (empty($_POST['password']))))){
            print '<p class = "error">Please enter your first and last name, username and password.</p>';
            $okay = FALSE;
        }elseif((ctype_alpha($fname) && (ctype_alpha($lname) && ($uname == $username) && ($password == $passwordd)))){
            $okay = TRUE;
        }else{
            print '<p class = "error"> First and last name should only be alphabets, invalid username or password.</p>';
            $okay = FALSE;
        }
        
        //success message when no error has been found
        if ($okay){
            print '<p> YAY! You have been successfully logged in to the Book Website!</p>';
            print "<p>Hello, $name!</p>";
            print "<p>You are registered and signed in as $uname.</p>";
        }
        
        
        
        ?>
        
    </body>
</html>