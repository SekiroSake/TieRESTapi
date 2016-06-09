package com.tie.app.repository;

import com.tie.app.domain.CbcrEntity;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CbcrEntity entity.
 */
@SuppressWarnings("unused")
public interface CbcrEntityRepository extends JpaRepository<CbcrEntity,Long> {

}
