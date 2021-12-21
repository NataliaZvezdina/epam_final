package by.zvezdina.bodyartsalon.main;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.UserDao;
import by.zvezdina.bodyartsalon.model.dao.impl.UserDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.User;
import by.zvezdina.bodyartsalon.model.service.UserService;
import by.zvezdina.bodyartsalon.model.service.impl.UserServiceImpl;
import by.zvezdina.bodyartsalon.model.util.MailSender;

public class Demo {
    public static void main(String[] args) {

//        UserDao userDao = UserDaoImpl.getInstance();
//        try {
//            User foundUser = userDao.findByLogin("leo_leshij");
//            System.out.println("found - " + foundUser);
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }

        MailSender mailSender = new MailSender();
        mailSender.send("natalja.zvezdina@yandex.by", "Fucking java mail");
    }
}
