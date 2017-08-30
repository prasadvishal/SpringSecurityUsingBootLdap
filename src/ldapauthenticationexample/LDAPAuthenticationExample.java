/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldapauthenticationexample;

import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 *
 * @author Vishal Prasad
 */
public class LDAPAuthenticationExample {

    /**
     * @param args the command line arguments
     */
	public static void main(String[] args) {
		String url = "ldap://localhost:10389";
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, url);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
		env.put(Context.SECURITY_CREDENTIALS, "secret");

		try {
			DirContext ctx = new InitialDirContext(env);
			System.out.println("connected");
			System.out.println(ctx.getEnvironment());
                        
                        System.out.println("\n\n"+ctx.getAttributes("uid=user1,ou=users,ou=system"));
			
			// do something useful with the context...
                        Attributes userdata = ctx.getAttributes("uid=user1,ou=users,ou=system");
                        userdata.get("cn");
                        System.out.println("\n\n"+userdata.get("cn")+" "+userdata.get("sn")+" "+userdata.get("userPassword"));
			ctx.close();

		} catch (AuthenticationNotSupportedException ex) {
			System.out.println("The authentication is not supported by the server");
		} catch (AuthenticationException ex) {
			System.out.println("incorrect password or username");
		} catch (NamingException ex) {
			System.out.println("error when trying to create the context");
		}
	}
		
}