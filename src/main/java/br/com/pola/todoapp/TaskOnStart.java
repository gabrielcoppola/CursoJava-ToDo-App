package br.com.pola.todoapp;

import br.com.pola.todoapp.model.TaskModel;
import br.com.pola.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TaskOnStart implements CommandLineRunner {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        TaskModel task1 = new TaskModel();
        task1.setDescription("Working on a project");

        taskRepository.save(task1);
    }
}
