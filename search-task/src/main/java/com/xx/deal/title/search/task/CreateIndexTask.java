package com.xx.deal.title.search.task;

import com.xx.deal.title.search.service.CreateIndexService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CreateIndexTask {

    Logger log = LoggerFactory.getLogger(CreateIndexTask.class);

    private CreateIndexService createIndexService;

    public void setCreateIndexService(CreateIndexService createIndexService) {
        this.createIndexService = createIndexService;
    }

    public void execFull() {
        log.info("CreateIndexTask execFull is run ...");

        createIndexService.execFull();

        log.info("CreateIndexTask execFull is end ...");
    }

    public void execIncre() {
        log.info("CreateIndexTask execIncre is run ...");

        DateTime today = new DateTime();

        log.info("CreateIndexTask execIncre [" + today + "] data .");
        createIndexService.execIncre(today);

        log.info("CreateIndexTask execIncre is end ...");
    }


}
