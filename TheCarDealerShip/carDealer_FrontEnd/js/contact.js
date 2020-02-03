$(document).ready(function() {
  let id = window.location.hash.substring(1);
  if (id) {
    getVehicleVin(id);
  }
  getCurrentUser();
});

function getVehicleVin(vehicleId) {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/vehicles/readOne?vehicleId=${vehicleId}`,
    success: function(vehicle) {
      addVehicleVinToMessage(vehicle);
    },
    error: function() {
      alert("Back End Error");
    }
  });
}

function addVehicleVinToMessage(vehicle) {
  let message = $("#message");
  console.log(vehicle);
  message.text(vehicle.vin);
}

function makeContact() {
  let name = $("#name").val(),
    email = $("#email").val(),
    phone = $("#phone").val(),
    message = $("#message").val();
  let url = `http://localhost:8080/contact/create?name=${name}&email=${email}&phone=${phone}&message=${message}`;

  console.log(url);

  $.ajax({
    type: "POST",
    url: url,
    success: function(contact) {
      alert("Contact Made");
      console.log(contact);
    },
    error: function() {
      alert("Error With Contact");
    }
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
