/*
 * References:-
[1] Title- JCE Encryption - Data Encryption Standard (DES) Tutorial
     Author -mykong
     Date:-February 25th 2009
     Updated: May 16th 2009
     URL:http://www.mkyong.com/java/jce-encryption-data-encryption-standard-des-tutorial/
[2]Title- Encrypt/Decrypt String with DES
     Author:-Byron Kiourtzoglou
     Date:November 11,2012
      URL:https://examples.javacodegeeks.com/core-java/crypto/encrypt-decrypt-string-with-des/
[3]Title- UTF-8 byte[] to String
   Author:-Jason Nichols
   Date:December 14, 2011
URL:http://stackoverflow.com/questions/8512121/utf-8-byte-to-string
[4]Title:Problems converting byte array to string and back to byte array
Author: Joni Salonen
Date:February 1 2012
URL:http://stackoverflow.com/questions/9098022/problems-converting-byte-array-to-string-and-back-to-byte-array
[5]Title:Converting Key to String and back to Key Java 
Author:Keith Randall
Date:Sept 6th 2012
URL:http://stackoverflow.com/questions/12292389/converting-key-to-string-and-back-to-key-java
 */

package DatabaseConnection;

import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Native;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Scanner;
import java.security.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.omg.IOP.Encoding;

public class Encryption {

	public String encryptAdmin(String userName, String password)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchProviderException,
			UnsupportedEncodingException {
		/*
		 * Reference:http://stackoverflow.com/questions/9098022/problems-converting
		 * -byte-array-to-string-and-back-to-byte-array
		 */
		byte userBytes[] = userName.getBytes("ISO-8859-1");
		String encodedUser = Base64.getEncoder().encodeToString(userBytes);
		byte dss[] = new byte[16];
		byte array[] = Base64.getDecoder().decode(encodedUser);

		for (int i = 0; i < 16; i++) {
			if (i < userName.length()) {
				dss[i] = array[i];
			} else {
				dss[i] = 0;
			}
		}

		SecretKey k = new SecretKeySpec(dss, 0, dss.length, "AES");

		// SecretKey l1=sharedKey.generateKey();
		Cipher ss = Cipher.getInstance("AES");
		byte[] passwordinBytes = password.getBytes("ISO-8859-1");
		ss.init(Cipher.ENCRYPT_MODE, k);
		byte hashedPassword[] = ss.doFinal(passwordinBytes);
		/*
		 * Reference
		 * http://stackoverflow.com/questions/9098022/problems-converting
		 * -byte-array-to-string-and-back-to-byte-array
		 */
		String hashString = new String(hashedPassword, "ISO-8859-1");

		return hashString;

	}

	public String decryptUser(String user, String hashedPassword)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException,
			NoSuchProviderException {
		/*
		 * Reference
		 * http://stackoverflow.com/questions/9098022/problems-converting
		 * -byte-array-to-string-and-back-to-byte-array
		 */

		byte userBytes[] = user.getBytes("ISO-8859-1");
		String encodedUser = Base64.getEncoder().encodeToString(userBytes);
		byte dss[] = new byte[16];
		byte array[] = Base64.getDecoder().decode(encodedUser);

		for (int i = 0; i < 16; i++) {
			if (i < user.length()) {
				dss[i] = array[i];
			} else {
				dss[i] = 0;
			}
		}

		SecretKey k = new SecretKeySpec(dss, 0, dss.length, "AES");
		Cipher ss = Cipher.getInstance("AES");
		ss.init(Cipher.DECRYPT_MODE, k);
		/*
		 * Reference
		 * :http://stackoverflow.com/questions/9098022/problems-converting
		 * -byte-array-to-string-and-back-to-byte-array
		 */
		byte newbyte11[] = hashedPassword.getBytes("ISO-8859-1");
		byte a11[] = new byte[16];
		for (int i = 0; i < 16; i++) {
			if (i < hashedPassword.getBytes().length) {
				a11[i] = hashedPassword.getBytes()[i];
			} else {
				a11[i] = 0;
			}
		}

		byte decrypt11[] = ss.doFinal(newbyte11);
		/*
		 * Reference :
		 * http://stackoverflow.com/questions/9098022/problems-converting
		 * -byte-array-to-string-and-back-to-byte-array
		 */
		String mainString11 = new String(decrypt11, "ISO-8859-1");

		return mainString11;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException,
			UnsupportedEncodingException, NoSuchProviderException {
		

	}
}
