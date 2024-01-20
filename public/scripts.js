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
  function updateUser() {
              var oldUsername = localStorage.getItem("activeUser");
              var username = $('#user').val();
              var password = $('#password').val();
              var email = $('#mail').val();

              $.ajax({
                  contentType: "application/json",
                  type: 'PUT',
                  url: "/dsaApp/player/update/"+oldUsername,
                  data: JSON.stringify({username: username, password: password, email: email}),
                  dataType: 'json',
                  success: function (result) {
                  alert("Information updated successfully");
                      localStorage.setItem("activeUser", username);
                      window.location.href = "profile.html";
                  },
                  error : function(error) {
                      if (username == "" || password == "" || email == "")
                          alert("You left something blank. Please try again!");
                      else
                          alert("Username or email already in use. Please try again!");
                      window.location.href = "cambiardatos.html";
                  }
              });
  }

  function logout() {
          localStorage.clear();
          window.location.href = "index.html";
  }
  function getItemsList(){
          $.ajax({
              type: 'GET',
              url: "/dsaApp/item/storeList",
              dataType: 'json',
              success:function (result) {
                  for (let i = 0; i < result.length; i++) {
                      console.log("i: "+i, result[i]);
                      $("#itemsTable").append(
                          "<tr> <td>" + result[i].name +
                          "</td> <td>" + result[i].description +
                          "</td> <td>" + result[i].price +
                          "</td> <td>" + result[i].type +
                          "</td><td>" +  '<img src ="images/' + result[i].image + '.gif.url" width = "100" height ="100">' + "</td></tr>" +
                          '</td><td>' + '<button type = "button" class = "button" id="' + result[i].id +
                          '" onclick="PurchaseItem(this.id)"  >Purchase</button>' + '</td> </tr>');

                  }},

              error: function (error) {
                  alert("Unable to get Shop data.");
                  console.log(error);
                  window.location.href = "Main.html";
              }
          })
      }

      function PurchaseItem(item) {
              var userName = localStorage.getItem("activeUser");
              var itemId = item;
              $.ajax({
                  type: 'PUT',
                  url: "dsaApp/item/buyItem/" + itemId + "/" + userName,
                  dataType: 'json',
                  success: function (result) {
                      localStorage.setItem("coins", result.coins);
                      window.location.href = "shop.html";
                      alert('Bought succesfully');
                  },
                  error: function (error) {
                      alert('Purchase failed');
                  },
              })
          }



