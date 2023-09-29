package com.example.demo.service;


import com.example.demo.dto.AuthenticatinonRequest;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService service;

    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {

//        User admin = User.builder()
//                .fullName("tien")
//                .email("tien@gmail.com")
//                .password("123")
//                .role(Role.ADMIN)
//                .build();

        var user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
         repository.save(user);


         return "đăng kí thành công";
//        var jwtToken = service.generateToken(user);
//        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticatinonRequest authenticatinonRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticatinonRequest.getEmail(),
                            authenticatinonRequest.getPassword()
                    )
            );
        }catch (Exception e) {
            e.printStackTrace();
        }

        var user = repository.findByEmail(authenticatinonRequest.getEmail()
        ).orElseThrow();

        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
