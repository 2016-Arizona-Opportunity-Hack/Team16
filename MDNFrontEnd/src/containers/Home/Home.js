import React, { Component } from 'react';
// import { Link } from 'react-router';
// import { CounterButton, GithubButton } from 'components';
// import config from '../../config';
import Helmet from 'react-helmet';

export default class Home extends Component {
  render() {
    const styles = require('./Home.scss');
    // require the logo image both from client and server
    // const logoImage = require('./logo.png');
    return (
      <div className={styles.home}>
        <Helmet title="Home" />
          <div className="container">
            <h1>Welcome</h1>
            <div className="panel panel-default">
              <div className="panel-body">You have all the admin features here</div>
            </div>
        </div>
      </div>
    );
  }
}
