import React, { Component } from 'react';

class NumberPicker extends Component {

	constructor(props) {
		super(props);
		var warning = require('fbjs/lib/warning');
		warning(false, 'NumberPicker is not available on iOS');
	}

	render() {
		return null;
	}
}

module.exports = NumberPicker;
