package io.fixd.reactnativenumberpicker;

import javax.annotation.Nullable;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.ReadableArray;

import java.lang.Integer;
import java.lang.String;
import java.util.Map;

public class RNNumberPickerManager extends SimpleViewManager<RNNumberPicker> {

    public static final String REACT_CLASS = "RNNumberPicker";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected RNNumberPicker createViewInstance(ThemedReactContext reactContext) {
        return new RNNumberPicker(reactContext);
    }

    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
            .put(
                "onChange",
                MapBuilder.of(
                        "phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onChange")))
            .build();
    }

    @ReactProp(name = "values")
    public void setValues(RNNumberPicker view, @Nullable  ReadableArray items) {
        String[] displayValues = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            displayValues[i] = (String) items.getString(i);
        }

        view.setMinValue(0);
        view.setMaxValue(displayValues.length - 1);
        view.setDisplayedValues(displayValues);
    }

    @ReactProp(name = "selected")
    public void setValue(RNNumberPicker view, Integer selected) {
        view.setValue(selected);
    }
}
