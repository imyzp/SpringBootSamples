package com.yzp.spring.springbootsamples.es.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzp.spring.springbootsamples.es.model.Content;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ContentService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 写入es
     * @param keywords
     * @return
     * @throws Exception
     */
    public Boolean parseContent(String keywords) throws Exception {
        List<Content> contents = new ArrayList<>();
        contents.add(new Content("1","dd.img","30"));
        contents.add(new Content("2","dd.img","30"));
        contents.add(new Content("3","dd.img","30"));
        contents.add(new Content("4","dd.img","30"));
        contents.add(new Content("5","dd.img","30"));

        // 放入es
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        ObjectMapper mapper = new ObjectMapper();
        for(Content c: contents)
        {
            bulkRequest.add(
                    new IndexRequest("jd_goods")
                    .source(mapper.writeValueAsString(c), XContentType.JSON)
            );
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
    }

    /**
     * 分页查询es
     * @param pageNo
     * @param pageSize
     * @param keyword
     * @return
     * @throws IOException
     */
    public List<Map<String,Object>> searchPage(int pageNo,int pageSize,String keyword) throws IOException {
        if(pageNo == 0)
        {
            pageNo = 1;
        }
        // 条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);
        // 精准匹配
        TermQueryBuilder termQuery = QueryBuilders.termQuery("title", keyword);
        sourceBuilder.query(termQuery);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // 解析结果
        List<Map<String,Object>> list = new ArrayList<>();
        for(SearchHit documentFields: searchResponse.getHits().getHits())
        {
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }
    /**
     * 分页查询es 加高亮
     * @param pageNo
     * @param pageSize
     * @param keyword
     * @return
     * @throws IOException
     */
    public List<Map<String,Object>> searchLightPage(int pageNo,int pageSize,String keyword) throws IOException {
        if(pageNo == 0)
        {
            pageNo = 1;
        }
        // 条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);
        // 精准匹配
        TermQueryBuilder termQuery = QueryBuilders.termQuery("title", keyword);
        sourceBuilder.query(termQuery);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(true);// 多个字段高亮显示
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);


        // 执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // 解析结果
        List<Map<String,Object>> list = new ArrayList<>();
        for(SearchHit hit: searchResponse.getHits().getHits())
        {
            // 解析高亮的字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            // 原来的结果
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            //解析高亮的字段，将原来的字段抵库我们高亮的字段即可
            if(title != null)
            {
                Text[] fragments = title.fragments();
                String n_title = "";
                for(Text text: fragments)
                {
                    n_title+=text;
                }
                sourceAsMap.put("title", n_title);
            }
            list.add(sourceAsMap);
        }
        return list;
    }

    /**
     * 联想字段值
     * @param keyword
     * @return
     * @throws IOException
     */
    public List<String> suggest(String keyword) throws IOException {
        // 条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // 联想
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        CompletionSuggestionBuilder titleSuggestionBuilder = SuggestBuilders.completionSuggestion("title.sugguest")
                .regex(".*" + keyword + ".*").size(10);// 正则包含
//                .prefix(keyword).size(10); // 匹配开头
        CompletionSuggestionBuilder priceSuggestionBuilder = SuggestBuilders.completionSuggestion("price.suggest")
                .regex(".*" + keyword + ".*").size(10);// 正则包含
//                .prefix(keyword).size(10); // 匹配开头
        suggestBuilder.addSuggestion("titleSuggest",titleSuggestionBuilder);
        suggestBuilder.addSuggestion("priceSuggest",priceSuggestionBuilder);
        sourceBuilder.suggest(suggestBuilder);

        // 执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        // 解析结果
        List<String> list = new ArrayList<>();
        Suggest suggest = response.getSuggest();
        suggest.forEach(s -> {
            System.out.println("adf");
            s.getEntries().forEach(o -> {
                o.getOptions().forEach(sug -> {
                    list.add(sug.getText().toString());
                });
            });
        });
        return list;
    }
}
