package com.tie.app.repository.search;

import com.tie.app.domain.CbcrEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the CbcrEntity entity.
 */
public interface CbcrEntitySearchRepository extends ElasticsearchRepository<CbcrEntity, Long> {
}
