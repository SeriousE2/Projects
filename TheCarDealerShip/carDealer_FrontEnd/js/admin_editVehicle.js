$(document).ready(function() {
  let id = window.location.hash.substring(1);
  getVehicle(id);
});

let vehicleImage;

let id = window.location.hash.substring(1);

// Set up Form

function getMakes(id) {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/make/readAll`,
    success: function(makes) {
      loadMakes(makes, id);
    },
    error: function(err) {
      alert("error with makes: ", err);
    }
  });
}

function loadMakes(makes, id) {
  let makesDropdown = $("#make");
  makesDropdown.empty();
  makes.forEach(make => {
    if (make.makeId == id) {
      makesDropdown.append(`<option selected value="${make.makeId}">
            ${make.makeName}
          </option>`);
    } else {
      makesDropdown.append(`<option value="${make.makeId}">
            ${make.makeName}
          </option>`);
    }
  });
}

function getModelsByMake() {
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

function getModelsByMakeId(makeId, modelId) {
  console.log(makeId);

  $.ajax({
    type: "GET",
    url: `http://localhost:8080/model/readAllByMakeId?makeId=${makeId}`,
    success: function(models) {
      loadModelsWithModelId(models, modelId);
    },
    failure: function(err) {
      console.log(err);
    }
  });
}

function loadModelsWithModelId(models, modelId) {
  let modelDropdown = $("#model");
  modelDropdown.empty();
  models.forEach(model => {
    if (model.modelId == modelId) {
      modelDropdown.append(`<option selected value="${model.id}">
      ${model.modelName}
    </option>`);
    } else {
      modelDropdown.append(`<option value="${model.id}">
      ${model.modelName}
    </option>`);
    }
  });
}

function loadModels(models) {
  let modelDropdown = $("#model");
  modelDropdown.empty();
  models.forEach(model => {
    modelDropdown.append(`<option value="${model.id}">
      ${model.modelName}
    </option>`);
  });
}

// Listen to makes

$("#make").on("select", getModelsByMake());

// Populate form with vehicle data

function getVehicle(vehicleId) {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/vehicles/readOne?vehicleId=${vehicleId}`,
    success: function(vehicle) {
      getMakes(vehicle.make.makeId);
      getModelsByMakeId(vehicle.make.makeId, vehicle.model.modelId);
      loadVehicle(vehicle);
    },
    error: function(e) {
      console.log(e);
    }
  });
}

function loadVehicle(vehicle) {
  let isFeatured = vehicle.isFeatured;
  vehicleImage = vehicle.image;

  // $("#make").selectedIndex = vehicle.make.makeName;
  // $("#model").selectedIndex = vehicle.model.modelName;
  $("#type").selectedIndex = vehicle.vehicleType;
  $("#body-style").selectedIndex = vehicle.bodyStyle;
  $("#year").val(vehicle.vehicleYear);
  $("#transmission").val(vehicle.transmission);
  $("#exterior-color").val(vehicle.exteriorColor);
  $("#interior-color").val(vehicle.interiorColor);
  $("#mileage").val(vehicle.mileage);
  $("#vin").val(vehicle.vin);
  $("#sale-price").val(vehicle.listPrice);
  $("#msrp").val(vehicle.msrp);
  $("#description").text(vehicle.vehicleDescription);
  $("#picture").text(`../images/${vehicle.image}`);
  $("#vehicle-image").attr("src", `../images/${vehicle.image}`);
  if (isFeatured) {
    $("#isFeatured").prop("checked", true);
  }
}

// Edit vehicle

function updateVehicle() {
  let isFeatured = false;
  if ($("#isFeatured:checked")) {
    isFeatured = true;
  }

  if ($("#picture").val() != "") {
    vehicleImage = $("#picture")
      .val()
      .substring(12);
  }

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
    image: vehicleImage,
    createdBy: { userId: "1" },
    isFeatured: isFeatured,
    vehicleId: id
  };

  let url = `http://localhost:8080/vehicles/edit`;

  console.log(data);

  $.ajax({
    type: "PUT",
    url: url,
    data: JSON.stringify(data),
    dataType: "json",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json; charset-utf-8"
    },
    success: function(success) {
      alert("vehicle updated");
    },
    error: function(err) {
      alert("Vehicle Updated");
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
