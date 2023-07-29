package com.string.HomeBuild.reposinory;

import com.string.HomeBuild.entity.estimates.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimateJPA extends JpaRepository<Estimate, Integer> {

//
//    @Query(value = "update estimates set name=:name, money=:money where id=:id", nativeQuery = true)
//    void updateEstimate(@Param("id")int id, @Param("name") String name, @Param("money") double money);
}
