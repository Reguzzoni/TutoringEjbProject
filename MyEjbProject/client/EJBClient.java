package client;

import java.util.Hashtable;
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

		} catch (NamingException e) {

			e.printStackTrace();

		}

		return bean;

	}


	public static Context getInitialContext() throws NamingException {

		if (initialContext == null) {

			// Properties extends HashTable

			/**
			 * TEST1
			Properties prop = new Properties();            
			prop.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);            
			prop.put(Context.URL_PKG_PREFIXES, JNP_INTERFACES);            
			prop.put(Context.PROVIDER_URL, PROVIDER_URL); */ 
			

			final Properties prop = new Properties();
			prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			prop.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			
		    // create a context passing these properties
			initialContext = new InitialContext(prop);

		}

		return initialContext;

	}
}