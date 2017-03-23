
package qyh.androidprojecthelper.bean;

import java.util.List;

public class GirlData {
    private boolean isError;
    private List<FirstBean> results;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public void setResults(List<FirstBean> results) {
        this.results = results;
    }

    public List<FirstBean> getResults() {
        return results;
    }
}
