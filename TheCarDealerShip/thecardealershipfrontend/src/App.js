import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import axios from 'axios';
// import { Navbar, Button, Nav, NavItem, Input } from 'react-bootstrap';


// Page components
import Home from './pages/AllUsers/Home';
import NewInventory from './pages/AllUsers/NewInventory';
import UsedInventory from './pages/AllUsers/UsedInventory';
import Specials from './pages/AllUsers/Specials';
import Contact from './pages/AllUsers/Contact';
import VehicleDetails from './pages/AllUsers/VehicleDetails';
import PurchaseVehicle from './pages/Sales/PurchaseVehicle';
import SalesSearch from './pages/Sales/SalesSearch';
import AdminSearch from './pages/Admin/AdminVehicleSearch';
import EditVehicle from './pages/Admin/EditVehicle';
import AddVehicle from './pages/Admin/AddVehicle';
import EditSuccess from './pages/Admin/EditSuccess';
import AddSuccess from './pages/Admin/AddSuccess';


class App extends Component {

  render() {
    return (
      <Router>
        <div className="App">
          <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <a className="navbar-brand" href="/">
            <img src="" width="50" height="50" className="d-inline-block align-center" alt="" />Cars</a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav">
                <li className="nav-item">
                  <Link className="nav-link" to='/'>Home <span className="sr-only">(current)</span></Link>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Browse Vehicles</a>
                  <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <Link to='/inventory/new' className="dropdown-item">New Vehicles</Link>
                    <Link to='/inventory/used' className="dropdown-item">Used Vehicles</Link>
                  </div>
                </li>
                <li className="nav-item">
                  <Link to='/specials' className="nav-link">Specials</Link>
                </li>
                <li className="nav-item">
                  <Link to='/contact/0' className="nav-link">Contact</Link>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Sales Team</a>
                  <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <Link to='/sales/index' className="dropdown-item">Search Available Vehicles</Link>
                  </div>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Admin</a>
                  <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <Link to='/admin/index' className="dropdown-item">Search Available Vehicles</Link>
                    <Link to='/admin/addVehicle' className="dropdown-item">Add Vehicle</Link>
                  </div>
                </li>
              </ul>
            </div>
          </nav>

          <Switch>
            <Route exact path='/' component={Home} />
            <Route path='/inventory/new' component={NewInventory} />
            <Route path='/inventory/used' component={UsedInventory} />
            <Route path='/specials' component={Specials} />
            <Route path='/contact/:vin' component={Contact} />
            <Route path='/inventory/details/:vehicleId'
              component={VehicleDetails} />
            <Route path='/sales/index' component={SalesSearch} />
            <Route path='/sales/purchase/:vehicleId' component={PurchaseVehicle} />
            <Route path='/admin/index' component={AdminSearch} />
            {<Route path='/admin/editVehicle/:vehicleId' component={EditVehicle} />}
            <Route path='/admin/addVehicle' component={AddVehicle} />
            <Route path='/editSuccess' component={EditSuccess} />
            <Route path='/addSuccess' component={AddSuccess} />
          </Switch>

        </div>
      </Router>
    );
  }
}

export default App;
