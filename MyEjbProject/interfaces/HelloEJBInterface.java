package interfaces;

import javax.ejb.Remote;

@Remote
public interface HelloEJBInterface {

	public String sayHello();
	
}
