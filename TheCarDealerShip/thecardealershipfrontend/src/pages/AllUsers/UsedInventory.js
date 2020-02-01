import React, { Component } from 'react';
import SearchInventory from '../../components/SearchInventory';
import axios from 'axios';
import VehicleList from '../../components/VehicleList';


class UsedInventory extends Component {
    state = {
        vehicleList: [],
    }

    getVehicles = (criteria) => {
        axios.post('http://localhost:8080/vehicle/used', criteria
        ).then(response => {
            this.setState(this.state.vehicleList = response.data);
        }).catch(error => {
            console.log('Error with POST: ', error);
        })
    }

    render() {
        return (
            <div className="container">
                <h2>Used Inventory</h2>
                <SearchInventory onSubmit={this.getVehicles} typeOfSearch="userSearch" />
                <VehicleList vehicleList={this.state.vehicleList} typeOfList="userSearch" />
            </div>
        );
    }
}

export default UsedInventory;