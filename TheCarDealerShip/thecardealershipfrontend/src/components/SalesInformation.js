import React, { Component } from 'react';
import propTypes from 'prop-types';

class SalesInformation extends Component {
    static propTypes = {
        onSubmit: propTypes.func,
        vehicle: propTypes.object
    }

    state = {
        purchaseData: {
            name: '',
            phone: '',
            email: '',
            street1: '',
            street2: '',
            city: '',
            state: '',
            zipCode: '',
            purchasePrice: 0,
            purchaseType: '',
            user: {
                userId: 1
            },
            vehicle: {
                vehicleId: this.props.vehicle.vehicleId
            }
        }

    }

    handleChangeFor = (propertyName) => {

        return (event) => {
            this.setState({
                purchaseData: {
                    ...this.state.purchaseData,
                    [propertyName]: event.target.value
                }
            })
        }
    }

    handleSubmit = (event) => {
        event.preventDefault();
        const purchaseData = this.state.purchaseData;
        console.log(purchaseData);
        this.props.onSubmit(purchaseData);
        this.emptyState();
    }

    emptyState() {
        this.setState({
            purchaseData: {
                name: '',
                phone: '',
                email: '',
                street1: '',
                street2: '',
                city: '',
                state: 'AL',
                zipCode: '',
                purchasePrice: 0,
                purchaseType: 'Bank Finance',
                user: {
                    userId: 1
                },
                vehicle: {
                    vehicleId: this.props.vehicle.vehicleId
                }
            }
        })
    }

    render() {
        const purchaseData = this.state.purchaseData;
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="purchase-name">Full Name*</label>
                            <input type="text" value={purchaseData.name}
                                onChange={this.handleChangeFor('name')} class="form-control"
                                id="purchase-name" placeholder="Name" />
                        </div>
                        <div class="form-group col-md-6">
                            <label for="purchase-phone">Phone Number</label>
                            <input type="text" value={purchaseData.phone}
                                onChange={this.handleChangeFor('phone')}
                                class="form-control" id="purchase-phone" placeholder="Phone" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="purchase-email">Email</label>
                        <input type="text" value={purchaseData.email}
                            onChange={this.handleChangeFor('email')} class="form-control"
                            id="purchase-email" placeholder="name@example.com" />
                            <small id="emailHelp" class="form-text">Either phone number or email is required.</small>
                    </div>
                    <div class="form-group">
                        <label for="inputAddress">Address*</label>
                        <input type="text" value={purchaseData.street1}
                            onChange={this.handleChangeFor('street1')} class="form-control"
                            id="inputAddress" placeholder="1234 Main St" />
                    </div>
                    <div class="form-group">
                        <label for="inputAddress2">Address 2</label>
                        <input type="text" value={purchaseData.street2}
                            onChange={this.handleChangeFor('street2')} class="form-control"
                            id="inputAddress2" placeholder="Apartment, studio, or floor" />
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputCity">City*</label>
                            <input type="text" value={purchaseData.city}
                                onChange={this.handleChangeFor('city')} class="form-control"
                                id="inputCity" placeholder="City" />
                        </div>
                        <div class="form-group col-md-4">
                            <label for="inputState">State*</label>
                            <select value={purchaseData.state} onChange={this.handleChangeFor('state')}
                                id="inputState" className="form-control">
                                <option value="">Choose...</option>
                                <option value="AL">AL</option>
                                <option value="AK">AK</option>
                                <option value="AZ">AZ</option>
                                <option value="AR">AR</option>
                                <option value="CA">CA</option>
                                <option value="CO">CO</option>
                                <option value="CT">CT</option>
                                <option value="DE">DE</option>
                                <option value="DC">DC</option>
                                <option value="FL">FL</option>
                                <option value="GA">GA</option>
                                <option value="HI">HI</option>
                                <option value="ID">ID</option>
                                <option value="IL">IL</option>
                                <option value="IN">IN</option>
                                <option value="IA">IA</option>
                                <option value="KS">KS</option>
                                <option value="KY">KY</option>
                                <option value="LA">LA</option>
                                <option value="ME">ME</option>
                                <option value="MD">MD</option>
                                <option value="MA">MA</option>
                                <option value="MI">MI</option>
                                <option value="MN">MN</option>
                                <option value="MS">MS</option>
                                <option value="MO">MO</option>
                                <option value="MT">MT</option>
                                <option value="NE">NE</option>
                                <option value="NV">NV</option>
                                <option value="NH">NH</option>
                                <option value="NJ">NJ</option>
                                <option value="NM">NM</option>
                                <option value="NY">NY</option>
                                <option value="NC">NC</option>
                                <option value="ND">ND</option>
                                <option value="OH">OH</option>
                                <option value="OK">OK</option>
                                <option value="OR">OR</option>
                                <option value="PA">PA</option>
                                <option value="RI">RI</option>
                                <option value="SC">SC</option>
                                <option value="SD">SD</option>
                                <option value="TN">TN</option>
                                <option value="TX">TX</option>
                                <option value="UT">UT</option>
                                <option value="VT">VT</option>
                                <option value="VA">VA</option>
                                <option value="WA">WA</option>
                                <option value="WV">WV</option>
                                <option value="WI">WI</option>
                                <option value="WY">WY</option>
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="inputZip">Zip Code*</label>
                            <input type="text" value={purchaseData.zipCode}
                                onChange={this.handleChangeFor('zipCode')}
                                class="form-control" id="inputZip" />
                        </div>
                    </div>
                    <hr />
                    <div class="form-group">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="purchase-price">Purchase Price*</label>
                                <input type="text" value={purchaseData.purchasePrice}
                                    onChange={this.handleChangeFor('purchasePrice')}
                                    class="form-control" id="purchase-price"
                                    placeholder="Purchase Price" />
                                    <small class="form-text">Purchase price must be at least 95% of sale price. 
                                        <br />Purchase price cannot exceed MSRP.</small>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="purchase-type">Purchase Type*</label>
                                <select class="form-control" id="purchase-type"
                                    placeholder="Select Purchase Type" value={purchaseData.purchaseType}
                                    onChange={this.handleChangeFor('purchaseType')}>
                                    <option value="">Choose...</option>
                                    <option value="Bank Finance">Bank Finance</option>
                                    <option value="Cash">Cash</option>
                                    <option value="Dealer Finance">Dealer Finance</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn">Save Purchase</button>
                    <br />
                    <br />
                </form>
            </div>
        );
    }
}

export default SalesInformation;