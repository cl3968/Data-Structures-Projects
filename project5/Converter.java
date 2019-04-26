package project5;

/**
 * The Converter class implements various methods for converting numbers written 
 * using different number systems: binary, decimal, hexadecimal. 
 * The binary system uses two symbols and uses powers of the base 2.
 * The decimal system uses ten symbols and uses powers of the base 10.
 * The hexadecimal system uses sixteen symbols and uses powers of the base 16.
 * The decimal numbers are represented using int type. 
 * The binary and hexadecimal numbers are represented using binary and hexadecimal strings.
 * It has four methods in total: binaryToDecimal, binaryToHex, decimalToBinary, and hexToBinary.
 * 
 * 
 * 
 * @author Christina Liu 
 *
 **/

public class Converter {


	/**
	 * Converts binary strings to hexadecimal strings with the same value. 
	 * @param the binary sequence to be converted
	 * @returns the hexadecimal string equal in value to the binary string passed 
	 * as the parameter or null if the binary string is not valid
	 **/

	public static String binaryToHex​(String binary) {

		//checking if binary exceeds length
		if (binary.length() > 33) return null;

		//checking if binary is valid with a leading "0b"
		if (binary.substring(0,2).equals("0b")) {

			//getting rid of the leading "0b"
			binary = binary.substring(2);

			//checking if binary is valid after removing "0b"
			if (binary.length() ==  0) return null;
			for (int i = 0; i < binary.length(); i++) {
				if (binary.charAt(i) != '0' &&  binary.charAt(i) != '1') {
					return null;
				}
				else 
					continue;
			}

			//converting binary to hexadecimal and adding a leading "0x"
			//call helper method
			return "0x" + binaryToHex​(binary, 0);	
		}
		else 
			return null;	
	}

	/**
	 * Helper/Recursive Method to binaryToHex(String binary) 
	 * @param the binary sequence to be converted and the integer index of the sequence
	 * @returns the hexadecimal string without the leading "0x"
	 **/

	private static String binaryToHex​(String binary, int index) {

		//padding the binary with leading 0's
		if (binary.length() %4 !=0) {
			binary = "0" + binary;
			return binaryToHex​(binary, 0);
		}
		else {
			String digits = "0123456789ABCDEF";
			if (index < binary.length()) {

				//converting binary to decimal 
				int decimal = binaryToDecimal​("0b" + binary.substring(index,index+4));

				//converting decimal to hexadecimal character and then recursively calling the function
				return digits.charAt(decimal) + binaryToHex​(binary, index+4);
			}
			return "";
		}
	}

	/**
	 * Converts hexadecimal strings to binary strings with the same value. 
	 * @param the hexadecimal string to be converted
	 * @returns the binary string equal in value to the hexadecimal string 
	 * passed as the parameter or null if the hexadecimal string is not valid
	 **/

	public static String hexToBinary​(String hex) {

		//check if hexadecimal is valid 
		if (hex.length() > 8) return null;
		
		//check if hexadecimal has leading "0x"
		if (hex.substring(0,2).equals("0x")) {
			hex = hex.substring(2);
			if (hex.length()==0) return null;
			
			//check if hexadecimal has valid symbols
			for (int i = 0; i < hex.length(); i++) {
				String digits = "0123456789ABCDEF";
				if (digits.indexOf(hex.charAt(i)) == -1) {
					return null;
				}
				else 
					continue;
			}

			//call helper method
			return "0b" + hexToBinary​(hex, 0);	
		}
		else 
			return null;		
	}

	/**
	 * Helper/Recursive Method to hexToBinary(String hex) 
	 * @param the hexadecimal sequence to be converted and the integer index of the sequence
	 * @returns the binary sequence string without the leading "0b"
	 **/

	private static String hexToBinary​(String hex, int index) {
		String digits = "0123456789ABCDEF";

		if (hex.length() > 0) {

			//converting first character of hexadecimal to decimal first
			int decimal = digits.indexOf(hex.charAt(index));

			//converting decimal to binary sequence
			String binary = decimalToBinary​(decimal).substring(3);

			//padding binary with leading 0's 
			while (binary.length() % 4 != 0) {
				binary = "0" + binary;
			}

			//getting rid of first character in hexadecimal
			return  binary + hexToBinary​(hex.substring(1), 0)  ;
		}
		else return "";
	}

	/**
	 * Converts decimal numbers to binary strings with the same value.
	 * @param the decimal integer to be converted
	 * @returns the binary string equal in value to the decimal number passed as 
	 * the parameter or null if the decimal is negative
	 **/

	public static String decimalToBinary​(int decimal) {

		//checking for valid decimal
		if (decimal < 0) return null;
		if (decimal == 0)  return "0b0";  

		//recursively adding 0's or 1's to binary sequence
		if (decimal%2 == 1) {
			return decimalToBinary​(decimal / 2) + "1"; 
		}
		else
			return decimalToBinary​(decimal / 2) + "0"; 

	}

	/**
	 * Converts binary strings to decimal numbers with the same value.
	 * @param the binary string to be converted
	 * @returns the decimal number equal in value to the binary string passed as the parameter 
	 * @throws IllegalArgumentException if the binary string passed to the function is invalid
	 */

	public static int binaryToDecimal​(String binary) throws IllegalArgumentException
	{
		//checking if binary sequence is valid 
		if (binary == null)
			throw new IllegalArgumentException("Illegal Binary: no binary");

		//checking for a leading "0b" for a valid binary sequence
		if (binary.substring(0,2).equals("0b")) {
			binary = binary.substring(2);

			//checking if binary sequence is valid
			if (binary.length() ==  0) 
				throw new IllegalArgumentException("Illegal Binary: no binary");
			if (binary.length() > 31) 
				throw new IllegalArgumentException("Illegal Binary: binary too long");
			
			//checking for valid symbols in binary sequence
			for (int i = 0; i < binary.length(); i++) {
				if (binary.charAt(i) != '0' &&  binary.charAt(i) != '1') {
					throw new IllegalArgumentException("Illegal Binary: binary has invalid symbols");
				}
				else 
					continue;
			}

			//call helper method
			return binaryToDecimal​(binary, binary.length()-1);

		}

		else 
			throw new IllegalArgumentException("Illegal Binary: binary does not start with 0b"); 
	}

	/**
	 * Helper/Recursive Method to binaryToDecimal(String binary) 
	 * @param the binary string to be converted and the integer power of 2
	 * @returns the decimal integer equal in value to the binary string passed as the parameter
	 **/

	private static int binaryToDecimal​(String binary, int pow) {

		//checking if binary sequence is valid
		if (binary.length() == 0) 
			return 0;

		//recursively adding corresponding powers of 2
		if (binary.charAt(0) == '1') {
			return (int) (Math.pow(2, pow) + 
					binaryToDecimal​(binary.substring(1), pow-1));
		}
		else return binaryToDecimal​(binary.substring(1), pow-1);
	}




}
