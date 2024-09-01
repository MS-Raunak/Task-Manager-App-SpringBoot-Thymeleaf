package com.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{

}
