package com.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ms.dto.TaskDto;
import com.ms.service.TaskService;

@Controller
public class TaskController {
	@Autowired
	TaskService taskService;
	
	@GetMapping("/tasks")
	public String getAllTask(Model model) {
		List<TaskDto> allTasks = taskService.getAllTasks();
		model.addAttribute("tasks", allTasks);
		return "tasks/tasksList";
	}
	
	@GetMapping("/createTask")
	public String createTaskPage(Model model) {
		TaskDto taskDto = new TaskDto();
		model.addAttribute("task", taskDto);
		
		return "tasks/createTask";
	}
	
	@PostMapping("/tasks")
	public String createTask(@ModelAttribute TaskDto taskDto) {
		taskService.createTask(taskDto);
		
		return "redirect:/tasks?success";
	}
	
	@GetMapping("/tasks/delete/{id}")
	public String deleteTask(@PathVariable("id") Long id) {
		taskService.deleteTaskById(id);
		return "redirect:/tasks?deleted";
	}
	
	@GetMapping("/tasks/edit/{id}")
	public String editTaskPage(@PathVariable("id") Long id, Model model) {
		TaskDto taskById = taskService.getTaskById(id);
		model.addAttribute("task", taskById);
		return "tasks/editTask";
	}
	
	@PostMapping("/updateTask/tasks/{id}")
	public String editTask(@ModelAttribute TaskDto taskDto, @PathVariable("id") Long id) {
		taskService.updateTask(taskDto,id);
		return "redirect:/tasks?updated";
	}
	
	
}
