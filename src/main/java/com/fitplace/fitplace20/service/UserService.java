package com.fitplace.fitplace20.service;

import com.fitplace.fitplace20.model.User;
import com.fitplace.fitplace20.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        validateUser(user);
        user.setEndereco(fetchAddressFromViaCEP(user.getCpf()));
        user.setSenha(encodePassword(user.getSenha()));
        return userRepository.save(user);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        validateUser(updatedUser);
        User existingUser = getUserById(id).orElseThrow();
        existingUser.setNome(updatedUser.getNome());
        existingUser.setSexo(updatedUser.getSexo());
        existingUser.setCpf(updatedUser.getCpf());
        existingUser.setDataNascimento(updatedUser.getDataNascimento());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setSenha(encodePassword(updatedUser.getSenha()));
        existingUser.setEndereco(updatedUser.getEndereco());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private void validateUser(User user) {
        if (!isValidCPF(user.getCpf())) {
            throw new ValidationException("CPF inválido");
        }
        if (!isValidEmail(user.getEmail())) {
            throw new ValidationException("E-mail inválido");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ValidationException("E-mail já em uso");
        }
    }

    private boolean isValidCPF(String cpf) {
        // Lógica de validação de CPF
        return true;
    }

    private boolean isValidEmail(String email) {
        // Lógica de validação de e-mail
        return true;
    }

    private String fetchAddressFromViaCEP(String cpf) {
        // Simula a busca do endereço via API
        return "Rua Exemplo, 123";
    }

    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

