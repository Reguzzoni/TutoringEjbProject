package client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import interfaces.HelloEJBInterface;

public class EJBClient
{

	private static final String LOOKUP_STRING = "HelloEJB/remote";

	/*     * location of JBoss JNDI Service provider the client will use. It should be     * URL string.     */
	private static final String PROVIDER_URL = "http-remoting://localhost:8080";

	/*     * specifying the list of package prefixes to use when loading in URL     * context factories. colon separated     */
	private static final String JNP_INTERFACES = "org.jboss.naming:org.jnp.interfaces";

	/*     * Factory that creates initial context objects. fully qualified class name.     */
	private static final String INITIAL_CONTEXT_FACTORY = "org.jnp.interfaces.NamingContextFactory";

	private static Context initialContext;


	public static void main(String[] args) {

		HelloEJBInterface bean = doLookup();

		System.out.println(bean.sayHello());

		// 3. Call business logic

	}

	private static HelloEJBInterface doLookup() {

		Context context = null;

		HelloEJBInterface bean = null;

		try {

			// 1. Obtaining Context

			context = getInitialContext();

			// 2. Lookup and cast

			bean = (HelloEJBInterface) context.lookup(LOOKUP_STRING);
			bean.sayHello();

		} catch (NamingException e) {

			e.printStackTrace();

		}

		return bean;

	}


	public static Context getInitialContext() throws NamingException {

		if (initialContext == null) {

			Properties prop = new Properties();            
 		
			prop.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
			prop.setProperty(Context.PROVIDER_URL,"remote://localhost:4447");
			
		    // create a context passing these properties
			initialContext = new InitialContext(prop);

		}

		return initialContext;

	}
}