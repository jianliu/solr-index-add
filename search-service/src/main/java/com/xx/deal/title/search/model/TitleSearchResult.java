package com.xx.deal.title.search.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TitleSearchResult implements RowMapper {

    @Field("taobao_deal_id")
    private String taobaoDealId;

    @Field("shop_id_s")
    private String shopId;

    @Field("taobao_shop_id_s")
    private String taobaoShopId;

    @Field("wang_id_s")
    private String wangId;

    @Field("title_iks")
    private String title;

    public String getTaobaoDealId() {
        return taobaoDealId;
    }

    public void setTaobaoDealId(String taobaoDealId) {
        this.taobaoDealId = taobaoDealId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getTaobaoShopId() {
        return taobaoShopId;
    }

    public void setTaobaoShopId(String taobaoShopId) {
        this.taobaoShopId = taobaoShopId;
    }

    public String getWangId() {
        return wangId;
    }

    public void setWangId(String wangId) {
        this.wangId = wangId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        TitleSearchResult titleSearchResult = new TitleSearchResult();
        titleSearchResult.setShopId(rs.getString("shop_id"));
        titleSearchResult.setTaobaoDealId(rs.getString("taobao_deal_id"));
        String taobaoShopId = rs.getString("taobao_shop_id");
        titleSearchResult.setTaobaoShopId(taobaoShopId == null ? "" : taobaoShopId);
        titleSearchResult.setTitle(rs.getString("deal_name"));
        titleSearchResult.setWangId(rs.getString("wang_id"));
        return titleSearchResult;
    }
}
