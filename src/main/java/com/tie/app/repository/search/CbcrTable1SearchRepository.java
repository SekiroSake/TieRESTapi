package com.tie.app.repository.search;

import com.tie.app.domain.CbcrTable1;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the CbcrTable1 entity.
 */
public interface CbcrTable1SearchRepository extends ElasticsearchRepository<CbcrTable1, Long> {
}
