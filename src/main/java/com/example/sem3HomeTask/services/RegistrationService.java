package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RegistrationService {

    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

    @Autowired
    private DataProcessingService dataProcessingService;

    /**
     *
     * @return возвращает фабрику Пользователь
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * поле для сервиса сущности Пользователь
     * явное связываение с сервисом
     */
    @Autowired
    private UserService userService;
    /**
     *
     * @return возвращает сервис Уведомление
     */
    public NotificationService getNotificationService() {
        return notificationService;
    }
    /**
     * поле для сервиса Уведомление
     * явное связываение с сервисом
     */
    @Autowired
    private NotificationService notificationService;

    /**
     * - создается пользователь из параметров метода
     * - созданный пользователь добавляется в репозиторий
     * - через notificationService выводится сообщение в консоль
     */
    public void processRegistration(User user) {
        // пользователь создается Spring на лету из http POST JSON
        // и предоставляется Контроллеру
        dataProcessingService.getRepository().getUsers().add(user);
        notificationService.notifyUser(user);
    }
    public void processRegistrationByParam(Map<String,String> allParams) {
        User user = userService.createUserByParams(allParams);
        processRegistration(user);
    }

}
