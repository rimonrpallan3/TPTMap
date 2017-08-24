package com.voyager.user.myapplication.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by User on 8/24/2017.
 */

public class PlaceAutoCompleteAdapter extends ArrayAdapter<PlaceAutoCompleteAdapter.PlaceAutocompleteHolder> {


    public PlaceAutoCompleteAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    /**
     * Holder for Places Geo Data Autocomplete API results.
     */
    class PlaceAutocompleteHolder {

        public CharSequence placeId;
        public CharSequence description;

        PlaceAutocompleteHolder(CharSequence placeId, CharSequence description) {
            this.placeId = placeId;
            this.description = description;
        }

        @Override
        public String toString() {
            return description.toString();
        }


    }
}
