package io.robusta.business;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class EtagBusiness {

	public String getFastEtag(Object... inputs) {

		
		int initialCapacity = 0;
		for (Object input : inputs) {
			initialCapacity += String.valueOf(input).length();
		}
		StringBuilder builder = new StringBuilder(initialCapacity);
		for (Object  input : inputs) {
			builder.append(String.valueOf(input));
		}
		
		final java.nio.ByteBuffer buf = java.nio.charset.StandardCharsets.UTF_8
				.encode(builder.toString());

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA1");
			buf.mark();
			digest.update(buf);
			buf.reset();
			return DatatypeConverter.printHexBinary(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
