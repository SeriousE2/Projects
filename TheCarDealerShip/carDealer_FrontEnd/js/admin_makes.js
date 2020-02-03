$(document).ready(function() {
  getMakes();
});

function getMakes() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/make/readAll`,
    success: function(makes) {
      loadMakes(makes);
    },
    error: function(err) {
      alert("error with makes");
    }
  });
}

function loadMakes(makes) {
  let tableBody = $("tbody");
  tableBody.empty();
  makes.forEach(make => {
    $("tbody").append(`<tr>
          <th>${make.makeName}</th>
          <th>${make.dateAdded}</th>
          <th>${make.createdBy.profile.fullName}</th>
        </tr>`);
  });
}

function loadMake(make) {
  let tableBody = $("tbody");
  tableBody.append(`<tr>
          <th>${make.makeName}</th>
          <th>${make.dateAdded}</th>
          <th>${make.createdBy.profile.fullName}</th>
        </tr>`);
}

function addMake() {
  let newMake = $("#new-make").val();
  let url = `http://localhost:8080/make/create?makeName=${newMake}&userId=1`;
  console.log(url);
  $.ajax({
    type: "POST",
    url: url,
    success: function(make) {
      loadMake(make);
      $("#new-make").val("");
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
