package org.securityclient.repository;

import org.securityclient.domain.AbstractTimestampEntry;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.securityclient.util.ValidationUtil.checkUpdate;

@NoRepositoryBean
@Transactional(readOnly = true)
public interface AbstractTimestampRepository<T extends AbstractTimestampEntry> extends AbstractRepository<T> {

    List<T> findAllByEndpointIsNull();

    default void activate(int id, boolean activate) {
        checkUpdate(activate0(id, activate ? null : LocalDateTime.now()));
    }

    @Modifying
    @Transactional
    @Query("UPDATE  #{#entityName} te SET te.endpoint=:ldt WHERE te.id=:id")
    int activate0(int id, LocalDateTime ldt);

    @Transactional
    @Modifying
    default T createWithTimestamp(T entity) {
        entity.setStartpoint(LocalDateTime.now());
        entity.setEndpoint(null);
        return create(entity);
    }

    @Transactional
    @Modifying
    default T updateWithTimestamp(T entity, int id) {
        T dbEntity = get(id);
        entity.setStartpoint(dbEntity.getStartpoint());
        entity.setEndpoint(dbEntity.getEndpoint());
        return update(entity, id);
    }
}
