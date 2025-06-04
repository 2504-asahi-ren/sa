package com.example.sa.repository;

import com.example.sa.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface TaskRepository  extends JpaRepository<Task, Integer> {
    public List<Task> findByLimitDateBetweenAndContentContainingAndStatusOrderByLimitDateAsc(Date start, Date end, String content, int status);
    public List<Task> findByLimitDateBetweenAndContentContainingOrderByLimitDateAsc(Date start, Date end, String content);
    public List<Task> findByLimitDateBetweenAndStatusOrderByLimitDateAsc(Date start, Date end, int status);
    public List<Task> findByLimitDateBetweenOrderByLimitDateAsc(Date start, Date end);
    @Modifying
    @Query(value = "UPDATE tasks SET status = :status, updated_date = CURRENT_TIMESTAMP WHERE id = :id", nativeQuery = true)
    void updateStatusById(@Param("id") Integer id, @Param("status") Integer status);


}

