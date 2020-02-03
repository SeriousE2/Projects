$(document).ready(function() {
  getAllSpecials();
  getFeaturedVehicles();
  getCurrentUser();
});

// Declare variables
let specialsContainer = $(".specials-container");
let featuresContainer = $(".features-display");

function getAllSpecials() {
  specialsContainer.empty();

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/specials/readAll",
    success: function(specials) {
      loadSpecials(specials);
    },
    error: function() {
      alert("Error With Specials");
    }
  });
}

function getFeaturedVehicles() {
  featuresContainer.empty();

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/vehicles/featured",
    success: function(vehicles) {
      loadFeatured(vehicles);
    },
    error: function() {
      alert("Error With Vehicles");
    }
  });
}

function loadSpecials(specials) {
  for (i = 0; i < specials.length; i++) {
    if (i == 0) {
      specialsContainer.append(
        `<div class="carousel-item active special">
              <img
                src="../images/${specials[i].vehicle.image}"
                class="d-block w-100 special-img"
                alt="vehicle image"
              />
              <div class="carousel-caption d-none d-md-block special-details">
                <h5 class="special-title">${specials[i].title}</h5>
                <p class="special-details">${specials[i].specialDescription}</p>
              </div>
            </div>`
      );
    } else {
      specialsContainer.append(
        `<div class="carousel-item special">
              <img
                src="../images/${specials[i].vehicle.image}"
                class="d-block w-100 special-img"
                alt="vehicle image"
              />
              <div class="carousel-caption d-none d-md-block special-details">
                <h5 class="special-title">${specials[i].title}</h5>
                <p class="special-details">${specials[i].specialDescription}</p>
              </div>
            </div>`
      );
    }
  }
}

function loadFeatured(features) {
  features.forEach(feature => {
    console.log(feature);
    featuresContainer.append(`<div class="feature">
    <img
      class="feature-img"
      src="../images/${feature.image}"
      alt="vehicle-image"
    />
    <div class="feature-details">
      <h3 style="font-size: 40px" class="feature-title1">${
        feature.make.makeName
      }</h3>
      <h3  style="font-size: 2em" class="feature-title2">${
        feature.model.modelName
      }</h3>
      <div class="feature-description-div">
        <p class="feature-description">${feature.vehicleDescription}</p>
      </div
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
  console.log(out);
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
