package com.fitplace.fitplace20.service;

import com.fitplace.fitplace20.model.User;
import com.fitplace.fitplace20.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Serviço responsável por gerenciar os usuários.
 */
@Service
@Slf4j
public class UserService {

    /**
     * Repositório de usuários.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Cria um novo usuário.
     *
     * @param user Usuário a ser criado.
     * @return Usuário criado.
     */
    public User createUser(User user) {
        // Valida os dados do usuário
        validateUser(user);

        // Busca o endereço do usuário pelo CPF
        String address = fetchAddressFromViaCEP(user.getCpf());
        user.setEndereco(address);

        // Salva o usuário no banco de dados
        return userRepository.save(user);
    }

    /**
     * Lista todos os usuários.
     *
     * @return Lista de usuários.
     */
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    /**
     * Busca um usuário pelo ID.
     *
     * @param id ID do usuário.
     * @return Usuário encontrado.
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Atualiza um usuário existente.
     *
     * @param id          ID do usuário.
     * @param updatedUser Usuário atualizado.
     * @return Usuário atualizado.
     */
    public User updateUser(Long id, User updatedUser) {
        // Valida os dados do usuário
        validateUser(updatedUser);

        // Busca o usuário existente
        User existingUser = getUserById(id).orElseThrow();

        // Atualiza os dados do usuário
        existingUser.setNome(updatedUser.getNome());
        existingUser.setSexo(updatedUser.getSexo());
        existingUser.setCpf(updatedUser.getCpf());
        existingUser.setDataNascimento(updatedUser.getDataNascimento());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setSenha(updatedUser.getSenha());
        existingUser.setEndereco(updatedUser.getEndereco());

        // Salva o usuário atualizado no banco de dados
        return userRepository.save(existingUser);
    }

    /**
     * Deleta um usuário.
     *
     * @param id ID do usuário.
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Valida os dados do usuário.
     *
     * @param user Usuário a ser validado.
     */
    private void validateUser(User user) {
        // Verifica se o CPF é válido
        if (!isValidCPF(user.getCpf())) {
            throw new RuntimeException("CPF inválido");
        }

        // Verifica se o e-mail é válido
        if (!isValidEmail(user.getEmail())) {
            throw new RuntimeException("E-mail inválido");
        }

        // Verifica se o e-mail já está em uso
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já em uso");
        }
    }

    /**
     * Verifica se um CPF é válido.
     *
     * @param cpf CPF a ser validado.
     * @return True se o CPF for válido, false caso contrário.
     */
    private boolean isValidCPF(String cpf) {
        // Implementação da lógica de validação de CPF
        // Fonte: https://www.devmedia.com.br/validar-cpf-em-java/21905
        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digits[i] * (10 - i);
        }

        int remainder = sum % 11;
        if (remainder < 2) {
            remainder = 0;
        } else {
            remainder = 11 - remainder;
        }

        if (remainder != digits[9]) {
            return false;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += digits[i] * (11 - i);
        }

        remainder = sum % 11;
        if (remainder < 2) {
            remainder = 0;
        } else {
            remainder = 11 - remainder;
        }

        return remainder == digits[10];
    }

    /**
     * Verifica se um e-mail é válido.
     *
     * @param email E-mail a ser validado.
     * @return True se o e-mail for válido, false caso contrário.
     */
    private boolean isValidEmail(String email) {
        // Implementação da lógica de validação de e-mail
        // Fonte: https://www.devmedia.com.br/validar-e-mail-em-java/21906
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    /**
     * Busca o endereço de um usuário pelo CPF.
     *
     * @param cpf CPF do usuário.
     * @return Endereço do usuário.
     */
    private String fetchAddressFromViaCEP(String cpf) {
        // Implementação da integração com a API do ViaCEP
        // Fonte: https://viacep.com.br/
        try {
            // Simula a chamada à API do ViaCEP
            String address = "Rua Exemplo, 123, Bairro Exemplo, Cidade Exemplo - UF";
            return address;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar endereço", e);
        }
    }
}