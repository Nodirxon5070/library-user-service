package com.company.userservice.repository;

import com.company.userservice.modul.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserIdAndDeletedAtIsNull(Integer userId);

    @Query(
            value = "select *\n" +
                    "from users as u where id = ?1 and deleted_at is null",
            nativeQuery = true
    )
    Optional<User> getUserById(Integer userId);

    @Query(
            value = "select *\n" +
                    "from users where firstname like concat(:val, '%') order by id desc",
            nativeQuery = true)
    List<User> searchUserByFirstname(@Param(value = "val") String value);

    List<User> findAllByDeletedAtIsNull();

    @Query(
            value = "select * from users order by id",
            countQuery = "select count(*) from users",
            nativeQuery = true
    )
    Page<User> findAllByDeletedAtIsNull(Pageable pageable);


    @Query(
            value = "select * from users as u where u.firstname like concat(:val, '%')" +
                    " and deleted_at is null order by u.id",
            countQuery = "select count(*) from users",
            nativeQuery = true
    )
    Page<User> searchAllUserByValue(Pageable pageable, @Param(value = "val") String value);


    @Query(name = "existByEmail")
    boolean existsByEmail(@Param(value = "e") String email);

    @Query(name = "findAllUsers")
    List<User> findAllUserByNameValue(@Param(value = "v") String value);

    @Query(value = "select u from User as u  " +
            "where coalesce(:id, u.userId) = u.userId " +
            "and coalesce(:f, u.firstName) = u.firstName " +
            "and coalesce(:l, u.lastName) = u.lastName  " +
            "and coalesce(:e, u.email) = u.email " +
            "and coalesce(:p, u.password) = u.password " +
            "and coalesce(:bd, u.birthDate) = u.birthDate " +
            "AND coalesce(:ph, u.phone) = u.phone")
    Page<User> findAllUserByParams(
            @Param(value = "id") Integer id,
            @Param(value = "f") String firstname,
            @Param(value = "l") String lastname,
            @Param(value = "e") String email,
            @Param(value = "p") String password,
            @Param(value = "bd") String birthDate,
            @Param(value = "ph") String phoneNumber,
            Pageable pageable);


    List<User> findAllByDeletedAtIsNotNull();
}
