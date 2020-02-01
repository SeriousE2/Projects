import React, { Component } from 'react';
import propTypes from 'prop-types';
import axios from 'axios';
import Checkbox from './Checkbox';

class AddVehicleInfo extends Component {
    static propTypes = {
        onSubmit: propTypes.func,
        vehicle: propTypes.object
    }

    state = {
        vehicleData: {
            vehicleId: this.props.vehicle ? this.props.vehicle.vehicleId : '',
            make: {
                makeId: this.props.vehicle ? this.props.vehicle.make.makeId : ''
            },
            model: {
                modelId: this.props.vehicle ? this.props.vehicle.model.modelId : ''
            },
            type: this.props.vehicle ? this.props.vehicle.type : '',
            bodyStyle: this.props.vehicle ? this.props.vehicle.bodyStyle : '',
            year: this.props.vehicle ? this.props.vehicle.year : '',
            transmission: this.props.vehicle ? this.props.vehicle.transmission : '',
            color: this.props.vehicle ? this.props.vehicle.color : '',
            interior: this.props.vehicle ? this.props.vehicle.interior : '',
            mileage: this.props.vehicle ? this.props.vehicle.mileage : 0,
            vin: this.props.vehicle ? this.props.vehicle.vin : '',
            msrp: this.props.vehicle ? this.props.vehicle.msrp : 0,
            salePrice: this.props.vehicle ? this.props.vehicle.salePrice : 0,
            description: this.props.vehicle ? this.props.vehicle.description : '',
            photo: this.props.vehicle ? this.props.vehicle.photo : 'photo',
            featured: this.props.vehicle ? this.props.vehicle.featured : '',
        },
        makes: [],
        models: []
    }

    componentWillMount = () => {
        this.selectedCheckboxes = new Set();
    }

    componentDidMount() {
        this.getMakes();
        this.getModels();
    }

    getMakes() {
        axios.get('http://localhost:8080/make/all')
            .then(response => {
                this.setState({
                    makes: response.data
                })
            }).catch(error => {
                console.log(error);
            })
    }

    getModels() {
        axios.get('http://localhost:8080/model/all')
            .then(response => {
                this.setState({
                    models: response.data
                })
            }).catch(error => {
                console.log(error);
            })
    }

    handleChangeFor = (propertyName) => {

        return (event) => {
            this.setState({
                vehicleData: {
                    ...this.state.vehicleData,
                    [propertyName]: event.target.value
                }
            })
        }
    }

    handleNestedChangeFor = (property1, property2) => {

        return (event) => {
            this.setState({
                vehicleData: {
                    ...this.state.vehicleData,
                    [property1]: {
                        ...this.state.property1,
                        [property2]: event.target.value
                    }
                }
            })
        }
    }


    handleSubmit = (event) => {
        event.preventDefault();
        const vehicleData = this.state.vehicleData;
        this.props.onSubmit(vehicleData);
        // if(this.props.errorMessage.length == 0) {
        //     this.emptyState();
        // }
    }

    emptyState() {
        this.setState({
            vehicleId: this.props.vehicle ? this.props.vehicle.vehicleId : '',
            vehicleData: {
                make: {
                    makeId: this.props.vehicle ? this.props.vehicle.make.makeId : ''
                },
                model: {
                    modelId: this.props.vehicle ? this.props.vehicle.model.modelId : ''
                },
                type: this.props.vehicle ? this.props.vehicle.type : '',
                bodyStyle: this.props.vehicle ? this.props.vehicle.bodyStyle : '',
                year: this.props.vehicle ? this.props.vehicle.year : '',
                transmission: this.props.vehicle ? this.props.vehicle.transmission : '',
                color: this.props.vehicle ? this.props.vehicle.color : '',
                interior: this.props.vehicle ? this.props.vehicle.interior : '',
                mileage: this.props.vehicle ? this.props.vehicle.mileage : 0,
                vin: this.props.vehicle ? this.props.vehicle.vin : '',
                msrp: this.props.vehicle ? this.props.vehicle.msrp : 0,
                salePrice: this.props.vehicle ? this.props.vehicle.salePrice : 0,
                description: this.props.vehicle ? this.props.vehicle.description : '',
                photo: 'photo',
                featured: this.props.vehicle ? this.props.vehicle.featured : false,
            },
        })
    }

    toggleCheckbox = label => {
        if (this.selectedCheckboxes.has(label)) {
            this.selectedCheckboxes.delete(label);
        } else {
            this.selectedCheckboxes.add(label);
        }
    }

