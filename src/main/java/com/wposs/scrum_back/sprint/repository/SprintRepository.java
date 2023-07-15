package com.wposs.scrum_back.sprint.repository;

import com.wposs.scrum_back.sprint.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SprintRepository extends JpaRepository<Sprint, UUID> {
    Integer countByAreaIdAndTeamId(UUID idArea,UUID idTeam);

    Optional<Sprint> getByTeamIdAndSprintCount(UUID idTeam, Integer number);
    Optional<Sprint> getBySprintCountIsNull();

    List<Sprint> getByTeamId(UUID idTeam);

    @Query(value = "select ar.area_name, te.team_name, sp.numero_sprint, sc.score_sprint\n" +
            "from wposs.area ar inner join wposs.team te on ar.area_id = te.area_id inner join\n" +
            "wposs.sprint sp on te.team_id = sp.fk_team_id inner join wposs.score_sprint_days\n" +
            "sc on sp.sprint_id = sc.fk_sprint_id  where sp.sprint_id = ?1",nativeQuery = true)
    List<Object[]> getDataSprint(UUID idSprint);
}
