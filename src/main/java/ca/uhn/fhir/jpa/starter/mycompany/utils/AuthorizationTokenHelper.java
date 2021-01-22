package ca.uhn.fhir.jpa.starter.mycompany.utils;

import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import ca.uhn.fhir.jpa.starter.AppProperties;

@Component
public class AuthorizationTokenHelper {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AuthorizationTokenHelper.class);

	@Autowired
	AppProperties appProperties;
	
	
	public AuthorizationTokenHelper()
	{
		super();
	}
	public void ReadNewYamlProperties()
	{
		System.out.println("flow came here....33: The value of cert token uri is:"+appProperties.getAs_certs_token_uri());
		System.out.println("flow came here....3333: The value of expected issuer is:"+appProperties.getAs_expected_issuer());
	}

}
