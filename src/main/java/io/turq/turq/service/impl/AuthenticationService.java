package io.turq.turq.service.impl;

import io.turq.turq.config.JwtTokenUtil;
import io.turq.turq.contstants.APIErrors;
import io.turq.turq.entities.ContestEntity;
import io.turq.turq.entities.UserEntity;
import io.turq.turq.exceptions.ContestNotFoundException;
import io.turq.turq.exceptions.UserAlreadyExistsException;
import io.turq.turq.model.login.LoginRequest;
import io.turq.turq.model.login.LoginResponse;
import io.turq.turq.service.interfaces.IAuthenticationService;
import io.turq.turq.service.interfaces.IContestService;
import io.turq.turq.service.interfaces.IUserService;
import io.turq.turq.model.register.RegisterResponse;
import io.turq.turq.model.register.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public RegisterResponse register(RegisterRequest req) {
        String token = null;
        req.setEmail(req.getEmail().toLowerCase());
        req.setPassword(bCryptPasswordEncoder.encode(req.getPassword()));
        UserEntity existing = userService.findByEmail(req.getEmail());
        if (existing != null) {
            throw new UserAlreadyExistsException(APIErrors.USER_ALREADY_EXISTS);
        }
        UserEntity user = userService.save(req.getFirstName(), req.getLastName(), req.getEmail(), req.getPassword());
        if (user != null) {
            token = jwtTokenUtil.generateJwt(req.getEmail());
        }
        RegisterResponse res = new RegisterResponse(token, user);
        return res;
    }

    @Override
    public LoginResponse login(LoginRequest req) {
        req.setEmail(req.getEmail().toLowerCase());
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(req.getEmail().toLowerCase(), req.getPassword());
        authenticationManager.authenticate(authReq);
        req.setPassword(bCryptPasswordEncoder.encode(req.getPassword()));
        String token = jwtTokenUtil.generateJwt(req.getEmail());
        LoginResponse res = new LoginResponse(token, req.getEmail());
        return res;
    }
}
