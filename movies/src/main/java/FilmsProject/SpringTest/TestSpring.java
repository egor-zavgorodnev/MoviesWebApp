package FilmsProject.SpringTest;

import FilmsProject.BusinessLayer.AdminOperations;
import FilmsProject.BusinessLayer.FilmOperations;
import FilmsProject.BusinessLayer.UserOperations;
import FilmsProject.Interfaces.AdminService;
import FilmsProject.Interfaces.UserService;
import FilmsProject.Model.Admin;
import FilmsProject.Model.Film;
import FilmsProject.Model.Review;
import FilmsProject.Model.User;
import FilmsProject.View.ConsoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestSpring {

    @Autowired
    Admin admin;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        TestBean testBean = context.getBean("someBean",TestBean.class);
        testBean.info();

       FilmOperations filmOperations = context.getBean("filmOperations",FilmOperations.class);

        UserService userService = context.getBean("userOperations", UserOperations.class);
        User user = userService.signIn("egorka99","qwerty123");

        AdminService adminService = context.getBean("adminOperations", AdminService.class);
        Admin admin = context.getBean("admin", Admin.class);

        userService.updateReview(user,"77164",316L, "bla bla",4.6);

          //  userService.writeReview(user,"77164", "bla bla bla", 8.5);

        List<Review> reviewList = filmOperations.getReviews("77164");

        for (Review review : reviewList) {
            System.out.println(review);
            System.out.println(review.getReviewText());
        }

    }
}