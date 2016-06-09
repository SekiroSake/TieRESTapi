package com.tie.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tie.app.domain.TieDoc;
import com.tie.app.repository.TieDocRepository;
import com.tie.app.repository.search.TieDocSearchRepository;
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
 * REST controller for managing TieDoc.
 */
@RestController
@RequestMapping("/api")
public class TieDocResource {

    private final Logger log = LoggerFactory.getLogger(TieDocResource.class);
        
    @Inject
    private TieDocRepository tieDocRepository;
    
    @Inject
    private TieDocSearchRepository tieDocSearchRepository;
    
    /**
     * POST  /tie-docs : Create a new tieDoc.
     *
     * @param tieDoc the tieDoc to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tieDoc, or with status 400 (Bad Request) if the tieDoc has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/tie-docs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TieDoc> createTieDoc(@Valid @RequestBody TieDoc tieDoc) throws URISyntaxException {
        log.debug("REST request to save TieDoc : {}", tieDoc);
        if (tieDoc.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("tieDoc", "idexists", "A new tieDoc cannot already have an ID")).body(null);
        }
        TieDoc result = tieDocRepository.save(tieDoc);
        tieDocSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/tie-docs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("tieDoc", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tie-docs : Updates an existing tieDoc.
     *
     * @param tieDoc the tieDoc to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tieDoc,
     * or with status 400 (Bad Request) if the tieDoc is not valid,
     * or with status 500 (Internal Server Error) if the tieDoc couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/tie-docs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TieDoc> updateTieDoc(@Valid @RequestBody TieDoc tieDoc) throws URISyntaxException {
        log.debug("REST request to update TieDoc : {}", tieDoc);
        if (tieDoc.getId() == null) {
            return createTieDoc(tieDoc);
        }
        TieDoc result = tieDocRepository.save(tieDoc);
        tieDocSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("tieDoc", tieDoc.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tie-docs : get all the tieDocs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tieDocs in body
     */
    @RequestMapping(value = "/tie-docs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<TieDoc> getAllTieDocs() {
        log.debug("REST request to get all TieDocs");
        List<TieDoc> tieDocs = tieDocRepository.findAll();
        return tieDocs;
    }

    /**
     * GET  /tie-docs/:id : get the "id" tieDoc.
     *
     * @param id the id of the tieDoc to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tieDoc, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/tie-docs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TieDoc> getTieDoc(@PathVariable Long id) {
        log.debug("REST request to get TieDoc : {}", id);
        TieDoc tieDoc = tieDocRepository.findOne(id);
        return Optional.ofNullable(tieDoc)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /tie-docs/:id : delete the "id" tieDoc.
     *
     * @param id the id of the tieDoc to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/tie-docs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTieDoc(@PathVariable Long id) {
        log.debug("REST request to delete TieDoc : {}", id);
        tieDocRepository.delete(id);
        tieDocSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("tieDoc", id.toString())).build();
    }

    /**
     * SEARCH  /_search/tie-docs?query=:query : search for the tieDoc corresponding
     * to the query.
     *
     * @param query the query of the tieDoc search
     * @return the result of the search
     */
    @RequestMapping(value = "/_search/tie-docs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<TieDoc> searchTieDocs(@RequestParam String query) {
        log.debug("REST request to search TieDocs for query {}", query);
        return StreamSupport
            .stream(tieDocSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
