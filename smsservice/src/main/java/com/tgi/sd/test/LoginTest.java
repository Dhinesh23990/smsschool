package com.tgi.sd.test;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LoginTest {
	
    public static void main(String[] args) {
        String userName = "AWUser1";
        Hashtable<String,String> env = new Hashtable<String,String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        //env.put(Context.PROVIDER_URL, "ldap://iowebapi.gabrielny.com:389/dc=gabriel,dc=com");
        env.put(Context.PROVIDER_URL, "ldap://iowebapi.gabrielny.com:389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "webapi_svc");
        env.put(Context.SECURITY_CREDENTIALS, "0nlin3$ervice");

        DirContext ctx = null;
        NamingEnumeration results = null;
        try {
            ctx = new InitialDirContext(env);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search("", "(objectclass=person)", controls);
            while (results.hasMore()) {
                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();
                Attribute attr = attributes.get("cn");
                String cn = (String) attr.get();
                System.out.println(" Person Common Name = " + attributes.get("cn"));
                System.out.println(" Person Display Name = " + attributes.get("displayName"));
                System.out.println(" Person logonhours = " + attributes.get("logonhours"));
                System.out.println(" Person MemberOf = " + attributes.get("memberOf"));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (Exception e) {
                }
            }
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (Exception e) {
                }
            }
        }
    }
    
}
	

