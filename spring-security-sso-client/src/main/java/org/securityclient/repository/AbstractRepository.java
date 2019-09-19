package org.securityclient.repository;

import org.securityclient.domain.AbstractBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.securityclient.util.ValidationUtil.*;

@NoRepositoryBean
@Transactional(readOnly = true)
public interface AbstractRepository<E extends AbstractBaseEntity> extends JpaRepository<E, Integer> {
    default E get(int id) {
        return checkFoundOptional(findById(id), id);
    }

    @Override
    Optional<E> findById(Integer id);

    @Override
    List<E> findAll();

    @Transactional
    @Modifying
    default E create(E entity) {
        checkNew(entity);
        return save(entity);
    }

    @Transactional
    @Modifying
    default E update(E entity, int id) {
        assureIdConsistent(entity, id);
        return save(entity);
    }
}