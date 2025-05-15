package com.example.demo.controller;

import com.example.demo.dto.UsuarioLogin;
import com.example.demo.model.Usuario;
import com.example.demo.service.JwtService;
import com.example.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "API para la gestión de usuarios")
public class UsuarioController {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(JwtService jwtService, PasswordEncoder passwordEncoder, UsuarioService usuarioService) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioService = usuarioService;
    }

    // LOGIN
    @PostMapping("/login")
    @Operation(summary = "Loguear Usuario", description = "Valida las credenciales del usuario para el inicio de sesión.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuario logueado con éxito"), @ApiResponse(responseCode = "401", description = "Usuario o contraseña incorrectos")})
    public ResponseEntity<?> loginUsuario(@RequestBody @Parameter(description = "Credenciales de Acceso") UsuarioLogin usuarioLogin) {
        Optional<Usuario> optionalUsuario = usuarioService.findById(usuarioLogin.getIdUsuario()); // el opcional es para evitar que la respuesta genere un null pointer exception
        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        Usuario usuario = optionalUsuario.get();
        // 2. Verificar la contraseña
        if (!passwordEncoder.matches(usuarioLogin.getClave(), usuario.getClave())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
        // 3. Si todo es correcto, devolver una respuesta apropiada
        String jwt = this.jwtService.generateJwtToken(usuario);
        return ResponseEntity.ok(new UsuarioLogin(usuario.getUsua_id(), "", usuario.getRol_rol_id(), jwt));
    }

    // CREATE
    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario con los datos proporcionados.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Usuario creado con éxito"), @ApiResponse(responseCode = "400", description = "Datos inválidos")})
    public ResponseEntity<Usuario> createUsuario(@RequestBody @Parameter(description = "Datos del usuario a crear") Usuario usuario) {
        Usuario newUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);
    }

    // READ (All)
    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida con éxito"), @ApiResponse(responseCode = "500", description = "Error interno del servidor")})
    public ResponseEntity<?> getAllUsuarios(@RequestHeader(value = "Authorization", required = false) @Parameter(description = "Token de autorización JWT", in = ParameterIn.HEADER, name = "Authorization", example = "Bearer <token>") String authHeader) {
        String token = this.jwtService.extractToken(authHeader);
        if (token != null && this.jwtService.validateJwtToken(token)) {
            List<Usuario> usuarios = usuarioService.findAll();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }

    // READ (By ID)
    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Devuelve un usuario específico basado en su ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuario encontrado"), @ApiResponse(responseCode = "404", description = "Usuario no encontrado")})
    public ResponseEntity<?> getUsuarioById(@PathVariable @Parameter(description = "ID del usuario") Integer id, @RequestHeader(value = "Authorization", required = false) @Parameter(description = "Token de autorización JWT", in = ParameterIn.HEADER, name = "Authorization", example = "Bearer <token>") String authHeader) {
        String token = this.jwtService.extractToken(authHeader);
        if (token == null || !this.jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
        Optional<Usuario> usuario = usuarioService.findById(id);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario", description = "Actualiza los datos de un usuario existente.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuario actualizado con éxito"), @ApiResponse(responseCode = "404", description = "Usuario no encontrado")})
    public ResponseEntity<?> updateUsuario(@PathVariable @Parameter(description = "ID del usuario") Integer id, @RequestBody @Parameter(description = "Datos actualizados del usuario") Usuario usuario, @RequestHeader(value = "Authorization", required = false) @Parameter(description = "Token de autorización JWT", in = ParameterIn.HEADER, name = "Authorization", example = "Bearer <token>") String authHeader) {
        String token = this.jwtService.extractToken(authHeader);
        if (token == null || !this.jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
        Optional<Usuario> existingUsuario = usuarioService.findById(id);
        if (existingUsuario.isPresent()) {
            existingUsuario.get().setCorreo(usuario.getCorreo());
            existingUsuario.get().setNombre(usuario.getNombre());
            existingUsuario.get().setTelefono(usuario.getTelefono());
            Optional<Usuario> updatedUsuario = usuarioService.update(id, existingUsuario.get());
            return new ResponseEntity<>(updatedUsuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario basado en su ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Usuario eliminado con éxito"), @ApiResponse(responseCode = "404", description = "Usuario no encontrado")})
    public ResponseEntity<?> deleteUsuario(@PathVariable @Parameter(description = "ID del usuario") Integer id, @RequestHeader(value = "Authorization", required = false) @Parameter(description = "Token de autorización JWT", in = ParameterIn.HEADER, name = "Authorization", example = "Bearer <token>") String authHeader) {
        String token = this.jwtService.extractToken(authHeader);
        if (token == null || !this.jwtService.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
        Optional<Usuario> existingUsuario = usuarioService.findById(id);

        if (existingUsuario.isPresent()) {
            usuarioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

      /*
    @GetMapping("/buscar")
    @Operation(summary = "Buscar usuarios por filtros", description = "Busca usuarios por nombre, email y edad.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    public ResponseEntity<List<Usuario>> buscarUsuarios(
            @RequestParam @Parameter(description = "Nombre del usuario (parcial o completo)") String nombre,
            @RequestParam(required = false) @Parameter(description = "Email del usuario (opcional)") String email,
            @RequestParam @Parameter(description = "Edad del usuario") int edad) {
        List<Usuario> usuarios = usuarioService.buscarPorFiltros(nombre, email);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/auth")
    @Operation(summary = "Obtener usuario por token", description = "Devuelve un usuario basado en su token de autorización.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "401", description = "Token inválido o no autorizado")
    })
    public ResponseEntity<Usuario> getUserByToken(@RequestHeader("Authorization") @Parameter(description = "Token de autorización") String authToken) {
        Usuario usuario = usuarioService.findByAuthToken(authToken);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    } */
}