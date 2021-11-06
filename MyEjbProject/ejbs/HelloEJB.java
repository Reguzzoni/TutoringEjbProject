package ejbs;

import interfaces.HelloEJBInterface;

public class HelloEJB implements HelloEJBInterface{
	
	@Override
	public String sayHello() {

		return "HELLO WORLD FROM EJB PROJECT!";
	}
}