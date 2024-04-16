package com.example.UniBet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UniBet.model.Usuario;
import com.example.UniBet.model.DTO.UsuarioCreateDTO;
import com.example.UniBet.model.DTO.UsuarioUpdateDTO;
import com.example.UniBet.model.DTO.UsuarioViewDTO;
import com.example.UniBet.model.dao.UsuarioDAO;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO dUsuario;

    public Usuario verificaSalvamento(UsuarioCreateDTO usuario) throws Exception {
        validateUser(usuario);

        Usuario us = new Usuario();
        us.setNome(usuario.getNome());
        us.setEmail(usuario.getEmail());
        us.setSenha(usuario.getSenha());
        us.setLogin(usuario.getLogin());
        us.setSaldo(usuario.getSaldo());
        us.setEhAdmin(usuario.isEhAdmin());

        return dUsuario.save(us);
    }

    public Object getAllUsers() {
        return dUsuario.findAll();
    }

    public void deleteUser(int id) throws Exception {
        Usuario existinUser = dUsuario.findById(id).orElse(null);

        if (existinUser == null) {
            throw new Exception("User not found.");
        }

        dUsuario.delete(existinUser);
    }

    public UsuarioViewDTO getUser(int id) throws Exception {
        Usuario existingUser = dUsuario.findById(id).orElse(null);

        if (existingUser == null) {
            throw new Exception("User not found.");
        }

        UsuarioViewDTO us = new UsuarioViewDTO();
        us.setId(existingUser.getId());
        us.setNome(existingUser.getNome());
        us.setEmail(existingUser.getEmail());
        us.setLogin(existingUser.getLogin());
        us.setSaldo(existingUser.getSaldo());
        us.setEhAdmin(existingUser.isEhAdmin());

        return us;
    }

    public void updateUser(int id, UsuarioUpdateDTO usuario) throws Exception {
        Optional<Usuario> optionalUsuario = dUsuario.findById(id);
        if (!optionalUsuario.isPresent()) {
            throw new Exception("Usuario " + id + " não existe");
        }

        Usuario existingUser = optionalUsuario.get();

        validateUserUpdate(usuario);

        existingUser.setNome(usuario.getNome());
        existingUser.setEmail(usuario.getEmail());
        existingUser.setSenha(usuario.getSenha());
        existingUser.setLogin(usuario.getLogin());
        existingUser.setSaldo(usuario.getSaldo());
        existingUser.setEhAdmin(usuario.isEhAdmin());

        dUsuario.save(existingUser);
    }

    private void validateUser(UsuarioCreateDTO usuario) throws Exception {
        if (usuario.getNome().isEmpty() || usuario.getNome().isBlank()) {
            String errorMessage = "Invalid user name.";
            throw new Exception(errorMessage);
        }

        if (usuario.getEmail().isEmpty() || usuario.getEmail().isBlank()) {
            String errorMessage = "Invalid email.";
            throw new Exception(errorMessage);
        }

        if (usuario.getSenha().isEmpty() || usuario.getSenha().isBlank()) {
            String errorMessage = "Invalid password.";
            throw new Exception(errorMessage);
        }

        if (usuario.getLogin().isEmpty() || usuario.getLogin().isBlank()) {
            String errorMessage = "Invalid login.";
            throw new Exception(errorMessage);
        }

        Usuario usuarioComMesmoEmail = dUsuario.findByEmail(usuario.getEmail());
        if (usuarioComMesmoEmail != null) {
            throw new Exception("já existe um usuário cadastrado com esse e-mail");
        }

        Usuario usuarioComMesmoLogin = dUsuario.findByLogin(usuario.getLogin());
        if (usuarioComMesmoLogin != null) {
            throw new Exception("já existe um usuário cadastrado com esse login");
        }
    }

    private void validateUserUpdate(UsuarioUpdateDTO usuario) throws Exception {
        if (usuario.getNome().isEmpty() || usuario.getNome().isBlank()) {
            String errorMessage = "Invalid user name.";
            throw new Exception(errorMessage);
        }

        if (usuario.getEmail().isEmpty() || usuario.getEmail().isBlank()) {
            String errorMessage = "Invalid email.";
            throw new Exception(errorMessage);
        }

        if (usuario.getSenha().isEmpty() || usuario.getSenha().isBlank()) {
            String errorMessage = "Invalid password.";
            throw new Exception(errorMessage);
        }

        if (usuario.getLogin().isEmpty() || usuario.getLogin().isBlank()) {
            String errorMessage = "Invalid login.";
            throw new Exception(errorMessage);
        }

        Usuario usuarioComMesmoEmail = dUsuario.findByEmail(usuario.getEmail());
        if (usuarioComMesmoEmail != null) {
            throw new Exception("já existe um usuário cadastrado com esse e-mail");
        }

        Usuario usuarioComMesmoLogin = dUsuario.findByLogin(usuario.getLogin());
        if (usuarioComMesmoLogin != null) {
            throw new Exception("já existe um usuário cadastrado com esse login");
        }
    }
}
