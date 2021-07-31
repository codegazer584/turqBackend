package io.turq.turq.service.impl;

import io.turq.turq.config.JwtTokenUtil;
import io.turq.turq.contstants.APIErrors;
import io.turq.turq.exceptions.UserBadRequestException;
import io.turq.turq.exceptions.UserNotFoundException;
import io.turq.turq.exceptions.ForbiddenException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import io.turq.turq.service.interfaces.IUserService;
import io.turq.turq.repository.UserRepository;
import io.turq.turq.entities.UserEntity;
import io.turq.turq.model.user.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserEntity findByEmail(String email) {
        UserEntity user = repository.findByEmail(email);
        return user;
    }

    @Override
    public UserEntity save(String firstName, String lastName, String email, String password, Boolean admin) {
        UserEntity retUser = null;
        if (email == null || password == null)
          throw new UserBadRequestException(APIErrors.USER_BAD_REQUEST);
        try {
            UserEntity user = new UserEntity(firstName, lastName, email, password, admin);
            retUser = repository.save(user);
        } catch (DataIntegrityViolationException e) {
            System.out.println("PSQL Data Integrity Violation Exception: " + e.getMessage());
            System.out.println(e.getClass());
            retUser = null;
        }
        return retUser;
    }

    public UserProfileResponse getName(String email) {
        UserProfileResponse retUser = null;
        if (email == null)
          throw new UserBadRequestException(APIErrors.USER_BAD_REQUEST);
        try {
            UserEntity user = repository.findByEmail(email);
            retUser = new UserProfileResponse(user.getFirstname(), user.getLastname(), email);
            if (retUser == null) {
                throw new UserNotFoundException(APIErrors.USER_NOT_FOUND);
            }
        } catch (DataIntegrityViolationException e) {
            System.out.println("PSQL Data Integrity Violation Exception: " + e.getMessage());
            System.out.println(e.getClass());
            retUser = null;
        }
        return retUser;
    }

    public UserUpdateResponse updateName(UserUpdateRequest req, String email, String token) {
        UserUpdateResponse retUser = null;
        String userEmail = jwtTokenUtil.getSubject(token);
        if (email == null)
          throw new UserBadRequestException(APIErrors.USER_BAD_REQUEST);
        try {
            UserEntity user = repository.findByEmail(email);
            if (user == null) {
                throw new UserNotFoundException(APIErrors.USER_NOT_FOUND);
            } else if (!user.getEmail().equalsIgnoreCase(userEmail)) {
                throw new ForbiddenException(APIErrors.USER_UPDATE_PERMISSION);
            } else {
                UserEntity latestUser = repository.save(new UserEntity(req.getFirstName(), req.getLastName(), email, user.getPassword(), user.getAdmin()));
                retUser = new UserUpdateResponse(latestUser.getFirstname(), latestUser.getLastname());
            }
        } catch (DataIntegrityViolationException e) {
            System.out.println("PSQL Data Integrity Violation Exception: " + e.getMessage());
            System.out.println(e.getClass());
            retUser = null;
        }
        return retUser;
    }

    public UserEntity getActivity(String email) {
        UserEntity retUser = null;
        if (email == null)
          throw new UserBadRequestException(APIErrors.USER_BAD_REQUEST);
        try {
            UserEntity user = repository.findByEmail(email);
            if (user == null) {
                throw new UserNotFoundException(APIErrors.USER_NOT_FOUND);
            } else {
                //retUser = repository.save(new UserEntity(firstName, lastName, email, user.getPassword(), user.getAdmin()));
            }
        } catch (DataIntegrityViolationException e) {
            System.out.println("PSQL Data Integrity Violation Exception: " + e.getMessage());
            System.out.println(e.getClass());
            retUser = null;
        }
        return retUser;
    }
}
