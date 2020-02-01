import React, { Component } from 'react';
import Special from './Special';

class SpecialList extends Component {

    render() {
        const specials = this.props.specials;
        return (
            <div>
                <div className="carousel slide" data-ride="carousel">
                    <div className="carousel-inner">
                    <div className="carousel-item active">
                            {/* <h1></h1> */}
                        </div>
                        {specials.map((special) => {
                            return (
                                <div className="carousel-item">
                                    <Special special={special} />
                                </div>
                            )
                        })}

                    </div>
                </div>


            </div>
        )
    }
}

export default SpecialList;