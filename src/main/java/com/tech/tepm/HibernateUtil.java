package com.tech.tepm;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

import com.tech.entity.User;

public class HibernateUtil {
	private SessionFactory sessionFactory;
	
	
	public String driverClass;
	public String url;
	public String username;
	public String pass;
	public String dialect;
	public String ddl;
	
	
	public HibernateUtil(String driverClass, String url, String username, String pass, String dialect, String ddl) {		
		Properties prop = new Properties();
		prop.setProperty("hibernate.connection.driver_class", driverClass);
		prop.setProperty("hibernate.connection.url", url);
		prop.setProperty("hibernate.connection.username", username);
		prop.setProperty("hibernate.connection.password", pass);
		prop.setProperty("hibernate.dialect", dialect);
		prop.setProperty("hibernate.hbm2ddl.auto", ddl);
		prop.setProperty("hibernate.default_schema", username);
		
				
		sessionFactory = new Configuration().addPackage("com.tech.entity").addProperties(prop).addAnnotatedClass(User.class).buildSessionFactory();
		
		
	}
	
	public static void main(String...args){
		HibernateUtil hUtil = new HibernateUtil("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@192.168.60.62:1521:xe", "Milos_test", "milos", "org.hibernate.dialect.Oracle10gDialect", "update");
		Session session = hUtil.sessionFactory.openSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, new Integer(1));
		System.out.println(user.getName());
		User u = new User();
		u.setName("Miki1");
		u.setPass("Miki1");
		session.save(u);		
		session.getTransaction().commit();
		System.out.println("Created user with name: " + u.getName());
		session.close();
	}
	
	
	
	
	
	
	

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public String getDdl() {
		return ddl;
	}

	public void setDdl(String ddl) {
		this.ddl = ddl;
	}
	
	
	
	

}
