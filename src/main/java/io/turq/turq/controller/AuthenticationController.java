package io.turq.turq.controller;

import io.turq.turq.config.JwtTokenUtil;
import io.turq.turq.model.login.LoginResponse;
import io.turq.turq.model.register.RegisterRequest;
import io.turq.turq.model.register.RegisterResponse;
import io.turq.turq.service.interfaces.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.turq.turq.model.login.LoginRequest;
import io.turq.turq.contstants.UrlConstants;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(path = UrlConstants.LOGIN_URL, consumes = "application/json", produces = "application/json")
    public ResponseEntity login(@RequestBody LoginRequest req) {
        LoginResponse res =  authenticationService.login(req);
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = UrlConstants.REGISTER_URL, consumes = "application/json", produces = "application/json")
    public ResponseEntity register(@RequestBody RegisterRequest req) {
        RegisterResponse res = authenticationService.register(req);
        return ResponseEntity.ok(res);
    }
}
