
let URL = 'http://localhost:8080';  // URL of the server (change if needed)
function signup(){
                     var username = $('#signup_username').val();
                     var password = $('#signup_password').val();
                     var telephone = $('#signup_telephone').val();
                     var email = $('#signup_email').val();

                     $.ajax({
                         contentType: "application/json",
                         type: 'POST',
                         url: '/dsaApp/player/signup',
                         data: JSON.stringify({"username": username, "password": password,"telephone": telephone, "email": email}),
                         dataType: 'json',
                         success: function (result) {
                             alert("Sign up completed. Welcome to Croacky, " + username);
                             localStorage.setItem("activeUser", username);
                             window.location.href = "playerProfile.html";
                         },
                         error : function(error) {
                             if (username == "" || password == "" || telephone == "" || email == "")
                                 alert("You left something blank. Please try again!");
                             else
                                 alert("Username or email already in use. Please try again!");
                         }
                     });
}

function login(){
  var username = $('#login_username').val();
    var password = $('#login_password').val();

    $.ajax({
        contentType: "application/json",
        type: 'POST',
        url: '/dsaApp/player/login',
        data: JSON.stringify({"username": username, "password": password}),
        dataType: 'json',
        success: function (result) {
            alert("Login successful. Welcome back to Croacky, " + username);
            localStorage.setItem("activeUser", username);
            localStorage.setItem("activeUserId", result.id);
            localStorage.setItem("activeUserCoins", result.coins);
            window.location.href = "profile.html";
        },
        error : function(error) {
            if (username == "" || password == "")
                alert("You left something blank, please try again!");
            else
                alert("Wrong username or password, please try again!");
        }
    });
  }

  function logout() {
          localStorage.clear();
          window.location.href = "index.html";
  }