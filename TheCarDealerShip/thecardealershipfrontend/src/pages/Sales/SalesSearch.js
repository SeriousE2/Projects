import React, { Component } from 'react';
import SearchInventory from '../../components/SearchInventory';
import axios from 'axios';
import VehicleList from '../../components/VehicleList';


class SalesSearch extends Component {
    state = {
        vehicleList: [],
    }

    getVehicles = (criteria) => {
        axios.post('http://localhost:8080/vehicle/all', criteria
        ).then(response => {
            this.setState(this.state.vehicleList = response.data);
        }).catch(error => {
            console.log('Error with POST: ', error);
        })
    }

    render() {
        return (
            <div className="container">
                <h2>Sales</h2>
                <SearchInventory onSubmit={this.getVehicles} />
                <VehicleList vehicleList={this.state.vehicleList} typeOfList="salesSearch" />
            </div >
        );
    }
}

export default SalesSearch;