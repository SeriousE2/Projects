import React, { Component, Fragment } from 'react';
import axios from 'axios';
import AddVehicleInfo from '../../components/AddVehicleInfo';

class EditVehicle extends Component {
    state = {
        vehicle: null,
        errorMessage: []
    }

    componentDidMount() {
        const { match: { params } } = this.props;
        console.log('props for details: ', this.props.match.params);

        axios.get(`http://localhost:8080/vehicle/${params.vehicleId}`)
            .then(({ data: vehicle }) => {
                console.log('vehicle', vehicle);
                this.setState({
                    vehicle: vehicle,
                });
            });
    }

    editVehicle = (vehicleData) => {
        const { match: { params } } = this.props;
        console.log('vehicle to send: ', vehicleData);
        console.log('route params: ', params.vehicleId);
        axios.put(`http://localhost:8080/vehicle/${params.vehicleId}`, vehicleData
        ).then(response => {
            console.log('Post success!', response);
            window.location.href = '/editSuccess';
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
                    <div className="container" >
                        <h2>Edit Vehicle</h2>
                        <AddVehicleInfo vehicle={vehicle} onSubmit={this.editVehicle} />
                    </div>
                ) : (
                        <div>
                            Error: {errorMessage}
                        </div>
                    )}
                {errorMessage.length > 0 &&
                    <div>
                        {errorMessage.map((error) => {
                            return (
                                <h4>{error}</h4>
                            )
                        })}
                    </div>}
            </Fragment>

        )
    }
}

export default EditVehicle;