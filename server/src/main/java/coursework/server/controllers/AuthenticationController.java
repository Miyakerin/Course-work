package coursework.server.controllers;

import coursework.server.Request.AuthenticationRequest;
import coursework.server.Request.RegisterRequest;
import coursework.server.Service.AuthenticationService;
import coursework.server.Response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * контроллер для аутентификпции
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    /**
     * @param request реквест класса RegisterRequest, содержащий информацию о новом пользователе
     * @return http-ответ сервера
     */
    @PostMapping(value = "/register",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    /**
     * @param request реквест, содержащий информацию о пользователе для аутентификации
     * @return http-ответ сервера, содержащий jwt-token
     */
    @PostMapping(value = "/authenticate",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
