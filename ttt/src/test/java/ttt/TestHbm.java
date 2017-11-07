package ttt;


import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestHbm {
	private Session session ;
	private Transaction tx ;
	
	@Before
	public void before(){
		Configuration cfg = new Configuration();
        cfg.configure();//省略参数，默认文件名称：hibernate.cfg.xml	
        
        //2.创建SessionFactory对象  
        ServiceRegistryBuilder srb = new ServiceRegistryBuilder() ;
        srb.applySettings(cfg.getProperties());
        ServiceRegistry serviceRegistry =  srb.buildServiceRegistry() ;
        SessionFactory sessionFactory = cfg.buildSessionFactory((org.hibernate.service.ServiceRegistry) serviceRegistry);
        
        //3.创建Session对象
        session = sessionFactory.openSession();
        
        
        //4.创建事务对象   collection.setAutoCommit(false);
        tx = session.beginTransaction();
	}
	
	
	@After
	public void after(){
		//提交事务
		tx.commit();
			
		//关闭链接
		session.close();
	}
	

	@Test
	public void testSave() {
	        
        //1.加载配置文件
        Configuration cfg = new Configuration();
        cfg.configure();//省略参数，默认文件名称：hibernate.cfg.xml	
        
        //2.创建SessionFactory对象  
        ServiceRegistryBuilder srb = new ServiceRegistryBuilder() ;
        srb.applySettings(cfg.getProperties());
        ServiceRegistry serviceRegistry = srb.buildServiceRegistry() ;
        SessionFactory sessionFactory = cfg.buildSessionFactory((org.hibernate.service.ServiceRegistry) serviceRegistry);
        
        //3.创建Session对象
        Session session = sessionFactory.openSession();
        
        
        //4.创建事务对象   collection.setAutoCommit(false);
        Transaction tx = session.beginTransaction();
        
        //5.保存
        /*New stu = new New(1,"zhangsan");*/
        New stu2 = new New(5,"快马鹤鹤");
       
        /*session.save(stu);*/
        session.save(stu2);
        System.out.println(session.isConnected());
        
        
        //6.提交事务
        tx.commit();
        
        //7.关闭对象
        session.close();//必须关闭
       sessionFactory.close();//一般不需要手动关闭
	}
	
	
	@Test
	public void testCache(){
		
		New student = (New)session.get(New.class, 1);
		System.out.println(student);
		
		System.out.println("------------------------------------");
		
		student  = (New)session.get(New.class, 2);
		System.out.println(student);
		student=new New(1, "11");
		Serializable save = session.save(student);
		System.out.println(save);
	}
}
