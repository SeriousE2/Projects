$(document).ready(function() {
  let id = window.location.hash.substring(1);
  getVehicle(id);
});

function getVehicle(vehicleId) {
  console.log(vehicleId);
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/vehicles/readOne?vehicleId=${vehicleId}`,
    success: function(vehicle) {
      loadVehicle(vehicle);
    },
    error: function(e) {
      console.log(e);
    }
  });
}

function loadVehicle(vehicle) {
  $("#purchase-button").val(vehicle.vehicleId);
  $("#vehicle-to-purchase").append(`<div class="vehicle">
  <div class="vehicle-title">
    <h3 class="title-item vehicle-year">${vehicle.vehicleYear}</h3>
    <h3 class="title-item vehicle-make">${vehicle.make.makeName}</h3>
    <h3 class="title-item vehicle-model">${vehicle.model.modelName}</h3>
  </div>
  <div class="vehicle-details-container">
    <img class="vehicle-img" src="../images/${vehicle.image}" alt="shoe" />
    <div class="vehicle-details">
      <div class="detail-key-value">
        <label class="label" for="bodyStyle">Body Style:</label>
        <p id="bodyStyle" class="body-style">${vehicle.bodyStyle}</p>
      </div>
      <div class="detail-key-value">
        <label for="transmission" class="label">Trans:</label>
        <p id="transmission" class="body-style">${vehicle.transmission}</p>
      </div>
      <div class="detail-key-value">
        <label for="exteriorColor" class="label">Color:</label>
        <p id="exteriorColor" class="body-style">${vehicle.exteriorColor}</p>
      </div>
    </div>
    <div class="vehicle-details">
      <div class="detail-key-value">
        <label for="interiorColor" class="label">Interior:</label>
        <p id="interiorColor" class="body-style">${vehicle.interiorColor}</p>
      </div>
      <div class="detail-key-value">
        <label for="mileage" class="label">Mileage:</label>
        <p id="mileage" class="body-style">${vehicle.mileage}</p>
      </div>
      <div class="detail-key-value">
        <label for="vin" class="label">Vin #:</label>
        <p id="vin" class="body-style">${vehicle.vin}</p>
      </div>
    </div>
    <div class="vehicle-details">
      <div for="listPrice" class="detail-key-value">
        <label class="label">Sale Price:</label>
        <p id="listPrice" class="body-style">${vehicle.listPrice}</p>
      </div>
      <div class="detail-key-value">
        <label for="msrp" class="label">MSRP:</label>
        <p id="msrp" class="body-style">${vehicle.msrp}</p>
      </div>
    </div>
  </div>
</div>`);
  $("#sales-form").append(`<a href="index.html"
><button
  id="purchase-button"
  type="button"
  class="submit-button btn btn-primary"
  onclick="makePurchase(${vehicle.vehicleId})"
>
  Save
</button></a
>`);
}

function makePurchase(soldVehicleId) {
  let street = $("#street1").val() + $("#street2").val();
  let data = {
    vehicle: { vehicleId: soldVehicleId },
    customerProfile: {
      fullName: $("#name").val(),
      email: $("#email").val(),
      number: $("#phone").val(),
      streetAddress: street,
      zipcode: $("#zipcode").val()
    },
    salePrice: $("#purchase-price").val(),
    saleType: $("#purchase-type").val(),
    createdBy: { userId: "4" }
  };
  console.log(data);

  let url = `http://localhost:8080/purchase/purchase`;

  $.ajax({
    type: "POST",
    url: url,
    data: JSON.stringify(data),
    dataType: "json",
    headers: { Accept: "application/json", "Content-Type": "application/json" },
    success: function(vehicle) {
      console.log(vehicle);
    },
    error: function(jqXHR, textStatus, errorThrown) {
      console.warn(jqXHR.responseText);
    }
  });
}

function makePurchaseByUser(soldVehicleId, userId) {
  let street = $("#street1").val() + $("#street2").val();
  let data = {
    vehicle: { vehicleId: soldVehicleId },
    customerProfile: {
      fullName: $("#name").val(),
      email: $("#email").val(),
      number: $("#phone").val(),
      streetAddress: street,
      zipcode: $("#zipcode").val()
    },
    salePrice: $("#purchase-price").val(),
    saleType: $("#purchase-type").val(),
    createdBy: { userId: userId }
  };
  console.log(data);

  let url = `http://localhost:8080/purchase/purchase`;

  $.ajax({
    type: "POST",
    url: url,
    data: JSON.stringify(data),
    dataType: "json",
    headers: { Accept: "application/json", "Content-Type": "application/json" },
    success: function(vehicle) {
      console.log(vehicle);
    },
    error: function(jqXHR, textStatus, errorThrown) {
      console.warn(jqXHR.responseText);
    }
  });
}

function getCurrentUserAndMakePurchase() {
  console.log("firstFunction");
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/user/isLoggedIn`,
    success: function(user) {
      console.log("success callback");
      let id = window.location.hash.substring(1);
      makePurchaseByUser(id, user.userId);
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
