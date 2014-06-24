package com.xx.deal.title.search.model;

import java.util.List;


public class SearchTitleBvo {

    private long numFound;

    private List<TitleSearchResult> titleSearchResults;

    public SearchTitleBvo() {
    }

    public SearchTitleBvo(long numFound, List<TitleSearchResult> titleSearchResults) {
        this.numFound = numFound;
        this.titleSearchResults = titleSearchResults;
    }

    public long getNumFound() {
        return numFound;
    }

    public void setNumFound(long numFound) {
        this.numFound = numFound;
    }

    public List<TitleSearchResult> getTitleSearchResults() {
        return titleSearchResults;
    }

    public void setTitleSearchResults(List<TitleSearchResult> titleSearchResults) {
        this.titleSearchResults = titleSearchResults;
    }
}
