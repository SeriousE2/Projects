$(document).ready(function() {
  loadSales();
  getUsers();
});

function loadSales() {
  let userId = $("#users option:selected").val();

  if (userId == undefined) {
    userId = 0;
  }
  let from = $("#from").text(),
    to = $("#to").text();

  if (from == "") {
    from = null;
  }

  if (to == "") {
    to = null;
  }

  console.log(userId, from, to);

  $.ajax({
    type: "GET",
    url: `http://localhost:8080/purchase/salesReport?id=${userId}&startingOnString=${from}&toString=${to}`,
    success: function(salesReport) {
      console.log(salesReport);
      addSalesReport(salesReport);
    },
    error: function(err) {
      console.log(err);
    }
  });
}

function addSalesReport(salesReport) {
  let table = $("#table-body");
  table.empty();

  salesReport.forEach(report => {
    table.append(
      `<tr><td scope="row">${report.userId}</td>
            <td>${report.netSales}</td>
            <td>${report.numberOfSales}</td></tr>`
    );
  });
}

function getUsers() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/user/readAll`,
    success: function(users) {
      loadUsers(users);
    },
    error: function(err) {
      alert("BackEnd Error");
    }
  });
}

function loadUsers(users) {
  let userDropdown = $("#users");
  userDropdown.empty();

  userDropdown.append(`<option value=0>All</option>`);

  users.forEach(user => {
    userDropdown.append(`<option value=${user.userId}>${user.userId}</option>`);
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
