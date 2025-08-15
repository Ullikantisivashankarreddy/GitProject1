package com.nit;
import com.nit.service.PurchaseOrderImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.nit.service.IpurchseOrder;

@SpringBootApplication
public class SpringBootMail_01 {

    SpringBootMail_01(PurchaseOrderImpl purchaseservice) {
    }

	public static void main(String[] args) {
	ApplicationContext ctx=	SpringApplication.run(SpringBootMail_01.class, args);
	
	IpurchseOrder order=ctx.getBean("purchaseservice",IpurchseOrder.class);
	try {
		String msg=order.purchse(new String[] {"shirt","trouser","watch"},
												new double[] {5000,3000,2000},
												new String[] {"shivashankarreddy444@gmail.com","shivashankar9704587985@gmail.com","sullikan@gitam.in"});
		System.out.println(msg);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	((ConfigurableApplicationContext)ctx).close();
	}

}
