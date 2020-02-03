initMap();

function initMap() {
  let address = {
    title: "Block Busters",
    location: { lat: 37.9752, lng: 23.7434 },
    description: "Where cruiosity was created",
    city: "Athens, Greece",
    number: "214-916-0963"
  };

  let styles = [
    {
      featureType: "water",
      stylers: [{ color: "#4d71a3" }]
    }
  ];

  let map = new google.maps.Map(document.getElementById("map"), {
    zoom: 4,
    center: address.location,
    styles: styles
  });

  let addressMarker = new google.maps.Marker({
    map: map,
    position: address.location,
    animation: google.maps.Animation.DROP
  });

  let addressInfo = new google.maps.InfoWindow({
    content: `<h3>${address.title}</h3><hr/><p class="info-words">${
      address.description
    }</p><p class="info-words">${address.number}</p>`,
    maxWidth: 300
  });

  addressMarker.addListener("click", function() {
    addressInfo.open(map, addressMarker);
  });
}
