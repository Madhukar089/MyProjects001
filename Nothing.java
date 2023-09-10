package demo.getting_started.input;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class Nothing extends GenericForwardComposer<Component>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Intbox numberInput;

	public void onClick$startButton() {
		int numDigits = numberInput.getValue();
		if (numDigits >= 3 && numDigits <= 7) {
            int distinctNumber = generateDistinctNumber(numDigits);
            System.out.println(distinctNumber);
            // Pass the random number as a parameter to the new page
            Executions.sendRedirect("demo.zul?number=" + distinctNumber + "&digits=" +numDigits);
        } else {
        	throw new IllegalArgumentException("Number of digits must be between 3 and 7.");
        }
	}
	
	private static int generateDistinctNumber(int numDigits) {
        Random rand = new Random();
        Set<Integer> usedDigits = new HashSet<>();
        int number = 0;

        for (int i = 0; i < numDigits; i++) {
            int digit;
            do {
                digit = rand.nextInt(10);
            } while (usedDigits.contains(digit));
            usedDigits.add(digit);
            number = number * 10 + digit;
        }

        return number;
    }
	
}