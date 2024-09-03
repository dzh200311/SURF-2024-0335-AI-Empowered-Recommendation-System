package com.surf0335.AI_Recommendation_System.controller;

import com.surf0335.AI_Recommendation_System.model.Student;
import com.surf0335.AI_Recommendation_System.model.Teacher;
import com.surf0335.AI_Recommendation_System.model.User;
import com.surf0335.AI_Recommendation_System.repository.StudentRepo;
import com.surf0335.AI_Recommendation_System.repository.TeacherRepo;
import com.surf0335.AI_Recommendation_System.repository.UserRepo;
import com.surf0335.AI_Recommendation_System.services.StoreImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.surf0335.AI_Recommendation_System.model.Manager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private TeacherRepo teacherRepository;

    @Autowired
    private StoreImage storeImage;

    @GetMapping("/user_home_page")
    public String getUserHomePage(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("manager", new Manager());
            return "userInfo";
        } else {
            model.addAttribute("user", new User());
            model.addAttribute("manager", new Manager());
            return "logIn";
        }
    }

    @GetMapping("/add_user")
    public String getUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("manager", new Manager());
        return "signUp";
    }

    @PostMapping("/add_user")
    public String postAddUser(@ModelAttribute User user, Model model) {
        User existingUser = userRepo.findByUsername(user.getUsername());
        User existingUserByEmail = userRepo.findByEmail(user.getEmail());

        if (existingUser != null) {
            model.addAttribute("manager", new Manager());
            model.addAttribute("error", "Username already exists. Please choose a different username.");
            return "signUp";
        } else if (existingUserByEmail != null) {
            model.addAttribute("manager", new Manager());
            model.addAttribute("error", "Email already used. Please use another email.");
            return "signUp";
        } else {
            if (user.getRole() == null || user.getRole().isEmpty()) {
                model.addAttribute("manager", new Manager());
                model.addAttribute("error", "Please select a role.");
                return "signUp";
            }

            userRepo.save(user);

            // 根据用户角色创建对应的实体
            if ("student".equals(user.getRole())) {
                Student student = new Student();
                student.setUser(user);
                student.setEmail(user.getEmail());
                studentRepository.save(student);
                user.setStudent(student);
            } else if ("teacher".equals(user.getRole())) {
                Teacher teacher = new Teacher();
                teacher.setUser(user);
                teacher.setEmail(user.getEmail());
                teacherRepository.save(teacher);
                user.setTeacher(teacher);
            }

            userRepo.save(user);

            model.addAttribute("manager", new Manager());
            model.addAttribute("success", "Sign up successfully. Now you can log in!");
            return "signUp";
        }
    }

    @GetMapping("/log_in")
    public String getLogInPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "homepageAuth";
        }
        model.addAttribute("user", new User());
        model.addAttribute("manager", new Manager());
        return "logIn";
    }

    @PostMapping("/log_in")
    public String logIn(@RequestParam String username, @RequestParam String password, HttpSession session, Model m) {
        User user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);

            if ("teacher".equals(user.getRole())) {
                return "/teacherdashboard";
            } else if ("student".equals(user.getRole())) {
                return "/studentdashboard";
            } else {
                return "redirect:/home_page_auth";
            }
        } else {
            m.addAttribute("user", new User());
            m.addAttribute("manager", new Manager());
            m.addAttribute("loginerror", "Invalid username or password. Please check again.");
            return "logIn"; 
        }
    }

    @PostMapping("/user_home_page")
    public String updateProfile(@ModelAttribute User updateUser, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        User existingUserByEmail = userRepo.findByEmail(updateUser.getEmail());

        if (currentUser != null) {
            if (updateUser.getNickname() != null && !updateUser.getNickname().isEmpty())
                currentUser.setNickname(updateUser.getNickname());

            if (updateUser.getGender() != null)
                currentUser.setGender(updateUser.getGender());

            if (updateUser.getBirthday() != null)
                currentUser.setBirthday(updateUser.getBirthday());

            if (!updateUser.getEmail().equals(currentUser.getEmail())) {
                if (existingUserByEmail != null) {
                    model.addAttribute("manager", new Manager());
                    model.addAttribute("error", "Email already used. Please use another email.");
                    model.addAttribute("user", currentUser);
                    return "userInfo";
                }
            }
            currentUser.setEmail(updateUser.getEmail());

            if (updateUser.getPhone() != null && !updateUser.getPhone().isEmpty())
                currentUser.setPhone(updateUser.getPhone());

            if (updateUser.getAbout() != null && !updateUser.getAbout().isEmpty())
                currentUser.setAbout(updateUser.getAbout());

            userRepo.save(currentUser);
            return "redirect:/user_home_page";  
        } else {
            return "redirect:/log_in";
        }
    }

    @GetMapping("/user_msg")
    public String getUserMessagePage(Model m) {
        m.addAttribute("user", new User());
        return "message";
    }

    @GetMapping("/user_appointment")
    public String getUserAppointmentPage(Model m) {
        m.addAttribute("user", new User());
        return "myappointments";
    }

    @GetMapping("/user_changepsw")
    public String getUserChangePswPage(Model m) {
        m.addAttribute("user", new User());
        return "changepassword";
    }

    @GetMapping("/user_delete")
    public String getUserCancelPage(Model m) {
        m.addAttribute("user", new User());
        return "deleteaccount";
    }

    @GetMapping("/user_home_page/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/home_page";
    }
    
    @PostMapping("/user_delete")
    public String deleteUser(@RequestParam String username, @RequestParam String password,
                             HttpSession session, RedirectAttributes redirectAttributes, Model m) {
        User user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            user.setUsername(null);
            user.setPassword(null);
            user.setEmail(null);
            userRepo.save(user);
            session.removeAttribute("user");
            return "redirect:/home_page";
        } else {
            m.addAttribute("error", "Invalid username or password.");
            return "deleteaccount";
        }
    }

    @GetMapping("/account-recovery")
    public String showAccountRecoveryForm(Model model) {
        model.addAttribute("user", new User());
        return "user/account-recovery";
    }

    @PostMapping("/account-recovery")
    public String accountRecovery(@ModelAttribute User user, Model model, HttpSession session) {
        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser != null) {
            session.setAttribute("user2", existingUser);
            return "user/password-reset";
        } else {
            model.addAttribute("error", "No user found with that email address");
            return "user/account-recovery";
        }
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam String newPassword, @RequestParam String confirmPassword, Model model, HttpSession session) {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "user/password-reset";
        }

        User user = (User) session.getAttribute("user2");

        if (user != null) {
            user.setPassword(newPassword);
            userRepo.save(user);
            session.invalidate();
            model.addAttribute("success", "Reset password successful! Now you can log in!");
            return "user/password-reset";
        } else {
            model.addAttribute("error", "User not found! (Maybe you've already reset the password)");
            return "user/password-reset";
        }
    }

    @PostMapping("/uploadAvatar")
    public String createOrUpdateTrainer(@RequestParam(name ="newAvatar",required = false) MultipartFile file, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser != null && file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String url = storeImage.store(file); 
                
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/")
                    .path(fileName)
                    .toUriString();
        
            currentUser.setAvatarUrl(fileDownloadUri);
            userRepo.save(currentUser);
        }
        return "redirect:/user_home_page";
    }
}
