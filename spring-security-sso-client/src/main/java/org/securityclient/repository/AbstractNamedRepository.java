package org.securityclient.repository;

import org.securityclient.domain.AbstractNamedEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
@Transactional(readOnly = true)
public interface AbstractNamedRepository<NE extends AbstractNamedEntity> extends AbstractTimestampRepository<NE> {
    Optional<NE> findByName(@Param("name") String name);

    //    https://stackoverflow.com/questions/25362540/like-query-in-spring-jparepository
    List<NE> findByNameContainingIgnoreCase(@Param("name") String name);
}