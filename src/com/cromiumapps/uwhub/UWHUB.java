package com.cromiumapps.uwhub;

import android.app.Application;

public class UWHUB extends Application {
    
    private String API_KEY = "1d425dd8265304177d7731007388191f";

    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setAPI_KEY(String inputAPI_KEY) {
        this.API_KEY = inputAPI_KEY;
    }
}
