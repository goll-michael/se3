package server;

import static common.CommandConstants.CMD_CREATE_PREFIX;
import static common.CommandConstants.CMD_CLOSE;
import static common.CommandConstants.CMD_GETCUSTOMERNAME_PREFIX;
import static common.CommandConstants.CMD_GETNEXTNUMBER;
import static common.CommandConstants.FAILED;
import static common.CommandConstants.SUCCESS;
import static common.CommandConstants.SUCCESS_1_PARAM;
import static common.CommandConstants.getCommandArguments;

import java.util.List;

import server.application.Customer;
import server.application.CustomerContainer;

public class ServerProtokoll 
{
    public String processInput(String input) 
    {
        String output = null;
        
        if(input.startsWith(CMD_CREATE_PREFIX))
        {
        	List<String> args = getCommandArguments(input);
        	if(args.size() == 2) 
        	{
        		CustomerContainer.getReference().create(args.get(1), Integer.parseInt(args.get(0)));
        	}
        	output = SUCCESS;
        }
        else if(input.equals(CMD_GETNEXTNUMBER.toString()))
        {
        	int number = CustomerContainer.getReference().getNextNumber();
        	output = String.format(SUCCESS_1_PARAM, number);
        }
        else if(input.startsWith(CMD_GETCUSTOMERNAME_PREFIX))
        {
        	output = SUCCESS;
        	List<String> args = getCommandArguments(input);
        	if(!args.isEmpty()) 
        	{
        		int number = Integer.parseInt(args.get(0));
        		Customer customer = new Customer();
        		customer.setName(CustomerContainer.getReference().getCustomerName(number));
        		customer.setNumber(number);
        		if(!"".equals(customer.getName()))
        		{
        			output = String.format(SUCCESS_1_PARAM, customer.getName());
        		}
        	}
        }
        else if(input.equals(CMD_CLOSE))
        {
        	CustomerContainer.getReference().close();
        	output = CMD_CLOSE;
        }    
        else
        {
        	output = FAILED;
        }
        	
        return output;
    }
}
