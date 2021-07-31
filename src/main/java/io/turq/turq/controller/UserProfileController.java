package io.turq.turq.controller;

import io.turq.turq.contstants.UrlConstants;
import io.turq.turq.entities.UserEntity;
import io.turq.turq.model.user.*;
import io.turq.turq.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserProfileController {

    @Autowired
    private IUserService userService;

    @GetMapping(path = UrlConstants.USERS_URL + "/{email}", produces = "application/json")
    public ResponseEntity getUserName(@PathVariable(value="email") String email) {
        UserProfileResponse user = userService.getName(email);
        return ResponseEntity.ok(user);
    }
    @GetMapping(path = UrlConstants.USERS_URL + "/{email}" + "/activity", produces = "application/json")
    public ResponseEntity getUserActivity(@PathVariable(value="email") String email) {
        ///TO DO
        UserProfileResponse user = userService.getName(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping(path = UrlConstants.USERS_URL + "/{email}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateUserName(@RequestBody UserUpdateRequest req, @PathVariable(value="email") String email, @RequestHeader (name="Authorization") String token) {
        UserUpdateResponse user = userService.updateName(req, email, token);
        return ResponseEntity.ok(user);
    }
}
