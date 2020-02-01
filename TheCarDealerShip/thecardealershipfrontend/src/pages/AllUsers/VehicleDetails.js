import React, { Component, Fragment } from 'react';
import axios from 'axios';

class Vehicle extends Component {
    constructor(props) {
        super(props);
        this.state = {
            vehicle: null,
            contactLink: null
        }
    }

    componentDidMount() {
        const { match: { params } } = this.props;
        console.log('props for details: ', this.props.match.params);

        axios.get(`http://localhost:8080/vehicle/${params.vehicleId}`)
            .then(({ data: vehicle }) => {
                console.log('vehicle', vehicle);
                this.setState({
                    vehicle: vehicle,
                    contactLink: "/contact/" + vehicle.vin
                });
            });
    }

    render() {
        const vehicle = this.state.vehicle;
        return (
            <Fragment>
                {vehicle ? (
                    <div className="container">
                        <h2>Vehicle Details</h2>
                        <div class="vehicle-details">
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
                                </div>
                            </div>
                            <div className="row justify-content-end">
                                <div className="col-9">
                                    <p><strong>Description: </strong>{vehicle.description}</p>
                                </div>
                            </div>
                            <div className="row justify-content-end">
                                <div className="col-2">
                                <a className="btn btn-success" href={this.state.contactLink}>Contact Us</a>
                                </div>
                            </div>
                        </div>
                    </div>
                ) : (
                        <div className="container">Error</div>
                    )}
            </Fragment>

        )
    }
}

export default Vehicle;