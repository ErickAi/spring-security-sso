package org.securityclient.repository;

import org.securityclient.domain.Role;
import org.securityclient.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends AbstractNamedRepository<User> {

    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(String email);

    List<User> findByNameContainingIgnoreCase(String name);

    @Query("SELECT u FROM User u WHERE :role MEMBER OF u.roles AND u.endpoint IS NULL ORDER BY u.name")
    List<User> findAllByRole(Role role);

/*
    @Query("SELECT u FROM User u JOIN FETCH u.team t JOIN FETCH t.coach WHERE :role MEMBER OF u.roles AND u.endpoint IS NULL")
    List<User> findAllByRoleWithTeamAndCoach(Role role);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.team t JOIN FETCH t.coach WHERE t.id=:teamId AND u.endpoint IS NULL")
    List<User> getAllWithCoachByTeamId(int teamId);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.team WHERE u.team.id=:teamId AND u.endpoint IS NULL")
    List<User> getAllByTeamId(int teamId);

    @Query("SELECT u FROM User u JOIN FETCH u.team t JOIN FETCH t.coach c WHERE c.id=:coachId AND u.endpoint IS NULL")
    List<User> getAllWithCoachByCoachId(int coachId);

    @Query("SELECT u FROM User u JOIN FETCH u.team WHERE u.team.coach.id=:coachId AND u.endpoint IS NULL")
    List<User> getAllByCoachId(int coachId);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.team WHERE u.id=:id")
    User getWithTeam(int id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.coachTeams WHERE u.id=:id")
    User getWithCoachTeams(int id);
*/
}