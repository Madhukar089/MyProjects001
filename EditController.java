package demo.getting_started.input;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.*;

public class EditController extends SelectorComposer<Window> {

	private static final long serialVersionUID = 1L;
	@Wire
	private Intbox input;
	@Wire
	private Vbox output;
    
	@Listen("onClick=#sendMessage")
	public void submit() {
		// Read user input
        int userInput = input.getValue();
        String response1 = "" + userInput;
        Component message1 = new Label(response1);
        
        output.appendChild(message1);
        int machineResponse = userInput * 2;

        // Generate a response
        String response2 = "What I will say is : " + machineResponse;

        // Display the response in the chat area
        Component message2 = new Label(response2);
        output.appendChild(message2);

        // Clear the user input
        input.setValue(null);
	}
	
}