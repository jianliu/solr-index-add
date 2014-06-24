package com.xx.deal.title.search.service.impl;

import com.xx.deal.title.search.model.DealTitleQuery;
import com.xx.deal.title.search.model.SearchTitleBvo;
import com.xx.deal.title.search.model.TitleSearchResult;
import com.xx.deal.title.search.service.TitleSearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class TitleSearchServiceImpl implements TitleSearchService {

    Logger log = LoggerFactory.getLogger(TitleSearchServiceImpl.class);

    private SolrServer server;

    public void setServer(SolrServer server) {
        this.server = server;
    }

    @Override
    public SearchTitleBvo searchTitle(DealTitleQuery query) {

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("title_iks:" + query.getQueryStr());
        solrQuery.setStart(query.getStart());
        solrQuery.setRows(query.getLimit());
        try {
            QueryResponse response = server.query(solrQuery);
            long numFound = response.getResults().getNumFound();
            List<TitleSearchResult> beans = response.getBeans(TitleSearchResult.class);

            return new SearchTitleBvo(numFound, beans);
        } catch (SolrServerException e) {
            log.error("searchTitle is error,", e);
        }
        return new SearchTitleBvo();
    }


}
