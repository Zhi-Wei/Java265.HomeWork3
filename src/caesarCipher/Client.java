package caesarCipher;

public class Client {

	public static void main(String[] args) {
		String plainText = "Java";
		Cipher cipher = new Cipher();
		String cipherText = cipher.encrypt(plainText);
		String decipherText = cipher.decrypt(cipherText);
		System.out.printf("Plain Text: %s\nCipher Text: %s\nDecipher Text: %s", plainText, cipherText, decipherText);
	}

}
