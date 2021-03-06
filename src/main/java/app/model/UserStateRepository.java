package app.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userStateRepository
// CRUD refers Create, Read, Update, Delete

public interface UserStateRepository extends CrudRepository<UserState, Integer> {
    @Query(value="SELECT * FROM UserState WHERE userId = ?1 AND (deleted IS NULL OR deleted = 0)",nativeQuery=true)
    ArrayList<UserState> findByUserId(Integer userId);

    @Modifying
    @Query(value="UPDATE UserState SET deleted = true WHERE userId = ?1 AND (deleted IS NULL OR deleted = 0)",nativeQuery=true)
    @Transactional
    Integer deleteByUserId(Integer userId);
}
