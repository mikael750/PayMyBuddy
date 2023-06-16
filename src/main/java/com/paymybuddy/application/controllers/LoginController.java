package com.paymybuddy.application.controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.Map;

@RestController
public class LoginController {

    public LoginController(OAuth2AuthorizedClientService authorizedClientService) {
    }
    @RolesAllowed("USER")
    @RequestMapping("/**")
    public String getUser()
    {
        return "Welcome User";
    }

    @RolesAllowed({"USER","ADMIN"})
    @RequestMapping("/admin")
    public String getAdmin()
    {
        return "Welcome Admin";
    }
    /**
     * @param user
     * @return
     */
    @RolesAllowed("USER")
    @RequestMapping("/*")
    public String getUserInfo(Principal user) {
        StringBuilder userInfo= new StringBuilder();
        if(user instanceof UsernamePasswordAuthenticationToken){
            userInfo.append(getUsernamePasswordLoginInfo(user));
        }
        else if(user instanceof OAuth2AuthenticationToken){
            userInfo.append(getOauth2LoginInfo(user));
        }
        return userInfo.toString();
    }

    /**
     * @param user
     * @return
     */
    private StringBuffer getUsernamePasswordLoginInfo(Principal user)
    {
        StringBuffer usernameInfo = new StringBuffer();

        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
        if(token.isAuthenticated()){
            User u = (User) token.getPrincipal();
            usernameInfo.append("Welcome, ").append(u.getUsername());
        }
        else{
            usernameInfo.append("NA");
        }
        return usernameInfo;
    }

    /**
     * @param user
     * @return
     */
    private StringBuffer getOauth2LoginInfo(Principal user){

        StringBuffer protectedInfo = new StringBuffer();

        OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
        OAuth2User principal = ((OAuth2AuthenticationToken) user).getPrincipal();
        //OAuth2AuthorizedClient authClient = this.authorizedClientService.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());

        Map<String,Object> userAttributes = authToken.getPrincipal().getAttributes();

        protectedInfo.append("Welcome, ").append(userAttributes.get("name")).append("<br><br>");
        protectedInfo.append("e-mail: ").append(userAttributes.get("email")).append("<br><br>");
        //String userToken = authClient.getAccessToken().getTokenValue();
        //protectedInfo.append("Access Token: ").append(userToken).append("<br><br>");

        OidcIdToken idToken = getIdToken(principal);
        if(idToken != null) {

            //protectedInfo.append("idToken value: ").append(idToken.getTokenValue()).append("<br><br>");
            protectedInfo.append("Token mapped values <br><br>");

            Map<String, Object> claims = idToken.getClaims();

            for (String key : claims.keySet()) {
                protectedInfo.append("  ").append(key).append(": ").append(claims.get(key)).append("<br>");
            }
        }

        return protectedInfo;
    }

    /**
     * @param principal
     * @return
     */
    private OidcIdToken getIdToken(OAuth2User principal){
        if(principal instanceof DefaultOidcUser oidcUser) {
            return oidcUser.getIdToken();
        }
        return null;
    }
}
