package edu.uestc.peng.gankio.item;

import java.util.ArrayList;

/**
 * Created by Peng on 2016/5/24.
 * response of server
 */
public class ServerResponse {
    private boolean isError;
    private ArrayList<News> results;

    public boolean isError() {
        return isError;
    }

    public ArrayList<News> getResults() {
        return results;
    }

    public ServerResponse(boolean isError, ArrayList<News> results) {
        this.isError = isError;
        this.results = results;
    }
}
