package com.wposs.scrum_back.sprintuserstory.repository;

import com.wposs.scrum_back.sprintemployee.entity.SprintEmployee;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import com.wposs.scrum_back.sprintuserstory.entity.SprintUserstory;
import com.wposs.scrum_back.sprintuserstory.entity.SprintUserstoryPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SprintUserstoryRepository extends JpaRepository<SprintUserstory, Long> {

    Boolean existsById( SprintUserstoryPk id);

    @Query("SELECT se FROM SprintUserstory se WHERE  se.id= :primaryKey")
    Optional<SprintUserstory> findByPrimaryKey1(@Param("primaryKey") SprintUserstoryPk primaryKey);
    @Query(value = " SELECT us.user_story_name, pr.project_name, sp.sub_project_name, sus.points, ar.area_name, spr.numero_sprint \n" +
                   "from wposs.area ar inner join wposs.sprint spr on ar.area_id=spr.fk_area_id\n" +
                   "inner join  wposs.sprint_userstory sus on spr.sprint_id= sus.fk_sprint_id\n" +
                   "inner join wposs.user_story us on sus.fk_user_story_id =us.user_story_id\n" +
                   "inner join wposs.sub_project sp on us.sub_project_id= sp.sub_project_id\n" +
                   "inner join wposs.project pr on sp.project_id= pr.project_id\n" +
                   "where spr.sprint_id = ?1",nativeQuery = true)
    List<Object[]> findAllBySprint(UUID IdSprint);

    void deleteById(SprintUserstoryPk sprintUserstoryPk);
}
