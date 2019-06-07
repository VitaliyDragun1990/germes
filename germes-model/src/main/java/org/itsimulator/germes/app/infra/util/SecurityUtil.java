package org.itsimulator.germes.app.infra.util;

import java.nio.charset.Charset;

import com.google.common.hash.Hashing;

/**
 * Security and encryption-related routines
 * 
 * @author Vitaly Dragun
 *
 */
public class SecurityUtil {

	private SecurityUtil() {
	}

	/**
	 * Encryptes source text using SHA-256 encoding
	 * @param source source text to encrypt
	 * @return
	 */
	public static String encryptSHA(String source) {
		return Hashing.sha256().hashString(source, Charset.forName("UTF-8")).toString();
	}

}
