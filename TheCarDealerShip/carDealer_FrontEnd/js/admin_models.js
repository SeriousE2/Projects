$(document).ready(function() {
  getModels();
  getMakes();
});

function getModels() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/model/readAll`,
    success: function(model) {
      loadModels(model);
    },
    error: function(err) {
      alert("error with model");
    }
  });
}

function loadModels(models) {
  models.forEach(model => {
    $("tbody").append(`<tr>
        <th>${model.modelName}</th>
        <th>${model.make.makeName}</th>
        <th>${model.dateAdded}</th>
        <th>${model.createdBy.profile.fullName}</th>
      </tr>`);
  });
}

function loadModel(model) {
  $("tbody").append(`<tr>
        <th>${model.modelName}</th>
        <th>${model.make.makeName}</th>
        <th>${model.dateAdded}</th>
        <th>${model.createdBy.profile.fullName}</th>
      </tr>`);
}

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
  let makesDropdown = $("#makes");
  makesDropdown.empty();
  makes.forEach(make => {
    makesDropdown.append(`<option value="${make.makeId}">
          ${make.makeName}
        </option>`);
  });
}

function addModel() {
  let newModel = $("#new-model").val();
  let makeId = $("#makes option:selected").val();
  let url = `http://localhost:8080/model/create?makeId=${makeId}&modelName=${newModel}&userId=1`;
  console.log(url);
  $.ajax({
    type: "POST",
    url: url,
    success: function(model) {
      loadModel(model);
      $("#new-model").val("");
    },
    error: function(err) {
      console.log(err);
    }
  });
  loadmodels();
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
