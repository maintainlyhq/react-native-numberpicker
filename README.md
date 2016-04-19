# react-native-numberpicker

## Installation

`npm install react-native-numberpicker --save`

(Ensure you rely on npm versioning via https://www.npmjs.com/package/react-native-numberpicker - not the github releases.)

For Android:

```
// file: android/settings.gradle
...

include ':NumberPicker', ':app'
project(':NumberPicker').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-numberpicker')
```

```
// file: android/app/build.gradle
...

dependencies {
    ...
    compile project(':NumberPicker')
}
```

```
// file: android/app/source/main/java/com/{projectName}.MainActivity.java
...
import fixd.io.numberpicker.NumberPickerManager;
...
.addPackage(new NumberPicker(this))
...
```

### Usage

```
<NumberPicker
	style={styles.picker}
	height={150}
	values={values}
	selected={selectedIndex}
	onSelect={(value) => {
		console.log('onSelect', value);
	}}
	/>
```