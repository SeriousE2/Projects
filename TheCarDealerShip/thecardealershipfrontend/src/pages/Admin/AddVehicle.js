import React, { Component, Fragment } from 'react';
import axios from 'axios';
import AddVehicleInfo from '../../components/AddVehicleInfo';

class AddVehicle extends Component {
    state = {
        errorMessage: []
    }

    addVehicle = (vehicleData) => {
        axios.post('http://localhost:8080/vehicle', vehicleData
        ).then(response => {
            console.log('Post success!', response);
            window.location.href = '/addSuccess';
        }).catch(error => {
            console.log('Error with POST: ', error.response.data);
            this.setState({errorMessage: error.response.data})
        })
    }

    render() {
        const errorMessage = this.state.errorMessage;
        console.log('error message: ', errorMessage);
        return (
            <Fragment>
                <div className="container">
                    <h2>Add Vehicle</h2>
                    <AddVehicleInfo onSubmit={this.addVehicle} errorMessage={this.state.errorMessage}/>
                    {errorMessage.length > 0 && 
                        <div>
                        {errorMessage.map((error) => {
                            return (
                                <h4>{error}</h4>
                            ) 
                        })}
                    </div>}
                </div>
            </Fragment>

        )
    }
}

export default AddVehicle;