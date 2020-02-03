$(document).ready(function() {});

function loginUser() {
  let email = $("#email").val();
  let password = $("#password").val();
  let url = `http://localhost:8080/user/loginUser?email=${email}&password=${password}`;
  console.log(url);

  $.ajax({
    type: "GET",
    url: url,
    success: function(user) {
      console.log(user);
    },
    error: function(err) {
      console.log(err);
    }
  });
}
