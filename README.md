solr-index-add
==============

solr添加索引


这个例子不是我写的，我只是搭建了一个solr，用这个工程往里面插入了数据

solr的搭建：：

solr+tomcat部署实践：
以前也没有仔细使用过tomcat，今天终于迎来了第一次，激动
引用网页：http://cdc.zhaopin.com/infomation/Industryinfo/contentinfo?articleid=1626169459&Category=160400和http://martin3000.iteye.com/blog/807503，http://wiki.apache.org/solr/SolrLogging终于弄了出来
直接来步骤：
1.下载最新版solr，去官网
2.下周tomcat6，去官网
3.下载IK-Analyzer，去google-code，得zip的那种
4.解压下载下来的包
5.将solr下面的solr4.0.war包考到tomcat的webapps目录下，重命名成solr.war，由于以前没用过tomcat部署web，我还傻气的建了一个solr目录，包war包放在solr下面，其实是直接放在webapps那个文件夹下
6.将solr的webapp考到tomcat的webapps目录下，重命名为solr，4.4.0的solr包下面的example\solr-webapp有一个webapp文件夹，就是它了
7.在tomcat的conf\Catalina\localhost下新建一个solr.xml，用它来指定solr的工作目录，配置地址
<Context docBase="F:\minede\apache-tomcat-6\webapps\solr\solr.war" debug="0" crossContext="true" >

<Environment name="solr/home" type="java.lang.String" value="F:\minede\solr\work\solr" override="true" />

</Context>
docBase就用的webapps下的solr.war，solr/home用的是新建的文件夹
8.新建一个solr的工作文件夹，可以从里面读配置信息，将solr解压后的包中的solr-4.4.0\example\solr的内容复制到这个solr工作文件夹
9.将solr/home的collection1\conf下的schema.xml文件修改一下，添加中文分词器的信息
    <!-- IK Analyzer --> 
	<fieldType name="text_ik" class="solr.TextField">

      <analyzer type="index" isMaxWordLength="false" class="org.wltea.analyzer.lucene.IKAnalyzer"/>

      <analyzer type="query" isMaxWordLength="true" class="org.wltea.analyzer.lucene.IKAnalyzer"/>

    </fieldType>
10.将IK Analyzer的文件IKAnalyzer.cfg.xml,IKAnalyzer2012FF_u1.jar,stopword.dic拷贝到TOMCAT_HOME/webapps/solr/WEB-INF/lib/目录下
11.将solr-4.4.0\example\lib\ext下包拷贝到tomcat的webapps/solr/WEB-INF/lib下面，由于最新的solr里面war里面没有保护slflog的包，因此需要拷过去
12.访问http://localhost:8983/solr/#/，行了，8983是配置的端口，默认的tomcat端口是8080

solr是在tomcat下面启动了，但是其他的东西还需要深入理解和研究，路漫漫其修远兮。。。


http://www.solrcn.com/books/#4-en
http://lucene.apache.org/solr/tutorial.html

使用solrj推送数据创建索引：
maven包依赖：
  <dependency>
            <groupId>org.apache.solr</groupId>
            <artifactId>solr-solrj</artifactId>
            <version>4.8.1</version>
        </dependency>
spring配置solrserver
 <bean class="org.apache.solr.client.solrj.impl.HttpSolrServer" id="solrServer" scope="prototype">
        <constructor-arg name="baseURL" value="#{solrProps['solr.url']}"/>
    </bean>

删除数据：
  server.deleteByQuery("*:*");
            server.commit();

add索引
  server.addBeans(searchResults);
            server.commit();

