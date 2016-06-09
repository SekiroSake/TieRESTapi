package com.tie.app.repository.search;

import com.tie.app.domain.CbcrTable2;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the CbcrTable2 entity.
 */
public interface CbcrTable2SearchRepository extends ElasticsearchRepository<CbcrTable2, Long> {
}