    render() {
        const vehicleData = this.state.vehicleData;
        const makes = this.state.makes;
        const models = this.state.models;
        if (this.props.vehicle) {
            console.log('vehicle featured: ', this.props.vehicle.featured);
        }
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <div class="form-row">
                        <div class="col">
                            <label>Make</label>
                            <select className="form-control" value={vehicleData.make.makeId}
                                onChange={this.handleNestedChangeFor('make', 'makeId')}>
                                <option>Choose...</option>
                                {makes.map((make) => {
                                    return (
                                        <option value={make.makeId}>{make.make}</option>
                                    )
                                })}
                            </select>
                        </div>
                        <div class="col">
                            <label>Model</label>
                            <select className="form-control" value={vehicleData.model.modelId}
                                onChange={this.handleNestedChangeFor('model', 'modelId')}>
                                <option>Choose...</option>
                                {models.filter((model) => {
                                    return (model.make.makeId == this.state.vehicleData.make.makeId);
                                }).map((model) => {
                                    return (
                                        <option value={model.modelId}>{model.model}</option>
                                    )
                                })}
                            </select>
                        </div>
                    </div>
                    <br />
                    <div class="form-row">
                        <div class="col">
                            <label>Type</label>
                            <select className="form-control" value={vehicleData.type}
                                onChange={this.handleChangeFor('type')}>
                                <option>Choose...</option>
                                <option value="New">New</option>
                                <option value="Used">Used</option>
                            </select>
                        </div>
                        <div class="col">
                            <label>Body Style</label>
                            <select className="form-control" value={vehicleData.bodyStyle}
                                onChange={this.handleChangeFor('bodyStyle')}>
                                <option>Choose...</option>
                                <option value="Car">Car</option>
                                <option value="SUV">SUV</option>
                                <option value="Truck">Truck</option>
                                <option value="Van">Van</option>
                            </select>
                        </div>
                    </div>
                    <br />
                    <div class="form-row">
                        <div class="col">
                            <label>Year</label>
                            <input className="form-control" value={vehicleData.year}
                                onChange={this.handleChangeFor('year')} />
                        </div>
                        <div class="col">
                            <label>Transmission</label>
                            <select className="form-control"
                                value={vehicleData.transmission}
                                onChange={this.handleChangeFor('transmission')}>
                                <option>Select Transmission</option>
                                <option value="Manual">Manual</option>
                                <option value="Automatic">Automatic</option>
                            </select>
                        </div>
                    </div>
                    <br />
                    <div class="form-row">
                        <div class="col">
                            <label>Color</label>
                            <select className="form-control" value={vehicleData.color}
                                onChange={this.handleChangeFor('color')}>
                                <option>Select Color</option>
                                <option value="Red">Red</option>
                                <option value="Yellow">Yellow</option>
                                <option value="Blue">Blue</option>
                                <option value="Black">Black</option>
                                <option value="Silver">Silver</option>
                                <option value="White">White</option>
                            </select>
                        </div>
                        <div class="col">
                            <label>Interior</label>
                            <select className="form-control"
                                value={vehicleData.interior}
                                onChange={this.handleChangeFor('interior')}>
                                <option>Select Interior</option>
                                <option value="Black">Black</option>
                                <option value="Silver">Beige</option>
                                <option value="White">Burgundy</option>
                            </select>
                        </div>
                    </div>
                    <br />
                    <div className="form-row">
                        <div className="col">
                            <label>Mileage</label>
                            <input className="form-control" value={vehicleData.mileage}
                                onChange={this.handleChangeFor('mileage')} />
                        </div>
                        <div class="col">
                            <label>VIN</label>
                            <input className="form-control" value={vehicleData.vin}
                                onChange={this.handleChangeFor('vin')} />
                        </div>
                    </div>
                    <br />
                    <div class="form-row">
                        <div class="col">
                            <label>MSRP</label>
                            <input className="form-control" value={vehicleData.msrp}
                                onChange={this.handleChangeFor('msrp')} />
                        </div>
                        <div class="col">
                            <label>Sale Price</label>
                            <input className="form-control" value={vehicleData.salePrice}
                                onChange={this.handleChangeFor('salePrice')} />
                        </div>
                    </div>
                    <br />
                    <div>
                        <label>Description</label>
                        <textarea className="form-control" value={vehicleData.description}
                            onChange={this.handleChangeFor('description')} />
                    </div>
                    <br />
                    <button type="submit" class="btn">Save Vehicle</button>
                    <br />
                </form >
            </div>
        );
    }
}

export default AddVehicleInfo;