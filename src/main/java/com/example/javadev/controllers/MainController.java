package com.example.javadev.controllers;

import com.example.javadev.model.Lecture;
import com.example.javadev.model.User;
import com.example.javadev.repository.LectureRepository;
import com.example.javadev.service.Service;
import com.example.javadev.service.ServiceImpl;
import com.example.javadev.repository.UserRepository;
import com.example.javadev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping(path = "/home")
public class MainController {

    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Service service;
    @Autowired
    private UserService userService;


    @GetMapping(value = "/mylogin")
    public String getLoginPage(Model model) {
        return "loginpage";
    }

    @GetMapping(value = "/page")
    public String page(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        return "page";
    }

    @GetMapping(value = "/addlectures")
    public String getAddLectures(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("isadiingnewlecturespossible", service.isNumberOfLecturesMoreThan8());
        return "add_lectures";
    }

    @PostMapping(value = "/addlectures")
    public ModelAndView createLecture(
            @RequestParam("lecture_topic") String lecture_topic,
            @RequestParam("lecture_place") String lecture_place,
            @RequestParam("lecture_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date lecture_date) {
        return new ModelAndView("redirect:/home/addlectures");
    }

    @GetMapping(value = "/mylectures")
    public String getMyLectures(Model model, HttpServletRequest request) {
        model.addAttribute("lectures", lectureRepository.findAll());
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("user_id", userRepository.findStudentsIdByEmail(request.getRemoteUser()));
        model.addAttribute("numberOfLecturesAttendedByStudent",
                service.getNumberOfLecturesAttendedByStudent(userRepository.findStudentsIdByEmail(request.getRemoteUser())));
        model.addAttribute("numberOfAllLectures", service.getNumberOfAllLectures());
        return "my_lectures";
    }

    @PostMapping(value = "/lectureattended")
    @Transactional
    public ModelAndView lectureAttended(@RequestParam("user_id") int user_id,
                                        @RequestParam("lecture_id") int lecture_id) {

        User user = userRepository.findByUserId(user_id);
        Lecture lecture = lectureRepository.findOne(lecture_id);

        user.getLectures().add(lecture);
        lecture.getUsers().add(user);

        userRepository.save(user);

        return new ModelAndView("redirect:/home/mylectures");
    }

    @GetMapping(value = "/attendancelist")
    public String getAttendanceList(Model model, HttpServletRequest request) {
        model.addAttribute("lectures", service.createListOfLecturesSortedByDate());
        model.addAttribute("numberoflectures", lectureRepository.count());
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("attendancelist", service.createAttendanceList());
        model.addAttribute("listnumberofstudentspresentonlecture", service.createListNumberOfStudentsPresentOnLecture());
        model.addAttribute("numberofstudents", userRepository.findOnlyStudentIds().size());

        return "attendance_list";
    }

    @GetMapping(value = "/studentslist")
    public String getStudentsList(Model model, HttpServletRequest request) {
        model.addAttribute("students", service.createStudentsList());
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("numberOfStudents", service.getNumberOfStudents());
        return "students_list";
    }


    @PostMapping(value = "/students/{id}")
    public String deleteStudent (@PathVariable int id){
        userRepository.deleteUserByUserId(id);
        return "redirect:/home/studentslist";
    }

//
//    @RequestMapping(value = "/registration", method = RequestMethod.GET)
//    public ModelAndView registration() {
//        ModelAndView modelAndView = new ModelAndView();
//        User user = new User();
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("registration");
//        return modelAndView;
//    }

//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
//        ModelAndView modelAndView = new ModelAndView();
//        User userExists = userService.findUserByEmail(user.getEmail());
//        if (userExists != null) {
//            bindingResult
//                    .rejectValue("email", "error.user",
//                            "There is already a user registered with the email provided");
//        }
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("registration");
//        } else {
//            userService.saveUser(user);
//            modelAndView.addObject("successMessage", "User has been registered successfully");
//            modelAndView.addObject("user", new User());
//            modelAndView.setViewName("registration");
//
//        }
//        return modelAndView;
//    }

}
