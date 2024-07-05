package org.example.signupwithsecurity.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.signupwithsecurity.entity.User;
import org.example.signupwithsecurity.repository.UserInfoRepository;
import org.example.signupwithsecurity.repository.UserRepository;
import org.example.signupwithsecurity.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private JwtService jwtService;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserRepository userRepository;

    @Value("${frontend.url}")
    private String frontendUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
//        if("github".equals(authenticationToken.getAuthorizedClientRegistrationId())) {
//            DefaultOAuth2User principal = (DefaultOAuth2User) authenticationToken.getPrincipal();
//            Map<String, Object> attributes = principal.getAttributes();
//            String username = attributes.get("login").toString();
//            userRepository.findByUsername(username).ifPresentOrElse(user -> {
//
//            }, () -> {
//
//            });
//
//
//        }
        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl(frontendUrl);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
