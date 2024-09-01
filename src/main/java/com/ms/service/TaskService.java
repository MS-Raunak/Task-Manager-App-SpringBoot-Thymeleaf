package com.ms.service;

import java.util.List;

import com.ms.dto.TaskDto;

public interface TaskService {
	List<TaskDto> getAllTasks();
	void createTask(TaskDto taskDto);
	TaskDto getTaskById(Long id);
	void updateTask(TaskDto taskDto, Long id);
	void deleteTaskById(Long id);
}
