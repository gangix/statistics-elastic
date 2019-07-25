package com.atfarm.statisticsjava.repository;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.atfarm.statisticsjava.dto.VegetationStatisticsDTO;

@Component
public class ElasticQueryBuilderRepository {

	@Autowired
	TransportClient client;

	@Value("${spring.data.date.from}")
	private String from;

	@Value("${spring.data.date.to}")
	private String now;

	public VegetationStatisticsDTO getVegetationStatistics() {
		RangeQueryBuilder datedQuery = QueryBuilders.rangeQuery("date").to(now).from(from);

		SearchResponse sr = client.prepareSearch().setQuery(datedQuery)
				.addAggregation(AggregationBuilders.stats("agg").field("vegetation").format("yyyy-MM-dd HH:mm")).get();

		Stats agg = sr.getAggregations().get("agg");
		double min = agg.getMin();
		double max = agg.getMax();
		double avg = agg.getAvg();

		return new VegetationStatisticsDTO(min, max, avg);
	}
}