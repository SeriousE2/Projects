import React, { Component } from 'react';
import SearchInventory from '../../components/SearchInventory';
import axios from 'axios';
import VehicleList from '../../components/VehicleList';


class AdminSearch extends Component {
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
        const addLink = "/admin/addVehicle";
        return (
            <div className="container">
                <h2>Admin Search</h2>
                <a className="btn-primary" href={addLink}><button>Add a New Vehicle</button></a>
                <SearchInventory onSubmit={this.getVehicles} />
                <VehicleList vehicleList={this.state.vehicleList} typeOfList="adminSearch" />
            </div>
        );
    }
}

export default AdminSearch;