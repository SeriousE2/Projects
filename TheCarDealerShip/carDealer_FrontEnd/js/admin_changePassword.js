$(document).ready(function() {});

function changePassword() {
  let password1 = $("#password1").val();
  let password2 = $("#password2").val();

  if (password1 == password2) {
    updatePassword(password1);
  } else {
    alert("Passwords Must Match");
  }
}

function updatePassword(password) {
  $.ajax({
    type: "PUT",
    url: `http://localhost:8080/user/changePassword?password=${password}`,
    success: function(success) {
      console.log(success);
    },
    failure: function(err) {
      console.log(err);
    }
  });
}

function getCurrentUser() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/user/isLoggedIn`,
    success: function(user) {
      if (user.role == "admin") {
        showAdminTabs();
      }
      hideLoginButtonAndShowDropdown();
    },
    error: function(err) {
      hideDropdownAndShowLoginButton();
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

function showAdminTabs() {
  $("#admin-nav").show();
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
