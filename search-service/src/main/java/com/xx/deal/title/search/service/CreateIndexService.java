package com.xx.deal.title.search.service;

import org.joda.time.DateTime;


public interface CreateIndexService {

    public void execFull();

    public void execIncre(DateTime dateTime);

}
