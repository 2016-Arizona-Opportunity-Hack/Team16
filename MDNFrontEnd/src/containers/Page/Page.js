/**
 * Created by Vikranth on 10/1/2016.
 */

import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux';
import Section from 'components/Section/Section';
import styles from './Page.scss';

@connect(
  state => ({
    page: state.content.currentPage,
    isEditing: state.content.isEditing
  })
)
export default class Page extends Component {
  static propTypes = {
    page: PropTypes.object.isRequired,
    isEditing: PropTypes.bool.isRequired
  };

  constructor(props) {
    super(props);
  }

  state = {
    isEditing: false,
    heading: ''
  };

  _typing() {
    const input = this.refs.heading;
    console.log(input.value);
  }

  _editClick() {
    this.setState({
      isEditing: !this.state.isEditing
    });
  }

  renderEditButton() {
    const { isEditing } = this.props;
    const editText = isEditing ? 'Done' : 'Edit';
    return (
      <div className={styles.edit}>
        <button className={ 'btn btn-danger' }
          onClick={ () => this._editClick() }>
          { editText }
        </button>
      </div>
    );
  }

  render() {
    const { sections, header } = this.props.page;
    const { isEditing } = this.state;
    let index = 0;
    return (
      <div className={ styles.page + ' container' }>
        { this.renderEditButton() }
        {
          isEditing ? <h1 className={ 'page-header' }>{ header }</h1>
            : <h1 className={ 'page-header' }>
                <input type="text" value={ header }
                       onChange={ this._typing() }
                       ref={ 'heading' } />
              </h1>
        }
        <div>
          {
            sections.map((section) => {
              index++;
              return <Section section={section} key={ index } />;
            })
          }
        </div>
      </div>
    );
  }
}
