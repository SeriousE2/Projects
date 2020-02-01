import React, { Component } from 'react';
import axios from 'axios';


class Contact extends Component {
    constructor(props) {
        super(props);
        this.state = {
            message: {
                name: '',
                email: '',
                phone: '',
                message: props.match.params.vin != 0 ? props.match.params.vin : '',
            }
        }

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    //Currying: Returns a function
    handleChangeFor = (propertyName) => {
        return (event) => {
            console.log(event.target.value);
            this.setState({
                message: {
                    ...this.state.message,
                    [propertyName]: event.target.value

                }
            })
        }
    }

    handleSubmit = (event) => {
        event.preventDefault();
        const message = this.state.message;

        //Write a POST route for a contact message

        axios.post('http://localhost:8080/contact/create', {
            name: message.name,
            email: message.email,
            phone: message.phone,
            message: message.message,

        }).then(response => {
            console.log('Post was successful!', response);
        }).catch(error => {
            console.log('Error with POST: ', error);
        })
        console.log('form submitted: ', this.state.message);
        this.emptyState();
    }

    emptyState() {
        this.setState({
            message: {
                name: '',
                email: '',
                phone: '',
                message: '',
            }
        })
    }

    render() {
        const { match: { params } } = this.props;
        return (
            <div className="container">
                <h2>Contact Us</h2>
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label for="contact-name">Full Name</label>
                        <input type="text" value={this.state.message.name} 
                        onChange={this.handleChangeFor('name')} 
                        className="form-control" id="contact-name" 
                        placeholder="Enter name" />
                    </div>
                    <div className="form-group">
                        <label for="contact-email">Email address</label>
                        <input type="email" value={this.state.message.email} 
                        onChange={this.handleChangeFor('email')} 
                        className="form-control" id="contact-email" 
                        placeholder="name@example.com" />
                    </div>
                    <div className="form-group">
                        <label for="contact-phone">Phone</label>
                        <input type="text" value={this.state.message.phone} 
                        onChange={this.handleChangeFor('phone')} 
                        className="form-control" id="contact-phone" 
                        placeholder="Enter phone number" />
                    </div>
                    <div className="form-group">
                        <label for="contact-message">Message</label>
                        <textarea value={this.state.message.message} 
                        onChange={this.handleChangeFor('message')} id="contact-message" 
                        className="form-control"  placeholder="Enter message" />
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        )
    }
}

export default Contact;