import React from 'react';

const AddSuccess = () => {
    return (
        <div className="container">
            <div className="row justify-content-center">
                <div className="checkmark">
                    <img src="https://images.duckduckgo.com/iu/?u=https%3A%2F%2Fpng.icons8.com%2Fwin8%2F1600%2F107C10%2Fcheckmark&f=1" width="200" height="200" className="d-inline-block align-center" />
                </div>
            </div>
            <h3 className="success-message">Your vehicle was successfully edited!</h3>
        </div>
    )
}

export default AddSuccess;