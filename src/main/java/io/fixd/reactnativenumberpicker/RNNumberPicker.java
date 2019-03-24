package io.fixd.reactnativenumberpicker;

import javax.annotation.Nullable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class RNNumberPicker extends NumberPicker {

    private boolean mSuppressNextEvent;
    private @Nullable Integer mStagedSelection;

    public RNNumberPicker(Context context) {
        super(context);
        this.setOnChangeListener();
    }

    public RNNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnChangeListener();
    }

    public RNNumberPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOnChangeListener();
    }

    public void setOnChangeListener() {
        setOnValueChangedListener(
            new OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    if (!mSuppressNextEvent) {
                        WritableMap event = Arguments.createMap();
                        event.putInt("value", newVal);
                        event.putInt("oldVal", oldVal);
                        ReactContext reactContext = (ReactContext)getContext();
                        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                                getId(),
                                "topChange",
                                event);
                    }
                    mSuppressNextEvent = false;
                }
            }
        );
    }

    /**
     * Will cache "selection" value locally and set it only once {@link #updateStagedSelection} is
     * called
     */
    public void setStagedSelection(int selection) {
        mStagedSelection = selection;
    }

    public void updateStagedSelection() {
        if (mStagedSelection != null) {
            setValueWithSuppressEvent(mStagedSelection);
            mStagedSelection = null;
        }
    }

    /**
     * Set the selection while suppressing the follow-up event.
     * This is used so we don't get an event when changing the selection ourselves.
     *
     * @param value
     */
    private void setValueWithSuppressEvent(int value) {
        if (value != getValue()) {
            mSuppressNextEvent = true;
            setValue(value);
        }
    }

}
