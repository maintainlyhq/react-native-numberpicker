var React = require('react-native');

class NumberPicker extends React.Component {

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
