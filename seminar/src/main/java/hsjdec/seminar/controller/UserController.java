package hsjdec.seminar.controller;

import hsjdec.seminar.domain.User;
import hsjdec.seminar.dto.UserReq;
import hsjdec.seminar.dto.UserRes;
import hsjdec.seminar.dto.UsersRes;
import hsjdec.seminar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        UsersRes response = new UsersRes(userService.findAllUsers());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> postUser(@RequestBody UserReq userRequest){
        User user = new User();
        user.setName(userRequest.getName());
        User resUser = userService.createUser(user);
        return new ResponseEntity<>(resUser, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        UserRes response = new UserRes(id, userService.findUser(id).getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}