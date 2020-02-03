$(document).ready(function() {
  let id = window.location.hash.substring(1);
  console.log(id);
  editThis(id);
});

function editThis(id) {
  let url = `http://localhost:8080/user/readOne?id=${id}`;
  console.log(url);
  $.ajax({
    type: "GET",
    url: url,
    success: function(user) {
      editUser(user);
    },
    error: function(err) {
      console.log("Backend Failure", err);
    }
  });
}

function editUser(user) {
  let editUserForm = $("#form-section");
  editUserForm.empty();
  console.log(user);

  editUserForm.append(`<div id="edit-user-form" class="form user-form"><div class="user-input-container">
      <label for="first-name" class="input-label">Name</label>
      <input id="first-name" class="text" placeholder="${
        user.profile.fullName
      }"/>
      <label for="last-name" class="input-label">Last Name</label>
      <input id="last-name" class="text" placeholder="${
        user.profile.fullName
      }" />
    </div>
    <div class="user-input-container">
      <label for="email" class="input-label">Email</label>
      <input id="email" class="text"  placeholder="${user.profile.email}"/>
  
      <label for="role" class="input-label">Role</label>
      <select id="role">
        <option value="admin">Admin</option>
        <option value="sales">Sales</option>
        <option value="neither">No Role</option>
      </select>
    </div>
    <div class="user-input-container">
      <label for="password1" class="input-label">Password</label>
      <input id="password1" class="text" />
      <label for="password2" class="input-label">Password</label>
      <input id="password2" class="text" />
    </div>
  
    <a href="index.html"
      ><button type="button" class="submit-button" onclick="">
        Save
      </button></a
    ></div>`);
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
