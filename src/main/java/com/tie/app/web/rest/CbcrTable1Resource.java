package com.tie.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tie.app.domain.CbcrTable1;
import com.tie.app.repository.CbcrTable1Repository;
import com.tie.app.repository.search.CbcrTable1SearchRepository;
import com.tie.app.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing CbcrTable1.
 */
@RestController
@RequestMapping("/api")
public class CbcrTable1Resource {

    private final Logger log = LoggerFactory.getLogger(CbcrTable1Resource.class);
        
    @Inject
    private CbcrTable1Repository cbcrTable1Repository;
    
    @Inject
    private CbcrTable1SearchRepository cbcrTable1SearchRepository;
    
    /**
     * POST  /cbcr-table-1-s : Create a new cbcrTable1.
     *
     * @param cbcrTable1 the cbcrTable1 to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cbcrTable1, or with status 400 (Bad Request) if the cbcrTable1 has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/cbcr-table-1-s",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CbcrTable1> createCbcrTable1(@RequestBody CbcrTable1 cbcrTable1) throws URISyntaxException {
        log.debug("REST request to save CbcrTable1 : {}", cbcrTable1);
        if (cbcrTable1.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("cbcrTable1", "idexists", "A new cbcrTable1 cannot already have an ID")).body(null);
        }
        CbcrTable1 result = cbcrTable1Repository.save(cbcrTable1);
        cbcrTable1SearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/cbcr-table-1-s/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("cbcrTable1", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cbcr-table-1-s : Updates an existing cbcrTable1.
     *
     * @param cbcrTable1 the cbcrTable1 to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cbcrTable1,
     * or with status 400 (Bad Request) if the cbcrTable1 is not valid,
     * or with status 500 (Internal Server Error) if the cbcrTable1 couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/cbcr-table-1-s",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CbcrTable1> updateCbcrTable1(@RequestBody CbcrTable1 cbcrTable1) throws URISyntaxException {
        log.debug("REST request to update CbcrTable1 : {}", cbcrTable1);
        if (cbcrTable1.getId() == null) {
            return createCbcrTable1(cbcrTable1);
        }
        CbcrTable1 result = cbcrTable1Repository.save(cbcrTable1);
        cbcrTable1SearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("cbcrTable1", cbcrTable1.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cbcr-table-1-s : get all the cbcrTable1S.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of cbcrTable1S in body
     */
    @RequestMapping(value = "/cbcr-table-1-s",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CbcrTable1> getAllCbcrTable1S(@RequestParam(required = false) String filter) {
        if ("tiedoc-is-null".equals(filter)) {
            log.debug("REST request to get all CbcrTable1s where tieDoc is null");
            return StreamSupport
                .stream(cbcrTable1Repository.findAll().spliterator(), false)
                .filter(cbcrTable1 -> cbcrTable1.getTieDoc() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all CbcrTable1S");
        List<CbcrTable1> cbcrTable1S = cbcrTable1Repository.findAll();
        return cbcrTable1S;
    }

    /**
     * GET  /cbcr-table-1-s/:id : get the "id" cbcrTable1.
     *
     * @param id the id of the cbcrTable1 to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cbcrTable1, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/cbcr-table-1-s/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CbcrTable1> getCbcrTable1(@PathVariable Long id) {
        log.debug("REST request to get CbcrTable1 : {}", id);
        CbcrTable1 cbcrTable1 = cbcrTable1Repository.findOne(id);
        return Optional.ofNullable(cbcrTable1)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /cbcr-table-1-s/:id : delete the "id" cbcrTable1.
     *
     * @param id the id of the cbcrTable1 to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/cbcr-table-1-s/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCbcrTable1(@PathVariable Long id) {
        log.debug("REST request to delete CbcrTable1 : {}", id);
        cbcrTable1Repository.delete(id);
        cbcrTable1SearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("cbcrTable1", id.toString())).build();
    }

    /**
     * SEARCH  /_search/cbcr-table-1-s?query=:query : search for the cbcrTable1 corresponding
     * to the query.
     *
     * @param query the query of the cbcrTable1 search
     * @return the result of the search
     */
    @RequestMapping(value = "/_search/cbcr-table-1-s",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CbcrTable1> searchCbcrTable1S(@RequestParam String query) {
        log.debug("REST request to search CbcrTable1S for query {}", query);
        return StreamSupport
            .stream(cbcrTable1SearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
