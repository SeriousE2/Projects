import React, { Component, Fragment } from 'react';



class Vehicle extends Component {

    render() {
        const vehicle = this.props.vehicle;
        const typeOfList = this.props.typeOfList;

        const detailsLink = "/inventory/details/" + vehicle.vehicleId;
        const purchaseLink = "/sales/purchase/" + vehicle.vehicleId;
        const editLink = "/admin/editVehicle/" + vehicle.vehicleId;

        return (

            <Fragment>
                {vehicle ? (
                    <div class="search-vehicle" key={vehicle.vehicleId.toString()}>
                        <div class="row">
                            <div class="col-3">
                                <p><strong>{vehicle.year} {vehicle.make.make} {vehicle.model.model}</strong></p>
                                <img src="https://images.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.freestockphotos.biz%2Fpictures%2F15%2F15685%2FIllustration%2Bof%2Ba%2Bred%2Bcartoon%2Bcar.png&f=1" width="100" height="100" className="d-inline-block align-center" alt="car" />
                            </div>
                            <div class="col-3">
                                <p></p>
                                <p><strong>Body Style: </strong>{vehicle.bodyStyle}</p>
                                <p><strong>Trans: </strong>{vehicle.transmission}</p>
                                <p><strong>Color: </strong>{vehicle.color}</p>
                            </div>

                            <div class="col-3">
                                <p></p>
                                <p><strong>Interior: </strong>{vehicle.interior}</p>
                                <p><strong>Mileage: </strong>{vehicle.mileage}</p>
                                <p><strong>VIN: </strong>{vehicle.vin}</p>
                            </div>

                            <div class="col-3">
                                <p></p>
                                <p><strong>Sale Price: </strong>${vehicle.salePrice}</p>
                                <p><strong>MSRP: </strong>${vehicle.msrp}</p>
                                {typeOfList == "userSearch" &&
                                    <div><a className="btn btn-primary" href={detailsLink}>Details</a></div>}
                                {typeOfList == "salesSearch" &&
                                    <div><a className="btn btn-primary" href={purchaseLink}>Purchase</a></div>}
                                {typeOfList == "adminSearch" &&
                                    <div><a className="btn btn-primary" href={editLink}>Edit</a></div>}
                            </div>

                        </div>
                    </div>
                ) : (
                        <div>Error: Could not get vehicles</div>
                    )}
            </Fragment>

        )
    }
}

export default Vehicle;
