$(document).ready(function() {
  getCurrentUser();
});

let results = $("#results-container");
(query = $("#query").val()),
  (minPrice = $("#price-min").val()),
  (maxPrice = $("#price-max").val()),
  (minYear = $("#year-min").val()),
  (maxYear = $("#year-max").val());

function getInventory() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/vehicles/vehiclesFiltered?query=${query}&type=none&minPrice=${minPrice}&maxPrice=${maxPrice}&minYear=${minYear}&maxYear=${maxYear}`,
    success: function(inventory) {
      loadInventory(inventory);
    },
    error: function() {
      alert("Back End Error");
    }
  });
}

function getAllInventory() {
  console.log("gettingInventory");
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/vehicles/readAll`,
    success: function(inventory) {
      console.log("recievedInventory", inventory);
      loadInventory(inventory);
    },
    error: function() {
      alert("Back End Error");
    }
  });
}

function loadInventory(inventory) {
  console.log("loading");
  results.empty();
  inventory.forEach(vehicle => {
    if (vehicle.isAvailable == true) {
      results.append(`<div class="vehicle">
                      <div class="vehicle-title">
                        <h3 class="title-item vehicle-year">${
                          vehicle.vehicleYear
                        }</h3>
                        <h3 class="title-item vehicle-make">${
                          vehicle.make.makeName
                        }</h3>
                        <h3 class="title-item vehicle-model">${
                          vehicle.model.modelName
                        }</h3>
                      </div>
                      <div class="vehicle-details-container">
                        <img class="vehicle-img" src="../images/${
                          vehicle.image
                        }" alt="shoe" />
                        <div class="vehicle-details">
                          <div class="detail-key-value">
                            <label class="label" for="bodyStyle">Body Style:</label>
                            <p id="bodyStyle" class="body-style">${
                              vehicle.bodyStyle
                            }</p>
                          </div>
                          <div class="detail-key-value">
                            <label for="transmission" class="label">Trans:</label>
                            <p id="transmission" class="body-style">${
                              vehicle.transmission
                            }</p>
                          </div>
                          <div class="detail-key-value">
                            <label for="exteriorColor" class="label">Color:</label>
                            <p id="exteriorColor" class="body-style">${
                              vehicle.exteriorColor
                            }</p>
                          </div>
                        </div>
                        <div class="vehicle-details">
                          <div class="detail-key-value">
                            <label for="interiorColor" class="label">Interior:</label>
                            <p id="interiorColor" class="body-style">${
                              vehicle.interiorColor
                            }</p>
                          </div>
                          <div class="detail-key-value">
                            <label for="mileage" class="label">Mileage:</label>
                            <p id="mileage" class="body-style">${
                              vehicle.mileage
                            }</p>
                          </div>
                          <div class="detail-key-value">
                            <label for="vin" class="label">Vin #:</label>
                            <p id="vin" class="body-style">${vehicle.vin}</p>
                          </div>
                        </div>
                        <div class="vehicle-details">
                          <div for="listPrice" class="detail-key-value">
                            <label class="label">Sale Price:</label>
                            <p id="listPrice" class="body-style">${
                              vehicle.listPrice
                            }</p>
                          </div>
                          <div class="detail-key-value">
                            <label for="msrp" class="label">MSRP:</label>
                            <p id="msrp" class="body-style">${vehicle.msrp}</p>
                          </div>
                          <a href="purchase.html#${vehicle.vehicleId}"
                            ><button type="button" class="purchase-button btn btn-primary" >
                              Purchase
                            </button></a
                          >
                        </div>
                      </div>
                    </div>`);
    } else {
      results.append(`<div class="vehicle">
                      <div class="vehicle-title">
                        <h3 class="title-item vehicle-year">${
                          vehicle.vehicleYear
                        }</h3>
                        <h3 class="title-item vehicle-make">${
                          vehicle.make.makeName
                        }</h3>
                        <h3 class="title-item vehicle-model">${
                          vehicle.model.modelName
                        }</h3>
                      </div>
                      <div class="vehicle-details-container">
                        <img class="vehicle-img" src="../images/${
                          vehicle.image
                        }" alt="shoe" />
                        <div class="vehicle-details">
                          <div class="detail-key-value">
                            <label class="label" for="bodyStyle">Body Style:</label>
                            <p id="bodyStyle" class="body-style">${
                              vehicle.bodyStyle
                            }</p>
                          </div>
                          <div class="detail-key-value">
                            <label for="transmission" class="label">Trans:</label>
                            <p id="transmission" class="body-style">${
                              vehicle.transmission
                            }</p>
                          </div>
                          <div class="detail-key-value">
                            <label for="exteriorColor" class="label">Color:</label>
                            <p id="exteriorColor" class="body-style">${
                              vehicle.exteriorColor
                            }</p>
                          </div>
                        </div>
                        <div class="vehicle-details">
                          <div class="detail-key-value">
                            <label for="interiorColor" class="label">Interior:</label>
                            <p id="interiorColor" class="body-style">${
                              vehicle.interiorColor
                            }</p>
                          </div>
                          <div class="detail-key-value">
                            <label for="mileage" class="label">Mileage:</label>
                            <p id="mileage" class="body-style">${
                              vehicle.mileage
                            }</p>
                          </div>
                          <div class="detail-key-value">
                            <label for="vin" class="label">Vin #:</label>
                            <p id="vin" class="body-style">${vehicle.vin}</p>
                          </div>
                        </div>
                        <div class="vehicle-details">
                          <div for="listPrice" class="detail-key-value">
                            <label class="label">Sale Price:</label>
                            <p id="listPrice" class="body-style">${
                              vehicle.listPrice
                            }</p>
                          </div>
                          <div class="detail-key-value">
                            <label for="msrp" class="label">MSRP:</label>
                            <p id="msrp" class="body-style">${vehicle.msrp}</p>
                          </div>
                          <p class="sold">SOLD</p>
                        </div>
                      </div>
                    </div>`);
    }
  });
}

function getVehicleForPurhasePage(vehicleId) {
  console.log(vehicleId);
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/vehicles/readOne?vehicleId=${vehicleId}`,
    success: function(vehicle) {
      if (callback) {
        console.log("success");
      }
      console.log("success error");
      console.log(vehicle);
    },
    error: function(error) {
      console.log(error);
    }
  });
}

function getCurrentUser() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/user/isLoggedIn`,
    success: function(user) {
      if (user.role == "admin") {
        showAdminNavTabs();
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

function showAdminNavTabs() {
  $("#admin-nav").show();
}
