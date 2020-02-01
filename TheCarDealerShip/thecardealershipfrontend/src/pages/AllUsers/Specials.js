import React, { Component } from 'react';
import axios from 'axios';
import SpecialList from '../../components/SpecialList';

class Specials extends Component {
    state = {
        specials: [],
    }

    componentDidMount() {
        this.getSpecials();
    }

    getSpecials() {
        axios.get('http://localhost:8080/specials/readAll')
            .then(response => {
                this.setState({
                    specials: response.data
                })
            }).catch(error => {
                console.log(error);
            })
    }

    render() {
        const specials = this.state.specials;

        return (
            <div className="container" >
                <SpecialList specials={specials} />
            </div>
        );
    }
}


export default Specials;