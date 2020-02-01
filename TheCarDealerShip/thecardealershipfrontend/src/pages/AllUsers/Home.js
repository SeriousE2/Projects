import React, { Component } from 'react';
import Featured from '../../components/FeaturedVehicleList';
import SpecialList from '../../components/SpecialListHome';
import axios from 'axios';

class Home extends Component {
    state = {
        specials: [],
        vehicles: []
    }

    componentDidMount() {
        this.getSpecials();
        this.getFeaturedVehicles();
    }


    getSpecials() {
        axios.get('http://localhost:8080/special/all')
            .then(response => {
                this.setState({
                    specials: response.data
                })
            }).catch(error => {
                console.log(error);
            })
    }

    getFeaturedVehicles() {
        axios.get('http://localhost:8080/vehicle/featured')
            .then(response => {
                this.setState({ vehicles: response.data });
            }).catch(error => {
                console.log(error);
            })
    }

    render() {
        const specials = this.state.specials;
        const vehicles = this.state.vehicles;

        return (
            <div className="container">
                <div className="jumbotron">
                    <SpecialList specials={specials} />
                </div>
                <hr />
                <Featured vehicles={vehicles} />
            </div>

        );
    }
}

export default Home;