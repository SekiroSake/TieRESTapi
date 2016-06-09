package com.tie.app.repository.search;

import com.tie.app.domain.TieDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the TieDoc entity.
 */
public interface TieDocSearchRepository extends ElasticsearchRepository<TieDoc, Long> {
}
