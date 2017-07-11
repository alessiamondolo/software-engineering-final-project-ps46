package it.polimi.ingsw.ps46.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ReadInput {
	
	private InputStreamReader inputReader = new InputStreamReader(System.in);
	private BufferedReader input = new BufferedReader(inputReader);
	
	
	public ReadInput() {
		
	}
	
	public int IntegerFromConsole(int lowerNumber, int higherNumber) {
		boolean isValid = false;
		int consoleInput = 0;
		//the first number has to be the smallest
		if(lowerNumber > higherNumber) {
			int temp = lowerNumber;
			lowerNumber = higherNumber;
			higherNumber = temp;
		}
		do {
			try {
				consoleInput = Integer.parseInt(input.readLine());
			} catch (NumberFormatException e) {
				System.out.println("The input is not valid, please try again.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(consoleInput>=lowerNumber && consoleInput <= higherNumber)
				isValid = true;
		}
		while(!isValid);
		return consoleInput;
	}

	public String stringFromConsole() {
		String consoleInput = "";
		do {
			try {
				consoleInput = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		while(consoleInput.equals(""));
		return consoleInput;
	}
	
}
