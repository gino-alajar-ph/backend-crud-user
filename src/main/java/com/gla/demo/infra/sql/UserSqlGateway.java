package com.gla.demo.infra.sql;

import com.gla.demo.core.UserGateway;
import com.gla.demo.core.exception.MissingRequiredArgException;
import com.gla.demo.core.exception.RecordNotFoundException;
import com.gla.demo.core.model.User;
import com.gla.demo.infra.sql.entity.UserEntity;
import com.gla.demo.infra.sql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class UserSqlGateway implements UserGateway {

    private final UserRepository userRepository;

    @Override
    public Optional<List<User>> getAllUsers() {

        List<UserEntity> userEntityList = userRepository.findAll();

        return Optional.of(userEntityList.stream().map(UserSqlGateway::buildUser).collect(Collectors.toList()));
    }

    @Override
    public Optional<User> getUserById(Long id) {

        if (Objects.isNull(id)) {
            throw new MissingRequiredArgException("Missing required parameter/s");
        }

        Optional<UserEntity> employeeEntity = userRepository.findById(id);

        return employeeEntity.map(UserSqlGateway::buildUser);
    }

    @Override
    public void addUser(User user) {

        if (Objects.isNull(user)) {
            throw new MissingRequiredArgException("Missing required parameter/s");
        }

        boolean userNameExisting = userRepository.findByUserName(user.getUserName()).isPresent();

        if (!userNameExisting) {
            userRepository.save(buildUserEntity(user));
        }
    }

    @SneakyThrows
    @Override
    public void updateUser(User user, Long id) {

        if (Objects.isNull(user) || Objects.isNull(id)) {
            throw new MissingRequiredArgException("Missing required parameter/s");
        }

        Optional<UserEntity> employeeEntity = userRepository.findById(id);
        if (employeeEntity.isPresent()) {
            userRepository.save(buildUserEntity(employeeEntity.get(), user, id));
        } else {
            throw new RecordNotFoundException("Record not found");
        }

    }

    @SneakyThrows
    @Override
    public void deleteUserById(Long id) {

        if (Objects.isNull(id)) {
            throw new MissingRequiredArgException("Missing required parameter/s");
        }

        Optional<UserEntity> employeeEntity = userRepository.findById(id);

        if (employeeEntity.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Record not found");
        }
    }

    private static User buildUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .lastName(userEntity.getLastName())
                .firstName(userEntity.getFirstName())
                .userName(userEntity.getUserName())
                .role(userEntity.getRole())
                .build();
    }

    private static UserEntity buildUserEntity(User user) {
        return UserEntity.builder()
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .userName(user.getUserName())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    private static UserEntity buildUserEntity(UserEntity userEntity, User user, Long id) {
        return UserEntity.builder()
                .id(id)
                .lastName(Objects.isNull(user.getLastName()) ? userEntity.getLastName() : user.getLastName())
                .firstName(Objects.isNull(user.getFirstName()) ? userEntity.getFirstName() : user.getFirstName())
                .userName(Objects.isNull(user.getUserName()) ? userEntity.getUserName() : user.getUserName())
                .password(Objects.isNull(user.getPassword()) ? userEntity.getPassword() : user.getPassword())
                .role(Objects.isNull(user.getRole()) ? userEntity.getRole() : user.getRole())
                .build();
    }


}
