package com.xx.deal.title.search;

import com.xx.deal.title.search.task.CreateIndexTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TaskRun {

    static Logger log = LoggerFactory.getLogger(TaskRun.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            log.error("CreateIndexTask is error，args length is zero. please input full or incre");
            return;
        } else if (args.length == 1) {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/ApplicationContext-*.xml");
            String indexType = args[0];
            if (indexType.toLowerCase().equals("full")) {

                CreateIndexTask task = context.getBean(CreateIndexTask.class);
                task.execFull();

            } else if (indexType.toLowerCase().equals("incre")) {

                CreateIndexTask task = context.getBean(CreateIndexTask.class);
                task.execIncre();

            } else {
                log.error("please input full or incre.");
            }
        }


    }

}
