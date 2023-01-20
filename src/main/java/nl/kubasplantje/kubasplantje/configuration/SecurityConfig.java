package nl.kubasplantje.kubasplantje.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import nl.kubasplantje.kubasplantje.handlers.UnauthorizedHandler;
import nl.kubasplantje.kubasplantje.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final PasswordEncoderBCrypt passwordEncoderBCrypt;
    private final RsaKeyProperties rsaKeyProperties;

    @Autowired
    public SecurityConfig(UserService userService, PasswordEncoderBCrypt passwordEncoderBCrypt, RsaKeyProperties rsaKeyProperties) {
        this.userService = userService;
        this.passwordEncoderBCrypt = passwordEncoderBCrypt;
        this.rsaKeyProperties = rsaKeyProperties;
        }

    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoderBCrypt.passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
        .cors().configurationSource(c -> {
            CorsConfiguration corsConfig = new CorsConfiguration();
            corsConfig.addAllowedOriginPattern( "http://localhost:3030" );
            corsConfig.addAllowedHeader("*");
            corsConfig.addAllowedMethod(HttpMethod.GET);
            corsConfig.addAllowedMethod(HttpMethod.POST);
            corsConfig.addAllowedMethod(HttpMethod.PATCH);
            corsConfig.addAllowedMethod(HttpMethod.DELETE);
            return corsConfig;
        })
        .and()
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.POST, "/api/v1/auth/token").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/api/v1/**").authenticated();
            auth.requestMatchers(HttpMethod.POST, "/api/v1/**").authenticated();
            auth.requestMatchers(HttpMethod.PATCH, "/api/v1/**").authenticated();
            auth.requestMatchers(HttpMethod.DELETE, "/api/v1/**").authenticated();
        })
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .userDetailsService(userService)
        .logout(logout -> logout
            .logoutUrl("/api/v1/logout")
            .invalidateHttpSession(true)
        )
        .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeyProperties.publicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(rsaKeyProperties.publicKey()).privateKey(rsaKeyProperties.privatekey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
}
