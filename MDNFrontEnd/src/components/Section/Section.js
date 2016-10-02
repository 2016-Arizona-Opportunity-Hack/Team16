/**
 * Created by Vikranth on 10/1/2016.
 */

import React, {Component, PropTypes} from 'react';
import SectionContent from './SectionContent';

export default class Section extends Component {

  static propTypes = {
    section: PropTypes.object.isRequired
  };

  renderSection() {
    const { section } = this.props;
    console.log('rendering section');
    switch (section.type) {
      case 'full':
        return (
          <div className={ 'panel panel-default' }>
            <div className={ 'panel-heading' }>
              { section.header + ' hello'}
            </div>
            <SectionContent content={section.content} />
          </div>
        );
      default:
        // do nothing
        break;
    }
    return null;
  }

  render() {
    return this.renderSection();
  }
}
