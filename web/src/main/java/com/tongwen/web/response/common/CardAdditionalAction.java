package com.tongwen.web.response.common;

public class CardAdditionalAction {
    private String label;
    private String url;
    private boolean isAjax;
    private String refreshTarget;
    private boolean matchSecurity;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isAjax() {
        return isAjax;
    }

    public void setAjax(boolean ajax) {
        isAjax = ajax;
    }

    public String getRefreshTarget() {
        return refreshTarget;
    }

    public void setRefreshTarget(String refreshTarget) {
        this.refreshTarget = refreshTarget;
    }

    public boolean isMatchSecurity() {
        return matchSecurity;
    }

    public void setMatchSecurity(boolean matchSecurity) {
        this.matchSecurity = matchSecurity;
    }
}
