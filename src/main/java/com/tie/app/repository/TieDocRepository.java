package com.tie.app.repository;

import com.tie.app.domain.TieDoc;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TieDoc entity.
 */
@SuppressWarnings("unused")
public interface TieDocRepository extends JpaRepository<TieDoc,Long> {

}
