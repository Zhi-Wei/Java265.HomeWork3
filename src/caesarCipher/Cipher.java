package caesarCipher;

public class Cipher {
	private int key;

	public Cipher() {
		this(3);
	}

	public Cipher(int key) {
		int maxValue = (int) Character.MAX_VALUE;
		if (key > maxValue) {
			key = (key % maxValue) - 1;
		}
		this.key = key;
	}

	public String encrypt(String plainText) {
		char[] plains = plainText.toCharArray();
		String result = "";
		for(char c : plains) {
			result += (char) (c + this.key);
		}
		return result;
	}

	public String decrypt(String cipherText) {
		char[] plains = cipherText.toCharArray();
		String result = "";
		for(char c : plains) {
			result += (char) (c - this.key);
		}
		return result;
	}
}