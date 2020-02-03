$(document).ready(function() {
  getAllSpecials();
  getAvailableInventory();
});

function getAllSpecials() {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/specials/readAll",
    success: function(specials) {
      loadSpecials(specials);
      console.log(specials);
    },
    error: function() {
      alert("Error With Specials");
    }
  });
}

function getAvailableInventory() {
  console.log("gettingInventory");
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/vehicles/availableVehicles`,
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
  vehicles = $("#vehicles");
  console.table(inventory);
  vehicles.empty();
  inventory.forEach(vehicle => {
    vehicles.append(
      `<li onclick="addActiveClass()" value=${
        vehicle.vehicleId
      } class="list-group-item vehicle"><div><p>${vehicle.vehicleYear}  ${
        vehicle.make.makeName
      }   ${vehicle.model.modelName}</p></div>
        <div><p>${vehicle.vin}</p></div></li>`
    );
  });
  listenForSelection();
}

function loadSpecials(specials) {
  let specialsContainer = $("#specials-container");
  specialsContainer.empty();
  specials.forEach(special => {
    specialsContainer.append(`<li class="special">
    <div class="special-icon">
      <i class="fas fa-cash-register fa-7x"></i>
    </div>
    <div class="special-details">
      <div class="special-top">
        <h3 class="special-title">${special.title}</h3>
        <button class="delete-special-btn btn btn-danger" onclick="deleteThis(${
          special.specialId
        })">Delete</button>
      </div>
      <P class="special-description"
        >${special.specialDescription}</P
      >
    </div>
  </li>`);
  });
}

function createSpecial() {
  let title = $("#title").val(),
    description = $("#descripton").val();
  vehicleId = $("#vehicles")
    .find("li.active")
    .val();

  $.ajax({
    type: "POST",
    url: `http://localhost:8080/specials/create?title=${title}&description=${description}&vehicleId=${vehicleId}&userId=1`,
    success: function(special) {
      getAllSpecials();
      title.val("");
      description.text("");
    },
    error: function(err) {
      console.log("ERROR" + err);
    }
  });
}

function deleteThis(specialId) {
  $.ajax({
    type: "POST",
    url: `http://localhost:8080/specials/delete?id=${specialId}`,
    success: function(special) {
      getAllSpecials();
    },
    error: function(err) {
      console.log("ERROR" + err);
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

function listenForSelection() {
  let vehicles = $("#vehicles");
  let vehiclesLoaded = $(".vehicle");
  vehicles.on("click", ".vehicle", function() {
    let active = vehicles.find("li.active");
    active.removeClass("active");
    console.log($(this).val());
    $(this).addClass("active");
  });
}
