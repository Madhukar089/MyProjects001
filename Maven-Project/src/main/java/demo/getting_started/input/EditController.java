package demo.getting_started.input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Vbox;

public class EditController extends GenericForwardComposer<Component> {

	private static final long serialVersionUID = 1L;

	private Intbox input;

	private Vbox output;

	private int randomNumber;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		if (Sessions.getCurrent().getAttribute("distinctNumber") != null) {
			randomNumber = (int) Sessions.getCurrent().getAttribute("distinctNumber");
		}

	}

	public void onClick$sendMessage() {

		int cows = 0;
		int bulls = 0;
		int userInput = input.getValue();

		String response1 = "" + userInput;
		Component message1 = new Label(response1);
		output.appendChild(message1);

		if (userInput == randomNumber) {
			Executions.sendRedirect("Congrats.zul");
		} else {
			ArrayList<Integer> userNumber = generateArrayList(userInput);
			ArrayList<Integer> resultNumber = generateArrayList(randomNumber);

			if (userNumber.size() != resultNumber.size()) {
				throw new IllegalArgumentException("The number should be of " + resultNumber.size() + " size");
			}

			if (!areAllDistinct(userNumber)) {
				throw new IllegalArgumentException(
						"The number should be distinct i.e., there should be no repeating digits");
			}

			// Iterate through the elements of the first ArrayList
			for (int i = 0; i < userNumber.size(); i++) {
				int numToCompare = userNumber.get(i);

				// Check if the number exists in the second ArrayList
				if (resultNumber.contains(numToCompare)) {
					int indexInSecondArrayList = resultNumber.indexOf(numToCompare);

					// Compare positions
					if (i == indexInSecondArrayList) {
						bulls += 1;
					} else {
						cows += 1;
					}
				}
			}
		}

		// Generate a response
		String response2 = "There are " + cows + " Cows and " + bulls + " Bulls";

		// Display the response in the chat area
		Component message2 = new Label(response2);
		output.appendChild(message2);

		// Clear the user input
		input.setValue(null);
	}

	private ArrayList<Integer> generateArrayList(int number) {
		int temp = number;
		ArrayList<Integer> intArrayList = new ArrayList<Integer>();
		do {
			intArrayList.add(temp % 10);
			temp /= 10;
		} while (temp > 0);

		Collections.reverse(intArrayList);

		return intArrayList;
	}

	private boolean areAllDistinct(ArrayList<Integer> userNumber) {
		Set<Integer> distinctSet = new HashSet<>();
		for (Integer num : userNumber) {
			if (distinctSet.contains(num)) {
				return false; // Not all numbers are distinct
			}
			distinctSet.add(num);
		}
		return true; // All numbers are distinct
	}

}