package com.example.IMS_BE.repository;

import com.example.IMS_BE.entity.Issue;
import com.example.IMS_BE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long> {
    List<Issue> findAllByAssignee(User user);

    @Query(value = "SELECT i.class_id, COUNT(i.class_id) * 100 / (SELECT COUNT(*) FROM issue WHERE assignee_id = :assigneeId) AS num FROM issue i WHERE i.assignee_id = :assigneeId GROUP BY i.class_id", nativeQuery = true)
    List<Object[]> getClassStatisticsByAssignee(@Param("assigneeId") Long assigneeId);
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.assignee.id = :assigneeId")
    Long countIssuesByAssigneeId(@Param("assigneeId") Long assigneeId);
    @Query(value = "SELECT i.milestone_id, COUNT(i.milestone_id)  AS num FROM Issue i WHERE i.assignee_id = :assigneeId AND i.project_id = :projectId GROUP BY i.milestone_id",nativeQuery = true)
    List<Object[]> countMilestonesPercentage(@Param("assigneeId") Long assigneeId, @Param("projectId") Long projectId);
    @Query("SELECT i.issueSetting.id, COUNT(i.issueSetting.id) AS num FROM Issue i WHERE i.assignee.id = :assigneeId AND i.project.id = :projectId GROUP BY i.issueSetting.id")
    List<Object[]> countByWorkProcessId(@Param("assigneeId") Long assigneeId, @Param("projectId") Long projectId);
    @Query("SELECT i.status.id, COUNT(i.status.id) AS num FROM Issue i WHERE i.assignee.id = :assigneeId AND i.project.id = :projectId GROUP BY i.status.id")
    List<Object[]> countByStatusId(@Param("assigneeId") Long assigneeId, @Param("projectId") Long projectId);
    @Query("SELECT i.type.id, COUNT(i.type.id) AS num FROM Issue i WHERE i.assignee.id = :assigneeId AND i.project.id = :projectId GROUP BY i.type.id")
    List<Object[]> countByTypeId(@Param("assigneeId") Long assigneeId, @Param("projectId") Long projectId);
    @Query("SELECT i FROM Issue i WHERE i.assigner.id = :assignerId")
    List<Issue> findByAssignerId(@Param("assignerId") Long assignerId);
    @Query("SELECT i FROM Issue i WHERE i.assignee.id = :assigneeId")
    List<Issue> findByAssigneeId(@Param("assigneeId") Long assigneeId);
}
