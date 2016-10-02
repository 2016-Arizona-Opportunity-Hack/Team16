/**
 * Created by Vikranth on 10/1/2016.
 */

import React, {Component, PropTypes} from 'react';
import styles from './Section.scss';

export default class Section extends Component {

  static propTypes = {
    section: PropTypes.object.isRequired,
    pageId: PropTypes.string.isRequired,
    index: PropTypes.number.isRequired,
    updatePageSection: PropTypes.func.isRequired,
    deleteSection: PropTypes.func.isRequired
  };

  state = {
    isEditing: false
  };

  _editClick() {
    const { isEditing } = this.state;
    if (isEditing) {
      // update or delete
      const { content } = this.props.section;
      const newContent = { ...content };
      const header = this.refs.header.value;
      const section = {
        ...this.props.section
      };
      console.log(content, header, section);
      switch (content.type) {
        case 'text':
          const text = this.refs.text.value;
          if (
            header &&
            header.trim().length > 0 &&
            text &&
            text.trim().length > 0
          ) {
            section.header = header;
            newContent.text = text;
            section.content = newContent;
            this.props.updatePageSection(this.props.index, section);
          }
          break;
        case 'image':
          const url = this.refs.url.value;
          if (
            header &&
            header.trim().length > 0 &&
            url &&
            url.trim().length > 0
          ) {
            section.header = header;
            newContent.url = url;
            section.content = newContent;
            this.props.updatePageSection(this.props.index, section);
          }
          break;
        default:
          break;
      }
    }
    this.setState({
      isEditing: !isEditing
    });
  }

  _renderEditAndDelete() {
    const { isEditing } = this.state;
    const editText = isEditing
      ? <i className="fa fa-check"></i>
      : <i className="fa fa-edit"></i>;
    const className = isEditing ? 'btn-success' : 'btn-primary';
    return (
      <div className={styles.edit}>
        <button className={ `btn ${className}` }
                onClick={ () => this._editClick() }>
          { editText }
        </button>
        <button className="btn btn-danger"
                onClick={ () => this._deleteSection() }>
          <i className="fa fa-remove"></i>
        </button>
      </div>
    );
  }

  _deleteSection() {
    this.props.deleteSection(this.props.index);
  }

  renderSectionContent() {
    const { content } = this.props.section;
    const { isEditing } = this.state;
    switch (content.type) {
      case 'text':
        if (isEditing) {
          return (
            <div className="form-group">
              <textarea className="form-control"
                        defaultValue={ content.text }
                        ref={ 'text' }/>
            </div>
          );
        }
        return content.text;
      case 'image':
        if (isEditing) {
          return (
            <div className="form-group">
              <input type="text"
                     className="form-control"
                     defaultValue={ content.url }
                     ref={ 'url' } />
            </div>
          );
        }
        return (
          <div className={ styles.image }>
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

  renderSection() {
    const { section } = this.props;
    const { isEditing } = this.state;
    console.log('rendering section');
    switch (section.type) {
      case 'full':
        if (isEditing) {
          return (
            <div className="panel panel-default">
              <div className="panel-body">
                <div>
                  <div className={'form-group'}>
                    <input type="text"
                           defaultValue={ section.header }
                           className="form-control"
                           ref={ 'header' }/>
                  </div>
                  { this.renderSectionContent() }
                </div>
              </div>
            </div>
          );
        }
        return (
          <div className={ 'panel panel-default' }>
            <div className={ 'panel-heading' }>
              { section.header}
            </div>
            <div className={ 'panel-body' }>
              { this.renderSectionContent() }
            </div>
          </div>
        );
      default:
        // do nothing
        break;
    }
    return null;
  }
  render() {
    return (
      <div className={ styles.section }>
        { this._renderEditAndDelete() }
        { this.renderSection() }
      </div>
    );
  }
}
