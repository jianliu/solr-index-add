package com.xx;

import com.xx.deal.title.search.model.DealTitleQuery;
import com.xx.deal.title.search.model.SearchTitleBvo;
import com.xx.deal.title.search.model.TitleSearchResult;
import com.xx.deal.title.search.service.impl.TitleSearchServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class TitleSearchService {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/ApplicationContext-*.xml");
        TitleSearchServiceImpl bean = context.getBean(TitleSearchServiceImpl.class);
        DealTitleQuery query = new DealTitleQuery();

        //分词匹配
//        query.setQueryStr("卡卡");
        //精确匹配
        query.setQueryStr("\"卡\"");
        query.setLimit(10);
        SearchTitleBvo searchTitleBvo = bean.searchTitle(query);

        long numFound = searchTitleBvo.getNumFound();
        System.out.println("numFound = " + numFound);
        List<TitleSearchResult> searchResults = searchTitleBvo.getTitleSearchResults();
        for (TitleSearchResult searchResult : searchResults) {
            String title = searchResult.getTitle();
            System.out.println(title);
        }
    }
}
