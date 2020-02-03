$(document).ready(function() {
  getUsers();
});

function getUsers() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/user/readAll`,
    success: function(users) {
      loadUsers(users);
    },
    error: function(err) {
      alert("BackEnd Error");
    }
  });
}

function loadUsers(users) {
  let tableBody = $("tbody");
  tableBody.empty();
  users.forEach(user => {
    tableBody.append(`<tr>
    <td scope="row" class="cell cell-short">${user.profile.fullName}</td>
    <td class="cell cell-short">${user.profile.fullName}</td>
    <td class="cell cell-long">${user.profile.email}</td>
    <td class="cell cell-short">${user.role}</td>
    <td class="cell cell-short"><a href="editUser.html#${
      user.userId
    }"><p>Edit</p></td></a>
    </tr>`);
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
