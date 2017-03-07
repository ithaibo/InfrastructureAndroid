package com.andy.infrastructure.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2017/3/7.
 */

public class MeiziDataList extends Bean {
    ArrayList<MeiziData> results;

    public ArrayList<MeiziData> getResults() {
        return results;
    }

    public void setResults(ArrayList<MeiziData> results) {
        this.results = results;
    }
}
