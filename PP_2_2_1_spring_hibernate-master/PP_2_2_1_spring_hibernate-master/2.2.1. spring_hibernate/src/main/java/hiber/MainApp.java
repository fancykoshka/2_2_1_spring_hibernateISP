package hiber;

import hiber.model.Car;
import hiber.config.AppConfig;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Kitty", "Kit", "cat@gmeuu.com");
      User user2 = new User("Doggy", "Dog", "cat2@gmeuu.com");
      User user3 = new User("Cipa", "Cip", "cat3@gmeuu.com");
      User user4 = new User("Kot", "Kote", "cat4@gmeuu.com");

      Car car1 = new Car("Car1", 01);
      Car car2 = new Car("Car2", 02);
      Car car3 = new Car("Car3", 03);
      Car car4 = new Car("Car4", 04);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      try {
         User userCar = userService.getUserByCar("Car1", 01);
         System.out.println(userCar);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
