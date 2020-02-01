import React, { Component } from 'react';
import propTypes from 'prop-types'
import FeaturedVehicle from './FeaturedVehicle';
import Vehicle from './Vehicle';

class VehicleList extends Component {
    static propTypes = {
        vehicleList: propTypes.array,
        typeOfList: propTypes.string
    }

    static defaultProps = {
        vehicleList: []
    }

    render() {
        const vehicles = this.props.vehicleList;
        const typeOfList = this.props.typeOfList;
        return (
            <div>
                {vehicles.map((vehicle) => {
                    return (
                        <Vehicle typeOfList={typeOfList} vehicle={vehicle} />
                    ) 
                })}
            </div>
        )
    }
}

export default VehicleList;