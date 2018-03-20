package com.example.javadev.controllers;

import com.example.javadev.model.Lecture;
import com.example.javadev.model.User;
import com.example.javadev.repository.LectureRepository;
import com.example.javadev.service.Service;
import com.example.javadev.service.ServiceImpl;
import com.example.javadev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
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


    @RequestMapping(value = "/mylogin", method = RequestMethod.GET)
    public String loginPage(Model model) {
//		model.addAttribute("user", request.getRemoteUser());
        return "loginpage";
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String page(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        return "page";
    }

    @RequestMapping(value = "/addlectures", method = RequestMethod.GET)
    public String addLectures(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        return "add_lectures";
    }

    @RequestMapping(value = "/addlectures", method = RequestMethod.POST)
    public ModelAndView createLecture(
            @RequestParam("lecture_topic") String lecture_topic,
            @RequestParam("lecture_place") String lecture_place,
            @RequestParam("lecture_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date lecture_date) {
        lectureRepository.save(new Lecture(lecture_topic, lecture_place, lecture_date));
        return new ModelAndView("redirect:/home/page");
    }

    @RequestMapping(value = "/mylectures", method = RequestMethod.GET)
    public String myLectures(Model model, HttpServletRequest request) {
        model.addAttribute("lectures", lectureRepository.findAll());
        model.addAttribute("user", request.getRemoteUser());
        return "my_lectures";
    }

    @RequestMapping(value = "/lectureattended", method = RequestMethod.POST)
    @Transactional
    public ModelAndView lectureAttended(@RequestParam("user_email") String user_email,
                                 @RequestParam("lecture_id") int lecture_id) {

        User user = userRepository.findOne(user_email);
        Lecture lecture = lectureRepository.findOne(lecture_id);

        user.getLectures().add(lecture);
        lecture.getUsers().add(user);

        userRepository.save(user);

        return new ModelAndView("redirect:/home/mylectures");
    }

    @RequestMapping(value = "/attendancelist", method = RequestMethod.GET)
    public String attendanceList(Model model, HttpServletRequest request) {
        model.addAttribute("lectures", lectureRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("attendancelist", service.createAttendanceList());
        return "attendance_list";}


//	@Autowired
//	private UserRepository userRepository;
//
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public String helloForm() {
//		return "form";
//	}
//
//	@RequestMapping(path = "/all", method = RequestMethod.GET)
//	public String getAllUsers(Model model, HttpServletRequest request) {
//		model.addAttribute("users", userRepository.findAll());
//		model.addAttribute("user", request.getRemoteUser());
//		return "userslist";
//	}
//
//	@RequestMapping(path = "/delete", method = RequestMethod.POST)
//	public ModelAndView deleteUser(@RequestParam("user_id") long id) {//nazwa w RequestParam ma być taka sama jak w html form name
//		userRepository.delete(id);
//		return new ModelAndView("redirect:/demo/all");
//	}
//
//	@RequestMapping(value = "/create", method = RequestMethod.POST)
//	public ModelAndView createUser(@RequestParam("name") String name, @RequestParam("email") String email) {
//		userRepository.save(new User(name, email));
//		return new ModelAndView("redirect:/demo/all");
//	}
//
//	@RequestMapping(value="/edit/{id}", method= RequestMethod.GET)
//	public String editPage(@PathVariable long id, Model model) {
//		User user=userRepository.findOne(id);
//		model.addAttribute("user", user);
//		return "edituser";
//	}
//
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public ModelAndView editUser(@RequestParam("user_id") long id,
//                                 @RequestParam("name") String name,
//                                 @RequestParam("email") String email) {
//		User user = userRepository.findOne(id);
//		user.setName(name);
//		user.setEmail(email);
//		userRepository.save(user);
//		return new ModelAndView("redirect:/demo/all");
//	}
    }
