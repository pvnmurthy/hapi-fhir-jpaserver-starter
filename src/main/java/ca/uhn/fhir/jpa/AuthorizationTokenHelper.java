package ca.uhn.fhir.jpa.starter.utils;

import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;
import org.springframework.stereotype.Component;

import ca.uhn.fhir.jpa.starter.AppProperties;

@Component
public class AuthorizationTokenHelper {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AuthorizationTokenHelper.class);


	private AppProperties appProperties;
	
		
	public AuthorizationTokenHelper()
	{
		super();
	}

	public JwtClaims checkAuthorizationAndGetClaims(String encodedAccessToken)
			throws Exception {

			HttpsJwks httpsJkws = new HttpsJwks(appProperties.getAs_certs_token_uri());
			System.out.println("flow came here....1");
			HttpsJwksVerificationKeyResolver httpsJwksKeyResolver = new HttpsJwksVerificationKeyResolver(httpsJkws);
			JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime() // the JWT must have an expiration
																							// time
					.setAllowedClockSkewInSeconds(180) // allow some leeway in validating time based claims to account for
														// clock skew
					// .setRequireSubject() // the JWT must have a subject claim
					.setExpectedIssuer(appProperties.getAs_expected_issuer()) // whom the JWT needs to have been
																				// issued by
					//.setExpectedAudience(appProperties.getAs_expected_audience()) // to whom the JWT is intended for
					.setVerificationKeyResolver(httpsJwksKeyResolver).build();
			
			try {
				// Validate the JWT and process it to the Claims
				JwtClaims jwtClaims = jwtConsumer.processToClaims(encodedAccessToken);
				System.out.println("flow came here....4");
				System.out.println("JWT validation succeeded! " + jwtClaims);
				return jwtClaims;
			} catch (InvalidJwtException e) {
				System.out.println("flow came here....5");
				logger.error("Invalid JWT! ", e);
				System.out.println(e.getMessage());
				throw e;
			}
	}

}
