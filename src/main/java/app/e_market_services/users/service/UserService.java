package app.e_market_services.users.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.e_market_services.users.dto.request.UserRequest;
import app.e_market_services.users.dto.UserResponseDto;
import app.e_market_services.users.model.Users;
import app.e_market_services.users.repository.UsersRepository;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UsersRepository usersRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UsersRepository repository, PasswordEncoder passwordEncoder, ObjectMapper objectMapper) {
        this.usersRepository = repository;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDto> findAll() {
        return usersRepository.findAll().stream()
                .map(user -> objectMapper.convertValue(user, UserResponseDto.class))
                .toList();
    }

    public UserResponseDto createUser(UserRequest user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        Users users = Users.builder().email(user.getEmail()).firstName(user.getFirstName())
                .passwordHash(hashedPassword).build();
        Users createdUser = usersRepository.save(users);
        return objectMapper.convertValue(createdUser, UserResponseDto.class);
    }

    public Boolean verifyUser(UserRequest user) {
        Users verifiedUser = usersRepository.findByEmail(user.getEmail()).orElseThrow(()-> new RuntimeException("User not found"));
        log.info("UserModel {}", verifiedUser);
        return passwordEncoder.matches(user.getPassword(), verifiedUser.getPasswordHash());
    }

    public UserResponseDto updateUserDetails(String email, UserRequest userRequest) {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(RuntimeException::new);

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());

        Users savedUser = usersRepository.save(user);
        return UserResponseDto.builder()
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .build();
    }
}
