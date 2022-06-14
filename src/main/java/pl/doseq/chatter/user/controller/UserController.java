package pl.doseq.chatter.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.doseq.chatter.user.model.LoginRequestDto;
import pl.doseq.chatter.user.service.UserService;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(userService.authenticate(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
    }

}
