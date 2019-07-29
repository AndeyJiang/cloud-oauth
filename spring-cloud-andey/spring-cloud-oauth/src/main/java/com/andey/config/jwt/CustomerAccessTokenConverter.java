package com.andey.config.jwt;

import com.andey.entity.User;
import com.andey.service.UserService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;


public class CustomerAccessTokenConverter extends JwtAccessTokenConverter {
	public static final String TOKEN_USE_KEY = "user";
    @Autowired
	private UserService userService;
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
		if (authentication.getUserAuthentication() != null) {
				User user=userService.findByUserName(authentication.getUserAuthentication().getName());
				Map<String, Object> userMap = Maps.newHashMap();
				userMap.put("username", user.getUsername());
				userMap.put("authorities", user.getAuthorities());
				//userMap.put("email", user.getEmail());
				defaultOAuth2AccessToken.getAdditionalInformation().put(TOKEN_USE_KEY, userMap);

		}
		return super.enhance(defaultOAuth2AccessToken, authentication);
	}

	@Override
	public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
		OAuth2AccessToken oAuth2AccessToken = super.extractAccessToken(value, map);
		convertData(oAuth2AccessToken, oAuth2AccessToken.getAdditionalInformation());
		return oAuth2AccessToken;
	}

	private void convertData(OAuth2AccessToken oAuth2AccessToken, Map<String, Object> additionalInformation) {
		oAuth2AccessToken.getAdditionalInformation().put(TOKEN_USE_KEY, additionalInformation.get(TOKEN_USE_KEY));
	}
}
