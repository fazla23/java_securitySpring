package com.fazla.securityspring.controller;

import com.fazla.securityspring.model.User;
import com.fazla.securityspring.model.UserType;
import com.fazla.securityspring.repository.UserRepository;
import com.fazla.securityspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/no-auth")
    public String noAuth(){
        List<User> users=userService.getUserByUserTypes("ROLE_employee");
        int employeeCount = users.size();
        return "There are total "+employeeCount+" employees";
    }

    @PreAuthorize("hasAnyRole('ROLE_hr_admin')")
    @RequestMapping(value = "/admin" , method = RequestMethod.GET)
    public User admin(Principal principal){

            String username = principal.getName();
            return userService.getUserByUsername(username);
    }

    @PreAuthorize("hasAnyRole('ROLE_hr_executive')")
    @RequestMapping(value = "/executive" , method = RequestMethod.GET)
    public User executive(Principal principal){

        String username = principal.getName();
        return userService.getUserByUsername(username);
    }

    @PreAuthorize("hasAnyRole('ROLE_employee')")
    @RequestMapping(value = "/employee" , method = RequestMethod.GET)
    public User employee(Principal principal){

        String username = principal.getName();
        return userService.getUserByUsername(username);
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_hr_executive','ROLE_hr_admin')")
    @GetMapping("/employeelist")
    public List<User> employee(){
        return (List<User>) userService.getUserByUserTypes("ROLE_employee");
    }

    @PreAuthorize("hasAnyRole('ROLE_hr_admin')")
    @PostMapping("/employee")
    public User addEmployee(@RequestBody User user){
        userRepository.save(user);
        return user;
    }

    @PreAuthorize("hasAnyRole('ROLE_hr_admin')")
    @PutMapping("/employee")
    public User updateEmployee(@RequestBody User user){
        userRepository.save(user);
        return user;
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_hr_admin')")
    @DeleteMapping("/employee/{username}")
    public String deleteEmployee(@PathVariable String username){
        User user1= userService.getUserByUsername(username);
        userRepository.delete(user1);
        return "Deleted the user";
    }
}
