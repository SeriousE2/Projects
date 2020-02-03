function addUser() {
  let firstName = $("#first-name").val(),
    lastName = $("#last-name").val(),
    email = $("#email").val(),
    role = $("#role").val(),
    password1 = $("#password1").val(),
    password2 = $("#password2").val();

  console.log("role: " + role);

  let url = `http://localhost:8080/user/create?firstName=${firstName}&lastName=${lastName}&phone=${firstName}&email=${email}&role=${role}&password1=${password1}&password2=${password2}`;

  console.log(url);

  $.ajax({
    type: "POST",
    url: url,
    success: function(user) {
      console.log(user);
    },
    error: function(err) {
      console.log(err);
    }
  });
}

function logOutUser() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/user/logOut`,
    success: function(user) {
      hideDropdownAndShowLoginButton();
    },
    error: function(err) {
      hideLoginButtonAndShowDropdown();
    }
  });
}

function hideLoginButtonAndShowDropdown() {
  $("#login-btn").hide();
}

function hideDropdownAndShowLoginButton() {
  $("#user-dropdown").hide();
}

function showDropdownItems() {
  $("#user-dropdown-items").toggle();
}

function showAdminAndUserNavTabs() {
  $("#admin-nav").show();
  $("#sales-nav").show();
}

function showSalesNavTab() {
  $("#sales-nav").show();
}
