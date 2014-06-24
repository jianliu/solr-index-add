package com.xx.deal.title.search.service;

import com.xx.deal.title.search.model.DealTitleQuery;
import com.xx.deal.title.search.model.SearchTitleBvo;


public interface TitleSearchService {

    public SearchTitleBvo searchTitle(DealTitleQuery query);

}
