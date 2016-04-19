var React = require('react-native');
var { requireNativeComponent, PropTypes, View} = React;

var REF_PICKER = 'numberpicker';

class NumberPicker extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			selectedIndex: this.props.selectedIndex,
			values: this.props.values
		};
		this._onChange = this._onChange.bind(this);
	}

	componentWillReceiveProps(props) {
		this.state.values = props.values;
	}

	_onChange(event) {

		if (this.props.onSelect)
			this.props.onSelect(event.nativeEvent.value);

	}

	setValues(values) {
		this.setState({values: values})
	}

	setSelectedIndex(index) {
		this.setState({selectedIndex: index});
	}

	render() {
		var { values, style, ...otherProps } = this.props;

		return (
			<NativeNumberPicker
				ref={REF_PICKER}
				selected={this.state.selectedIndex}
				values={this.state.values}
				onChange={this._onChange}
				style={[{height:this.props.height}, style && style]}
				{...otherProps}
				/>
		);
	}
}

NumberPicker.defaultProps  = {
	selectedIndex: 0,
	height: 100,
};

NumberPicker.propTypes = {
	...View.propTypes,
	height: PropTypes.number,
	selectedIndex: PropTypes.number,
	values: PropTypes.arrayOf(PropTypes.string).isRequired,
	onSelect: PropTypes.func,
};

var NativeNumberPicker = requireNativeComponent('RNNumberPicker', NumberPicker, {
	nativeOnly: {
		onChange: true,
		selected: true,
	}
});

module.exports = NumberPicker;
