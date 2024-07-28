package com.espritech.workerExpress221.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/auth/authentication")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){

        var phoneNumber = request.getPhoneNumber();
        if (!phoneNumber.startsWith("+221")) {
            request.setPhoneNumber("+221" + request.getPhoneNumber());
        }

        var rest = service.authenticate(request);

        return ResponseEntity.ok(rest);
    }

}
