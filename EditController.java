package demo.getting_started.input;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.*;

public class EditController extends GenericForwardComposer<Component> {

	private static final long serialVersionUID = 1L;

	private Intbox input;

	private Vbox output;
    
	private int randomNumber;
	private int digits;
	private int cows;
	private int bulls;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        // Retrieve query parameters
        String numDigitsParam = Executions.getCurrent().getParameter("digits");
        String distinctNumberParam = Executions.getCurrent().getParameter("number");

        // Convert query parameters to integers
        if (numDigitsParam != null && distinctNumberParam != null) {
        	digits = Integer.parseInt(numDigitsParam);
        	randomNumber = Integer.parseInt(distinctNumberParam);
        }
        
    }
	
	public void onClick$sendMessage() {
		
        int userInput = input.getValue();
        
        System.out.println(digits);
        
        String response1 = "" + userInput;
        Component message1 = new Label(response1);
        output.appendChild(message1);
        
        String outResponse="";
        
//        if(userInput == randomNumber) {
//        	outResponse = "You won!! Congrats my dear";
//        }else{
//        	for()
//        }
        
        
        
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