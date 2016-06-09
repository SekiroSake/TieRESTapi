package com.tie.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tie.app.domain.CbcrEntity;
import com.tie.app.repository.CbcrEntityRepository;
import com.tie.app.repository.search.CbcrEntitySearchRepository;
import com.tie.app.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing CbcrEntity.
 */
@RestController
@RequestMapping("/api")
public class CbcrEntityResource {

    private final Logger log = LoggerFactory.getLogger(CbcrEntityResource.class);
        
    @Inject
    private CbcrEntityRepository cbcrEntityRepository;
    
    @Inject
    private CbcrEntitySearchRepository cbcrEntitySearchRepository;
    
    /**
     * POST  /cbcr-entities : Create a new cbcrEntity.
     *
     * @param cbcrEntity the cbcrEntity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cbcrEntity, or with status 400 (Bad Request) if the cbcrEntity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/cbcr-entities",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CbcrEntity> createCbcrEntity(@Valid @RequestBody CbcrEntity cbcrEntity) throws URISyntaxException {
        log.debug("REST request to save CbcrEntity : {}", cbcrEntity);
        if (cbcrEntity.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("cbcrEntity", "idexists", "A new cbcrEntity cannot already have an ID")).body(null);
        }
        CbcrEntity result = cbcrEntityRepository.save(cbcrEntity);
        cbcrEntitySearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/cbcr-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("cbcrEntity", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cbcr-entities : Updates an existing cbcrEntity.
     *
     * @param cbcrEntity the cbcrEntity to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cbcrEntity,
     * or with status 400 (Bad Request) if the cbcrEntity is not valid,
     * or with status 500 (Internal Server Error) if the cbcrEntity couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/cbcr-entities",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CbcrEntity> updateCbcrEntity(@Valid @RequestBody CbcrEntity cbcrEntity) throws URISyntaxException {
        log.debug("REST request to update CbcrEntity : {}", cbcrEntity);
        if (cbcrEntity.getId() == null) {
            return createCbcrEntity(cbcrEntity);
        }
        CbcrEntity result = cbcrEntityRepository.save(cbcrEntity);
        cbcrEntitySearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("cbcrEntity", cbcrEntity.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cbcr-entities : get all the cbcrEntities.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of cbcrEntities in body
     */
    @RequestMapping(value = "/cbcr-entities",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CbcrEntity> getAllCbcrEntities(@RequestParam(required = false) String filter) {
        if ("tiedoc-is-null".equals(filter)) {
            log.debug("REST request to get all CbcrEntitys where tieDoc is null");
            return StreamSupport
                .stream(cbcrEntityRepository.findAll().spliterator(), false)
                .filter(cbcrEntity -> cbcrEntity.getTieDoc() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all CbcrEntities");
        List<CbcrEntity> cbcrEntities = cbcrEntityRepository.findAll();
        return cbcrEntities;
    }

    /**
     * GET  /cbcr-entities/:id : get the "id" cbcrEntity.
     *
     * @param id the id of the cbcrEntity to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cbcrEntity, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/cbcr-entities/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CbcrEntity> getCbcrEntity(@PathVariable Long id) {
        log.debug("REST request to get CbcrEntity : {}", id);
        CbcrEntity cbcrEntity = cbcrEntityRepository.findOne(id);
        return Optional.ofNullable(cbcrEntity)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /cbcr-entities/:id : delete the "id" cbcrEntity.
     *
     * @param id the id of the cbcrEntity to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/cbcr-entities/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCbcrEntity(@PathVariable Long id) {
        log.debug("REST request to delete CbcrEntity : {}", id);
        cbcrEntityRepository.delete(id);
        cbcrEntitySearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("cbcrEntity", id.toString())).build();
    }

    /**
     * SEARCH  /_search/cbcr-entities?query=:query : search for the cbcrEntity corresponding
     * to the query.
     *
     * @param query the query of the cbcrEntity search
     * @return the result of the search
     */
    @RequestMapping(value = "/_search/cbcr-entities",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CbcrEntity> searchCbcrEntities(@RequestParam String query) {
        log.debug("REST request to search CbcrEntities for query {}", query);
        return StreamSupport
            .stream(cbcrEntitySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
