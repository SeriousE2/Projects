$(document).ready(function() {
  getMakes();
});

function getMakes() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/make/readAll`,
    success: function(makes) {
      getModelsByMake(loadMakes(makes));
    },
    error: function(err) {
      alert("error with makes");
    }
  });
}

function loadMakes(makes) {
  let makesDropdown = $("#make");
  makesDropdown.empty();
  makes.forEach(make => {
    makesDropdown.append(`<option value="${make.makeId}">
            ${make.makeName}
          </option>`);
  });
  return makes[0].makeId;
}

function getModelsByMake(makeId) {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/model/readAllByMakeId?makeId=${makeId}`,
    success: function(models) {
      loadModels(models);
    },
    failure: function(err) {
      console.log(err);
    }
  });
}

function getModelsByMakeId() {
  let makeId = $("#make option:selected").val();

  $.ajax({
    type: "GET",
    url: `http://localhost:8080/model/readAllByMakeId?makeId=${makeId}`,
    success: function(models) {
      loadModels(models);
    },
    failure: function(err) {
      console.log(err);
    }
  });
}

$("#make").on("select", getModelsByMakeId());

function loadModels(models) {
  let modelDropdown = $("#model");
  modelDropdown.empty();
  models.forEach(model => {
    modelDropdown.append(`<option value="${model.id}">
      ${model.modelName}
    </option>`);
  });
}

// Add vehicle

function addVehicle() {
  let data = {
    make: { makeId: $("#make option:selected").val() },
    model: {
      modelId: $("#model option:selected").val(),
      modelName: $("#model option:selected")
        .text()
        .trim()
    },
    vehicleType: $("#type option:selected").val(),
    bodyStyle: $("#body-style").val(),
    vehicleYear: $("#year").val(),
    transmission: $("#transmission option:selected").val(),
    exteriorColor: $("#exterior-color option:selected").val(),
    interiorColor: $("#interior-color option:selected").val(),
    mileage: $("#mileage").val(),
    vin: $("#vin").val(),
    msrp: $("#msrp").val(),
    listPrice: $("#sale-price").val(),
    vehicleDescription: $("#description").val(),
    image: $("#picture")
      .val()
      .substring(12),
    createdBy: { userId: "1" }
  };

  let url = `http://localhost:8080/vehicles/build`;

  console.log(data);

  $.ajax({
    type: "POST",
    url: url,
    data: JSON.stringify(data),
    dataType: "json",
    headers: { Accept: "application/json", "Content-Type": "application/json" },
    success: function(vehicle) {
      alert("vehicle added");
      console.log(vehicle);
    },
    error: function(err) {
      alert("vehicle add fail");
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
