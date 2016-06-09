package com.tie.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tie.app.domain.CbcrTable2;
import com.tie.app.repository.CbcrTable2Repository;
import com.tie.app.repository.search.CbcrTable2SearchRepository;
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
 * REST controller for managing CbcrTable2.
 */
@RestController
@RequestMapping("/api")
public class CbcrTable2Resource {

    private final Logger log = LoggerFactory.getLogger(CbcrTable2Resource.class);
        
    @Inject
    private CbcrTable2Repository cbcrTable2Repository;
    
    @Inject
    private CbcrTable2SearchRepository cbcrTable2SearchRepository;
    
    /**
     * POST  /cbcr-table-2-s : Create a new cbcrTable2.
     *
     * @param cbcrTable2 the cbcrTable2 to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cbcrTable2, or with status 400 (Bad Request) if the cbcrTable2 has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/cbcr-table-2-s",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CbcrTable2> createCbcrTable2(@RequestBody CbcrTable2 cbcrTable2) throws URISyntaxException {
        log.debug("REST request to save CbcrTable2 : {}", cbcrTable2);
        if (cbcrTable2.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("cbcrTable2", "idexists", "A new cbcrTable2 cannot already have an ID")).body(null);
        }
        CbcrTable2 result = cbcrTable2Repository.save(cbcrTable2);
        cbcrTable2SearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/cbcr-table-2-s/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("cbcrTable2", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cbcr-table-2-s : Updates an existing cbcrTable2.
     *
     * @param cbcrTable2 the cbcrTable2 to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cbcrTable2,
     * or with status 400 (Bad Request) if the cbcrTable2 is not valid,
     * or with status 500 (Internal Server Error) if the cbcrTable2 couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/cbcr-table-2-s",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CbcrTable2> updateCbcrTable2(@RequestBody CbcrTable2 cbcrTable2) throws URISyntaxException {
        log.debug("REST request to update CbcrTable2 : {}", cbcrTable2);
        if (cbcrTable2.getId() == null) {
            return createCbcrTable2(cbcrTable2);
        }
        CbcrTable2 result = cbcrTable2Repository.save(cbcrTable2);
        cbcrTable2SearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("cbcrTable2", cbcrTable2.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cbcr-table-2-s : get all the cbcrTable2S.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of cbcrTable2S in body
     */
    @RequestMapping(value = "/cbcr-table-2-s",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CbcrTable2> getAllCbcrTable2S(@RequestParam(required = false) String filter) {
        if ("tiedoc-is-null".equals(filter)) {
            log.debug("REST request to get all CbcrTable2s where tieDoc is null");
            return StreamSupport
                .stream(cbcrTable2Repository.findAll().spliterator(), false)
                .filter(cbcrTable2 -> cbcrTable2.getTieDoc() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all CbcrTable2S");
        List<CbcrTable2> cbcrTable2S = cbcrTable2Repository.findAll();
        return cbcrTable2S;
    }

    /**
     * GET  /cbcr-table-2-s/:id : get the "id" cbcrTable2.
     *
     * @param id the id of the cbcrTable2 to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cbcrTable2, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/cbcr-table-2-s/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CbcrTable2> getCbcrTable2(@PathVariable Long id) {
        log.debug("REST request to get CbcrTable2 : {}", id);
        CbcrTable2 cbcrTable2 = cbcrTable2Repository.findOne(id);
        return Optional.ofNullable(cbcrTable2)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /cbcr-table-2-s/:id : delete the "id" cbcrTable2.
     *
     * @param id the id of the cbcrTable2 to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/cbcr-table-2-s/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCbcrTable2(@PathVariable Long id) {
        log.debug("REST request to delete CbcrTable2 : {}", id);
        cbcrTable2Repository.delete(id);
        cbcrTable2SearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("cbcrTable2", id.toString())).build();
    }

    /**
     * SEARCH  /_search/cbcr-table-2-s?query=:query : search for the cbcrTable2 corresponding
     * to the query.
     *
     * @param query the query of the cbcrTable2 search
     * @return the result of the search
     */
    @RequestMapping(value = "/_search/cbcr-table-2-s",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CbcrTable2> searchCbcrTable2S(@RequestParam String query) {
        log.debug("REST request to search CbcrTable2S for query {}", query);
        return StreamSupport
            .stream(cbcrTable2SearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
