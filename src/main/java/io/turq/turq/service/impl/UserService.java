package io.turq.turq.service.impl;

import io.turq.turq.contstants.APIErrors;
import io.turq.turq.exceptions.UserBadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import io.turq.turq.service.interfaces.IUserService;
import io.turq.turq.repository.UserRepository;
import io.turq.turq.entities.UserEntity;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserEntity findByEmail(String email) {
        UserEntity user = repository.findByEmail(email);
        return user;
    }

    @Override
    public UserEntity save(String firstName, String lastName, String email, String password) {
        UserEntity retUser = null;
        if (email == null || password == null)
          throw new UserBadRequestException(APIErrors.USER_BAD_REQUEST);
        try {
            UserEntity user = new UserEntity(firstName, lastName, email, password);
            retUser = repository.save(user);
        } catch (DataIntegrityViolationException e) {
            System.out.println("PSQL Data Integrity Violation Exception: " + e.getMessage());
            System.out.println(e.getClass());
            retUser = null;
        }
        return retUser;
    }
}
