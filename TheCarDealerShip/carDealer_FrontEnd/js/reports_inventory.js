$(document).ready(function() {
  loadInventory();
});

function loadInventory() {
  loadNewInventory();
  loadUsedInventory();
}

function loadNewInventory() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/purchase/inventoryNew`,
    success: function(inventory) {
      showInventory(inventory, "new");
    },
    error: function(err) {
      console.log("ERROR" + err);
    }
  });
}

function loadUsedInventory() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/purchase/inventoryUsed`,
    success: function(loadUsedInventory) {
      showInventory(loadUsedInventory, "used");
    },
    error: function(err) {
      console.log("ERROR" + err);
    }
  });
}

function showInventory(inventory, type) {
  let newTable = $("#new-table-body");
  let usedTable = $("#used-table-body");

  let table;

  if (type == "new") {
    table = newTable;
  } else {
    table = usedTable;
  }

  inventory.forEach(row => {
    console.table(row);
    table.append(`<tr>
            <td scope="row" scope="col">${row.year}</td>
            <td scope="col">${row.makeName}</td>
            <td scope="col">${row.modelName}</td>
            <td scope="col">${row.count}</td>
            <td scope="col">${row.stockValue}</td></tr>`);
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
