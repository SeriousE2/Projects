import React, { Component } from 'react';
import Special from './Special';

class SpecialList extends Component {

    render() {
        const specials = this.props.specials;
        return (
            <div>
                {specials.map((special) => {
                    return (
                        <Special special={special} />
                    )
                })}
            </div>

        )
    }
}

export default SpecialList;