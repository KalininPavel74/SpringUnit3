package com.example.sem3HomeTask.controllers;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.RegistrationService;
import org.springframework.aot.generate.InMemoryGeneratedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")//localhost:8080/user
public class UserController {


    @Autowired
    private RegistrationService service;

    @GetMapping
    public List<User> userList() { return service.getDataProcessingService().getRepository().getUsers(); }

    @PostMapping("/body")  // { "name":"Artur", "age":23, "email":"exam1@yandex.ru" }
    public String userAddFromBody(@RequestBody User user)
    {
        //service.getDataProcessingService().getRepository().getUsers().add(user);
        // добавление через новый метод
        service.processRegistration(user);
        return "User added from body!";
    }

    /**
     * Добавление Пользователя с помощью параметров в http запросе
     * @param allParams Map с параметрами запроса
     * @return текстовое сообщение
     */
    @PostMapping("/param") // http://localhost:8080/user/param?name=User1&age=23&email=User1@yandex.ru
    public String userAddFromParam(@RequestParam Map<String,String> allParams)
    {
        service.getNotificationService().sendNotification(allParams.toString());
        // добавление через новый метод
        service.processRegistrationByParam(allParams);
        return "User added from body!";
    }

}
