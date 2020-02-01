import React, { Component } from 'react';

class Vehicle extends Component {

    render() {
        const vehicle = this.props.vehicle;

        return (
                <div className="col-3 featured-vehicle-display shadow">
                    {/* Car photo here */}
                    <img src="https://images.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.freestockphotos.biz%2Fpictures%2F15%2F15685%2FIllustration%2Bof%2Ba%2Bred%2Bcartoon%2Bcar.png&f=1" width="100" height="100" className="d-inline-block align-center" alt="new cars"/>
                    <p className="vehicle-header">{vehicle.year} {vehicle.make.make} {vehicle.model.model}</p>
                    <p className="vehicle-content">${vehicle.salePrice}</p>
                </div>
        );
    }
}

export default Vehicle;