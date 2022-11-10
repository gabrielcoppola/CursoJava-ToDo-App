package br.com.pola.todoapp.controller;

import br.com.pola.todoapp.model.TaskModel;
import br.com.pola.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class TaskController {

    private TaskRepository taskRepository;

    private TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String task(Model model) {
        model.addAttribute("tasklist", taskRepository.findAll());
        return "index";
    }

    @GetMapping("/newtask")
    public String newTask(@ModelAttribute("task") TaskModel taskModel) {
        return "save";
    }

    @GetMapping("/{id}")
    public String editTask(@PathVariable("id") long id, Model model) {
        Optional<TaskModel> taskOpt = taskRepository.findById(id);
        if(taskOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalido");
        }
        model.addAttribute("task", taskOpt.get());
        return "save";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") long id) {
        Optional<TaskModel> taskOpt = taskRepository.findById(id);
        if(taskOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalido");
        }
        taskRepository.delete(taskOpt.get());
        return "redirect:/";
    }

    @PostMapping("/save")
    public String saveDescription(@ModelAttribute("task") TaskModel taskModel) {
        taskRepository.save(taskModel);
        return "redirect:/";
    }
}
