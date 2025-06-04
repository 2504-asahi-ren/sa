package com.example.sa.repository;

import com.example.sa.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository  extends JpaRepository<Task, Integer> {
    public List<Task> findAllByOrderByLimitDateAsc();

    @Modifying
    @Query(value = "UPDATE tasks SET status = :status, updated_date = CURRENT_TIMESTAMP WHERE id = :id", nativeQuery = true)
    void updateStatusById(@Param("id") Integer id, @Param("status") Integer status);


}

