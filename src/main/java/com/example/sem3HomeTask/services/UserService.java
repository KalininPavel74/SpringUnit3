package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

   // @Autowired
    private NotificationService notificationService;

    // Внедрение зависимости через конструктор
    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public User createUser(String name, int age, String email) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);

        // Отправляем уведомление о создании нового пользователя
        notificationService.notifyUser(user);

        return user;
    }

    /**
     * Разбор параметров и создание Пользователя
     * @param allParams параметры http запроса
     * @return Пользователь
     */
    public User createUserByParams(Map<String,String> allParams) {
        return createUser(allParams.get("name"), Integer.valueOf(allParams.get("age")), allParams.get("email"));
    }
}
