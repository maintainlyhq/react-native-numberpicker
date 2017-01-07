package io.fixd.reactnativenumberpicker;

import javax.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.common.SystemClock;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.UIProp;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.lang.Integer;
import java.lang.String;

public class RNNumberPickerManager extends SimpleViewManager<RNNumberPicker>
        implements RNNumberPicker.OnChangeListener {

    public static final String REACT_CLASS = "RNNumberPicker";

    private RNNumberPicker view;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected RNNumberPicker createViewInstance(ThemedReactContext reactContext) {
        this.view = new RNNumberPicker(reactContext);
        return this.view;
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

    @Override
    protected void addEventEmitters(final ThemedReactContext reactContext, final RNNumberPicker picker) {
        picker.setOnChangeListener(this);
    }

    @Override
    public void onValueChange(int value) {
        WritableMap event = Arguments.createMap();
        event.putInt("value", value);
        ReactContext reactContext = (ReactContext) view.getContext();
        reactContext.getJSModule(RCTEventEmitter.class)
                .receiveEvent(view.getId(), "topChange", event);
    }


}
