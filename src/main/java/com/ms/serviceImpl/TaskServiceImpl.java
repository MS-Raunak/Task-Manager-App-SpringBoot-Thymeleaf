package com.ms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.dto.TaskDto;
import com.ms.entity.Task;
import com.ms.repository.TaskRepo;
import com.ms.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	TaskRepo taskRepo;
	@Autowired
	ModelMapper mapper; 
	
	
	@Override
	public List<TaskDto> getAllTasks() {
		List<Task> allTasks = taskRepo.findAll();
		List<TaskDto> listOfTasks = allTasks.stream().map(task-> mapper.map(task, TaskDto.class)).toList();
		return listOfTasks;
	}

	@Override
	public void createTask(TaskDto taskDto) {
		Task task = mapper.map(taskDto, Task.class);
		 taskRepo.save(task);
	}

	@Override
	public TaskDto getTaskById(Long id) {
		Optional<Task> optional = taskRepo.findById(id);
		if (optional.isPresent()) {
			Task task = optional.get();
			TaskDto taskDto = mapper.map(task, TaskDto.class);
			return taskDto;
		}
		
		return null;
	}

	@Override
	public void updateTask(TaskDto taskDto, Long id) {
		Optional<Task> optional = taskRepo.findById(id);
		
		Task task = optional.get();
		mapper.map(taskDto, task);
		task.setId(id);
		
		taskRepo.save(task);
	
	}

	@Override
	public void deleteTaskById(Long id) {
		taskRepo.deleteById(id);
		
	}

}
