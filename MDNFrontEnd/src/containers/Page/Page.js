/**
 * Created by Vikranth on 10/1/2016.
 */

import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux';
import Section from 'components/Section/Section';
import styles from './Page.scss';
import * as pageActions from 'redux/modules/content';

@connect(
  state => ({
    page: state.content.currentPage,
    isEditing: state.content.isEditing
  }), pageActions
)
export default class Page extends Component {
  static propTypes = {
    page: PropTypes.object.isRequired,
    isEditing: PropTypes.bool.isRequired,
    updatePageHeader: PropTypes.func.isRequired,
    updatePageSection: PropTypes.func.isRequired
  };

  constructor(props) {
    super(props);
    this._typing = this._typing.bind(this);
  }

  state = {
    isEditing: false,
    header: ''
  };

  _typing() {
  }

  _editClick() {
    const { isEditing } = this.state;
    const { page } = this.props;
    if (isEditing) {
      const header = this.refs.header.value;
      if (header && header.trim().length > 0) {
        const newPage = {
          ...page,
          header
        };
        this.props.updatePageHeader(header.trim());
      }
    }
    this.setState({
      isEditing: !isEditing,
      header: this.props.page.header
    });
  }

  _renderEditButton() {
    const { isEditing } = this.state;
    const editText = isEditing ? <i className="fa fa-check"></i> : <i className="fa fa-edit"></i>;
    const className = isEditing ? 'btn-success' : 'btn-primary';
    return (
      <div className={styles.edit}>
        <button className={ `btn ${className}` }
          onClick={ () => this._editClick() }>
          { editText }
        </button>
      </div>
    );
  }

  render() {
    const { sections, header, _id } = this.props.page;
    const { isEditing } = this.state;
    let index = -1;
    return (
      <div className={ styles.page + ' container' }>
        { this._renderEditButton() }
        {
          !isEditing ? <h1 className={ 'page-header' }>{ header }</h1>
            : <h1 className={ 'page-header' }>
                  <input type="text"
                         className={ 'form-control' }
                         onChange={ this._typing }
                         defaultValue={ this.state.header }
                         placeholder={ 'Page heading' }
                         ref={ 'header' } />
              </h1>
        }
        <div>
          {
            sections.map((section) => {
              index++;
              return (
                <Section section={section}
                         key={ index }
                         index={ index }
                         pageId={ _id }
                         updatePageSection={ this.props.updatePageSection } />
              );
            })
          }
        </div>
      </div>
    );
  }
}
