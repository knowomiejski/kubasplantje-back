package nl.kubasplantje.kubasplantje.controllers;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.kubasplantje.kubasplantje.dtos.LoginRequest;
import nl.kubasplantje.kubasplantje.dtos.UserDto;
import nl.kubasplantje.kubasplantje.models.UserModel;
import nl.kubasplantje.kubasplantje.services.TokenService;
import nl.kubasplantje.kubasplantje.services.UserService;

@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {

    private UserService userService;
    private TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private ModelMapper modelMapper;

    @Autowired
    public AuthController(UserService userService, TokenService tokenService, AuthenticationManager authenticationManager, ModelMapper modelMapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/check")
    public ResponseEntity<String> check() {
        String check = "success";
        return ResponseEntity.ok(check);
    }

    @GetMapping("/logout")
    public String logout(Authentication authentication) {
        String token = tokenService.expireToken(authentication);
        return token;
    }

    @PostMapping("/token")
    public String token(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        String token = tokenService.generateToken(authentication);
        return token;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserModel userModel) {
        UserDto userDto = convertModelToDto(this.userService.addNewUser(userModel));
        return ResponseEntity.ok(userDto);
    }


    private UserModel convertDtoToModel(UserDto userDto) {
        return this.modelMapper.map(userDto, UserModel.class);
    }

    private UserDto convertModelToDto(UserModel userModel) {
        return this.modelMapper.map(userModel, UserDto.class);
    }
}
