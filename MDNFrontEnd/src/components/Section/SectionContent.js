/**
 * Created by Vikranth on 10/1/2016.
 */

import React, {Component, PropTypes} from 'react';

export default class SectionContent extends Component {

  static propTypes = {
    content: PropTypes.object.isRequired
  };

  render() {
    const { content } = this.props;
    switch (content.type) {
      case 'text':
        return (
          <div className={ 'panel-body' }>
            { content.text }
          </div>
        );
      case 'image':
        return (
          <div className={ 'panel-body' } style={ {'marginLeft': 'auto', 'marginRight': 'auto', 'textAlign': 'center'} }>
            <img src={ content.url }
                 alt=""
                 className={ 'img-thumbnail' } />
          </div>
        );
      default:
        break;
    }
    return null;
  }
}
