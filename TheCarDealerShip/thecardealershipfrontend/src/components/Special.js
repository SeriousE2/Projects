import React, { Component, Fragment } from 'react';



class Special extends Component {

    render() {
        const special = this.props.special;

        return (
            <Fragment>
                {special ? (
                    <div className="special">
                         <h2>{special.title}</h2>
                         <h5>{special.description}</h5>  
                         <hr /> 
                    </div>
                ) : (
                        <div>Error: Could not get specials</div>
                    )}
            </Fragment>

        )
    }
}

export default Special;