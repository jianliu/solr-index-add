package com.xx.deal.title.search.service.impl;

import com.xx.deal.title.search.model.TitleSearchResult;
import com.xx.deal.title.search.service.CreateIndexService;
import org.apache.solr.client.solrj.SolrServer;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;


public class CreateIndexServiceImpl implements CreateIndexService {

    Logger log = LoggerFactory.getLogger(CreateIndexServiceImpl.class);

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private SolrServer server;

    public void setServer(SolrServer server) {
        this.server = server;
    }

    private int pageSize = 1000;

    public void execFull() {

        delAllIndexs();

        int count = countDeals();
        Integer pageNum = getPageNum(count);

        log.info(" create full indexs pagenum :[" + pageNum + "]");
        for (int pageNo = 0; pageNo < pageNum.intValue(); pageNo++) {
            log.info(" create full indexs execute :[" + pageNo + "/" + pageNum + "]");
            List<TitleSearchResult> searchResults = findDeals(pageNo);
            addIndex(searchResults);
        }

    }

    private void delAllIndexs() {
        try {
            server.deleteByQuery("*:*");
            server.commit();
        } catch (Exception e) {
            log.error("exec full del index is error ", e);
        }
    }

    public void execIncre(DateTime dateTime) {
        int count = countDeals(dateTime);
        Integer pageNum = getPageNum(count);
        log.info(" create incre indexs pagenum :[" + pageNum + "]");

        for (int pageNo = 0; pageNo < pageNum.intValue(); pageNo++) {

            log.info(" create full indexs execute :[" + pageNo + "/" + pageNum + "]");

            List<TitleSearchResult> searchResults = findDeals(pageNo, dateTime);
            delIndexWithIds(searchResults);

            addIndex(searchResults);
        }
    }

    private void addIndex(List<TitleSearchResult> searchResults) {
        try {
            server.addBeans(searchResults);
            server.commit();
        } catch (Exception e) {
            log.error("create index is error", e);
        }
    }

    private void delIndexWithIds(List<TitleSearchResult> searchResults) {
        List<String> delIds = new ArrayList<String>();
        for (TitleSearchResult searchResult : searchResults) {
            String taobaoDealId = searchResult.getTaobaoDealId();
            delIds.add(taobaoDealId);
        }
        try {
            server.deleteById(delIds);
            server.commit();
        } catch (Exception e) {
            log.error("delIndexWithIds is error ,", e);
        }

    }

    private Integer getPageNum(int count) {
        return ((Double) Math.ceil(count * 1.0 / pageSize)).intValue();
    }


    private List<TitleSearchResult> findDeals(int pageNo) {
        return this.findDeals(pageNo, null);
    }

    private List<TitleSearchResult> findDeals(int pageNo, DateTime dateTime) {

        String sql = tableSql();
        if (dateTime != null) {
            sql = addWhereConditions(dateTime, sql);
        }
        sql += " limit ?,?";

        return jdbcTemplate.query(sql, new Object[]{pageNo * pageSize, pageSize}, new TitleSearchResult());
    }

    private int countDeals() {
        return this.countDeals(null);
    }

    private int countDeals(DateTime dateTime) {
        String sql = "select count(1) from deal as d inner join shop as s on d.`shop_id` = s.`shop_id`";
        if (dateTime != null) {
            sql = addWhereConditions(dateTime, sql);
        }
        return jdbcTemplate.queryForInt(sql);
    }

    private String addWhereConditions(DateTime dateTime, String sql) {
        String startDate = dateTime.minusDays(1).toString("yyyy-MM-dd");
        String endDate = dateTime.toString("yyyy-MM-dd");
        sql += " where d.update_time >= '" + startDate
                + "' and d.update_time < '" + endDate + "' ";
        return sql;
    }

    private String tableSql() {
        return "select d.`deal_name`,d.`taobao_deal_id` ,d.`shop_id`,s.`taobao_shop_id`,s.`wang_id` " +
                "from deal as d inner join shop as s on d.`shop_id` = s.`shop_id`";
    }
}
