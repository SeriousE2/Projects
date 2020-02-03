$(document).ready(function() {
  getCurrentUser();
  loadYears();
});

function getUsedInventory() {
  let queryString = $("#query").val();
  console.log("Query: " + queryString);
  if (queryString == "") {
    queryString = null;
  }

  let data = {
    query: queryString,
    minPrice: $("#price-min").val(),
    maxPrice: $("#price-max").val(),
    minYear: $("#year-min").val(),
    maxYear: $("#year-max").val(),
    type: "used"
  };

  console.log(data);

  $.ajax({
    type: "GET",
    data: data,
    dataType: "json",
    headers: { Accept: "application/json", "Content-Type": "application/json" },
    url: `http://localhost:8080/vehicles/vehiclesQuery`,
    success: function(inventory) {
      loadInventory(inventory);
    },
    error: function() {
      alert("Back End Error");
    }
  });
}
let results = $("#results-container");
function loadInventory(inventory) {
  console.table(inventory);
  results.empty();
  inventory.forEach(vehicle => {
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
                        <a href="details.html#${vehicle.vehicleId}"
                          ><button type="button" class="btn btn-info">
                            Details
                          </button></a
                        >
                      </div>
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

function loadYears() {
  for (i = 2000; i < 2020; i++) {
    let html = `<option value="${i}">${i}</option>`;
    $("#year-min").append(html);
    $("#year-max").append(html);
  }
}
