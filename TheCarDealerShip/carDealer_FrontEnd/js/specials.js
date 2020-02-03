$(document).ready(function() {
  getAllSpecials();
  getCurrentUser();
});

let specialsContainer = $(".specials");

function getAllSpecials() {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/specials/readAll",
    success: function(specials) {
      loadSpecials(specials);
    },
    error: function() {
      alert("Error With Specials");
    }
  });
}

function loadSpecials(specials) {
  specialsContainer.empty();
  specials.forEach(special => {
    specialsContainer.append(`<div class="special">
        <div class="special-icon">
          <i class="fas fa-cash-register fa-7x"></i>
        </div>
        <div class="special-details">
          <h3 class="special-title">${special.title}</h3>
          <P class="special-description"
            >${special.specialDescription}</P
          >
        </div>
      </div>`);
  });
}
function getCurrentUser() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/user/isLoggedIn`,
    success: function(user) {
      if (user.role == "admin") {
        showAdminAndUserNavTabs();
      } else {
        showSalesNavTab();
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

function showAdminAndUserNavTabs() {
  $("#admin-nav").show();
  $("#sales-nav").show();
}

function showSalesNavTab() {
  $("#sales-nav").show();
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
