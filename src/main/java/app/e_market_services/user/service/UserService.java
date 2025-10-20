package app.e_market_services.user.service;

import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.e_market_services.user.dto.UserRequestDto;
import app.e_market_services.user.dto.UserResponseDto;
import app.e_market_services.user.model.UserModel;
import app.e_market_services.user.repository.UserRepository;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
        this.objectMapper = new ObjectMapper();
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> objectMapper.convertValue(user, UserResponseDto.class))
                .toList();
    }

    public UserResponseDto createUser(UserRequestDto user) {
        UserModel userModel = UserModel.builder().email(user.getEmail()).name(user.getName())
                .password(user.getPassword()).build();
        UserModel createdUser = userRepository.save(userModel);
        return objectMapper.convertValue(createdUser, UserResponseDto.class);
    }

    public Boolean verifyUser(UserRequestDto user) {
        UserModel verifiedUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        log.info("UserModel {}", verifiedUser);
        return Objects.nonNull(verifiedUser);
    }
}
