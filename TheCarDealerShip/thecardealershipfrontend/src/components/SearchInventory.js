import React, { Component } from 'react';
import propTypes from 'prop-types';

class SearchInventory extends Component {
    static propTypes = {
        onSubmit: propTypes.func
    }

    state = {
        searchCriteria: {
            makeModelYear: '',
            minSalePrice: 0,
            maxSalePrice: 0,
            minYear: 0,
            maxYear: 0
        }
    }

    handleChangeFor = (propertyName) => {
        return (event) => {
            this.setState({
                searchCriteria: {
                    ...this.state.searchCriteria,
                    [propertyName]: event.target.value
                }
            })
        }
    }

    handleSubmit = (event) => {
        event.preventDefault();
        const criteria = this.state.searchCriteria;
        this.props.onSubmit(criteria);
        this.emptyState();
    }

    emptyState() {
        this.setState({
            searchCriteria: {
                makeModelYear: '',
                minSalePrice: 0,
                maxSalePrice: 0,
                minYear: 0,
                maxYear: 0
            }
        })
    }

    render() {
        const criteria = this.state.searchCriteria;
        return (
            <div class="quick-search shadow">
                <h5>Quick Search</h5>
                <form onSubmit={this.handleSubmit}>
                    <div className="row justify-content-center">
                        <div className="form-group col-4">
                            <input className="form-control" value={criteria.makeModelYear}
                                onChange={this.handleChangeFor('makeModelYear')}
                                placeholder="Enter Make, Model or Year" />
                        </div>
                        <div class="col-4">
                            <div class="row">
                                <div className="form-group col-2 search-text">
                                    <p>Price</p>
                                </div>
                                <div className="form-group col-5">
                                    <select className="form-control" value={criteria.minSalePrice}
                                        onChange={this.handleChangeFor('minSalePrice')}>
                                        <option value=''>No Min</option>
                                        <option value='5000'>$5,000</option>
                                        <option value='6000'>$6,000</option>
                                        <option value='7000'>$7,000</option>
                                        <option value='8000'>$8,000</option>
                                        <option value='9000'>$9,000</option>
                                        <option value='10000'>$10,000</option>
                                        <option value='11000'>$11,000</option>
                                        <option value='12000'>$12,000</option>
                                        <option value='13000'>$13,000</option>
                                        <option value='14000'>$14,000</option>
                                        <option value='15000'>$15,000</option>
                                        <option value='16000'>$16,000</option>
                                    </select>
                                </div>
                                <div className="form-group col-5">
                                    <select className="form-control" value={criteria.maxSalePrice} onChange={this.handleChangeFor('maxSalePrice')}>
                                        <option value=''>No Max</option>
                                        <option value='8000'>$8,000</option>
                                        <option value='9000'>$9,000</option>
                                        <option value='10000'>$10,000</option>
                                        <option value='11000'>$11,000</option>
                                        <option value='12000'>$12,000</option>
                                        <option value='13000'>$13,000</option>
                                        <option value='14000'>$14,000</option>
                                        <option value='15000'>$15,000</option>
                                        <option value='16000'>$16,000</option>
                                        <option value='17000'>$17,000</option>
                                        <option value='18000'>$18,000</option>
                                        <option value='19000'>$19,000</option>
                                        <option value='20000'>$20,000</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="row">
                                <div className="form-group col-2 search-text">
                                    <p>Year</p>
                                </div>
                                <div className="form-group col-5">
                                    <select className="form-control" value={criteria.minYear} onChange={this.handleChangeFor('minYear')}>
                                        <option value=''>No Min</option>
                                        <option value='2008'>2008</option>
                                        <option value='2009'>2009</option>
                                        <option value='2010'>2010</option>
                                        <option value='2011'>2011</option>
                                        <option value='2012'>2012</option>
                                        <option value='2013'>2013</option>
                                        <option value='2014'>2014</option>
                                        <option value='2015'>2015</option>
                                        <option value='2016'>2016</option>
                                        <option value='2017'>2017</option>
                                        <option value='2018'>2018</option>
                                        <option value='2019'>2019</option>
                                    </select>
                                </div>
                                <div className="form-group col-5">
                                    <select className="form-control" value={criteria.maxYear} onChange={this.handleChangeFor('maxYear')}>
                                        <option value=''>No Max</option>
                                        <option value='2008'>2008</option>
                                        <option value='2009'>2009</option>
                                        <option value='2010'>2010</option>
                                        <option value='2011'>2011</option>
                                        <option value='2012'>2012</option>
                                        <option value='2013'>2013</option>
                                        <option value='2014'>2014</option>
                                        <option value='2015'>2015</option>
                                        <option value='2016'>2016</option>
                                        <option value='2017'>2017</option>
                                        <option value='2018'>2018</option>
                                        <option value='2019'>2019</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <button type="submit" className="btn btn-primary col-3">Search</button>
                    </div>
                </form>
            </div>
        );
    }
}

export default SearchInventory;