package com.txt.elasticsearch;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.util.List;
import java.util.Map;

import com.txt.elasticsearch.config.ElsaticsearchConfig;
import com.txt.elasticsearch.entities.Article;
import com.txt.elasticsearch.entities.Author;
import com.txt.elasticsearch.repository.ArticleRepository;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This Manual test requires: Elasticsearch instance running on localhost:9200.
 * <p>
 * The following docker command can be used: docker run -d --name es7132 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.13.2
 */
@ContextConfiguration(classes = ElsaticsearchConfig.class)
@SpringBootTest
public class ElasticSearchQueryManualTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RestHighLevelClient client;

    private final Author johnSmith = new Author("John Smith");
    private final Author johnDoe = new Author("John Doe");

    @BeforeEach
    public void before() {
        Article article = new Article("Spring Data Elasticsearch");
        article.setAuthors(asList(johnSmith, johnDoe));
        article.setTags("elasticsearch", "spring data");
        articleRepository.save(article);

        article = new Article("Search engines");
        article.setAuthors(asList(johnDoe));
        article.setTags("search engines", "tutorial");
        articleRepository.save(article);

        article = new Article("Second Article About Elasticsearch");
        article.setAuthors(asList(johnSmith));
        article.setTags("elasticsearch", "spring data");
        articleRepository.save(article);

        article = new Article("Elasticsearch Tutorial");
        article.setAuthors(asList(johnDoe));
        article.setTags("elasticsearch");
        articleRepository.save(article);
    }

    @AfterEach
    public void after() {
        articleRepository.deleteAll();
    }

    @Test
    public void givenFullTitle_whenRunMatchQuery_thenDocIsFound() {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("title", "Search engines").operator(Operator.AND)).build(); //default is -> Operator.OR
        //searching with: "Search" and "engines"

        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));
        Assertions.assertEquals(1, articles.getTotalHits());
    }

    @Test
    public void givenOneTermFromTitle_whenRunMatchQuery_thenDocIsFound() {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("title", "Engines Solutions")).build(); //searching with: "Engines" or "Solutions"

        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        Assertions.assertEquals(1, articles.getTotalHits());
        Assertions.assertEquals("Search engines", articles.getSearchHit(0).getContent().getTitle());
    }

    @Test
    public void givenPartTitle_whenRunMatchQuery_thenDocIsFound() {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("title", "elasticsearch data")).build();

        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        Assertions.assertEquals(3, articles.getTotalHits());
    }

    @Test
    public void givenFullTitle_whenRunMatchQueryOnVerbatimField_thenDocIsFound() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("title.verbatim", "Second Article About Elasticsearch")).build();

        SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        Assertions.assertEquals(1, articles.getTotalHits());

        searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("title.verbatim", "Second Article About")).build();

        articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));
        Assertions.assertEquals(0, articles.getTotalHits());
    }

    @Test
    public void givenNestedObject_whenQueryByAuthorsName_thenFoundArticlesByThatAuthor() {
        final QueryBuilder builder = nestedQuery("authors", boolQuery().must(termQuery("authors.name", "smith")), ScoreMode.None);

        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        Assertions.assertEquals(2, articles.getTotalHits());
    }

    @Test
    public void givenAnalyzedQuery_whenMakeAggregationOnTermCount_thenEachTokenCountsSeparately() throws Exception {
        final TermsAggregationBuilder aggregation = AggregationBuilders.terms("top_tags").field("title");

        final SearchSourceBuilder builder = new SearchSourceBuilder().aggregation(aggregation);
        final SearchRequest searchRequest = new SearchRequest("blog").source(builder);

        final SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        final Map<String, Aggregation> results = response.getAggregations().asMap();
        final ParsedStringTerms topTags = (ParsedStringTerms) results.get("top_tags");

        final List<String> keys = topTags.getBuckets().stream().map(MultiBucketsAggregation.Bucket::getKeyAsString)
                .sorted().collect(toList());
        Assertions.assertEquals(asList("about", "article", "data", "elasticsearch", "engines", "search", "second", "spring", "tutorial"), keys);
    }

    @Test
    public void givenNotAnalyzedQuery_whenMakeAggregationOnTermCount_thenEachTermCountsIndividually() throws Exception {
        final TermsAggregationBuilder aggregation = AggregationBuilders.terms("top_tags").field("tags")
                .order(BucketOrder.count(false));

        final SearchSourceBuilder builder = new SearchSourceBuilder().aggregation(aggregation);
        final SearchRequest searchRequest = new SearchRequest().indices("blog").source(builder);

        final SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        final Map<String, Aggregation> results = response.getAggregations().asMap();
        final ParsedStringTerms topTags = (ParsedStringTerms) results.get("top_tags");

        final List<String> keys = topTags.getBuckets().stream().map(MultiBucketsAggregation.Bucket::getKeyAsString).collect(toList());
        Assertions.assertEquals(asList("elasticsearch", "spring data", "search engines", "tutorial"), keys);
    }

    @Test
    public void givenNotExactPhrase_whenUseSlop_thenQueryMatches() {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchPhraseQuery("title", "spring elasticsearch").slop(1)).build();

        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        Assertions.assertEquals(1, articles.getTotalHits());
    }

    @Test
    public void givenPhraseWithType_whenUseFuzziness_thenQueryMatches() {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("title", "spring date elasticserch").operator(Operator.AND)
                        .fuzziness(Fuzziness.ONE).prefixLength(3))
                .build();

        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        Assertions.assertEquals(1, articles.getTotalHits());
    }

    @Test
    public void givenMultimatchQuery_whenDoSearch_thenAllProvidedFieldsMatch() {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(
                multiMatchQuery("tutorial").field("title").field("tags").type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
                .build();

        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        Assertions.assertEquals(2, articles.getTotalHits());
    }

    @Test
    public void givenBoolQuery_whenQueryByAuthorsName_thenFoundArticlesByThatAuthorAndFilteredTag() {
        final QueryBuilder builder = boolQuery()
                .must(nestedQuery("authors", boolQuery().must(termQuery("authors.name", "doe")), ScoreMode.None))
                .filter(termQuery("tags", "elasticsearch"));

        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        Assertions.assertEquals(2, articles.getTotalHits());
    }
}
