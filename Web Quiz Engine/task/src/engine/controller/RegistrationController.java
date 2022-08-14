package engine.controller;

import engine.entity.User;
import engine.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {
    @Autowired
    private RegistrationService service;

    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        service.registerUser(user);
        return new ResponseEntity<>(user.getEmail() + " successfully registered", HttpStatus.OK);
    }
}
