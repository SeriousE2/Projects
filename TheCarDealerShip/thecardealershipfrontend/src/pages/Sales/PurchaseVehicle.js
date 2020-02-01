import React, { Component, Fragment } from 'react';
import axios from 'axios';
import SalesInformation from '../../components/SalesInformation';
import Vehicle from '../../components/Vehicle';

class PurchaseVehicle extends Component {
    constructor(props) {
        super(props);
        this.state = {
            vehicle: null,
            contactLink: null,
            errorMessage: []

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

    purchaseVehicle = (purchaseData) => {
        console.log('Purchase data to post: ', purchaseData);
        axios.post('http://localhost:8080/purchase', purchaseData
        ).then(response => {
            console.log('Post success!', response);
        }).catch(error => {
            console.log('Error with POST: ', error);
            this.setState({ errorMessage: error.response.data })
        })
    }

    render() {
        const vehicle = this.state.vehicle;
        const errorMessage = this.state.errorMessage;
        return (
            <Fragment>
                {vehicle ? (
                    <div className="container">
                        <h2>Purchase Vehicle</h2>
                        <Vehicle vehicle={vehicle} typeOfList="null" />
                        <SalesInformation vehicle={vehicle} onSubmit={this.purchaseVehicle} />
                        {errorMessage.length > 0 &&
                            <div>
                                {errorMessage.map((error) => {
                                    return (
                                        <h4 className="error">{error}</h4>
                                    )
                                })}
                            </div>}
                    </div>
                ) : (
                        <div className="container">
                            Error: {errorMessage}
                        </div>
                    )}

            </Fragment>

        )
    }
}

export default PurchaseVehicle;