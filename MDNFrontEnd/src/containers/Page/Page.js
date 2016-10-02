/**
 * Created by Vikranth on 10/1/2016.
 */

import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux';
import Section from 'components/Section/Section';
import styles from './Page.scss';
import * as pageActions from 'redux/modules/content';
import Ajax from 'simple-ajax';
import config from '../../config';

export const initImageSection = {
  type: 'full',
  header: 'Image header',
  content: {
    type: 'image',
    url: 'http://www.tattooshunter.com/wp-content/uploads/2015/07/head-tattoo-beautiful-new-tiger-sample.jpg'
  }
};

export const initTextSection = {
  type: 'full',
  header: 'Text section',
  content: {
    type: 'text',
    text: 'Sample text'
  }
};

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
    updatePageSection: PropTypes.func.isRequired,
    updatePageId: PropTypes.func.isRequired,
    addPageSection: PropTypes.func.isRequired
  };

  constructor(props) {
    super(props);
    this._typing = this._typing.bind(this);
    this._addPageSection = this._addPageSection.bind(this);
  }

  state = {
    isEditing: false,
    header: ''
  };

  _typing() {
  }

  _sendAjaxRequest(page) {
    const url = page._id
      ? config.server + '/' + config.page.update + '/' + page._id
      : config.server + '/' + config.page.insert;
    const ajax = new Ajax({
      url,
      method: 'POST',
      dataType: 'json',
      data: page,
      processData: true
    });
    ajax.on('error', (error) => {
      // handle error
      console.log(error);
    });
    ajax.on('success', (result) => {
      // update the current page id
      console.log(result);
      const data = JSON.parse(result.target.responseText);
      if (!page._id) {
        this.props.updatePageId(data._id);
      }
    });
    ajax.on('complete', (ev) => {
      // do nothing
      console.log(ev);
    });
    ajax.send();
  }

  _updatePageSection(index, section) {
    const { page } = this.props;
    const sections = [ ...page.sections ];
    sections[index] = { ...section };
    const newPage = { ...page, sections };
    console.log(newPage);
    this._sendAjaxRequest(newPage);
    this.props.updatePageSection(index, section);
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
        console.log('before ajax request');
        this._sendAjaxRequest(newPage);
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

  _addPageSection(type) {
    let section = type === 'text' ? initTextSection : initImageSection;
    const page = this.props.page;
    section = JSON.parse(JSON.stringify(section));
    // append the new page after creating the section
    const newPage = {
      ...page,
      sections: [...page.sections, section]
    };
    this.props.addPageSection(section);
    this._sendAjaxRequest(newPage);
  }

  _renderOtherButtons() {
    return (
      <div>
        <div>
          <button className="btn btn-lg"
                  onClick={ () => this._addPageSection('text') }>Add text section</button>
        </div>
        <div>
          <button className="btn btn-lg"
                  onClick={ () => this._addPageSection('image') }>Add image section</button>
        </div>
      </div>
    );
  }

  render() {
    const { sections, header } = this.props.page;
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
                         pageId={ 'nothing' }
                         updatePageSection={ this._updatePageSection.bind(this) } />
              );
            })
          }
        </div>
        { this._renderOtherButtons() }
      </div>
    );
  }
}
