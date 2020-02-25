package cz.pavlikj3.delivery.console;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.pavlikj3.delivery.core.business.PackageBll;

@Component
public class PostOfficeThread extends Thread
{
	private static final Logger log = Logger.getLogger(PostOfficeThread.class);
	
	private boolean stop;
	
	@Autowired
	private PackageBll packageBll;
	
	@Override
	public void run() 
	{
		while (!stop)
		{
			try
			{
				System.out.println("");
				System.out.println("________________");
				System.out.println("PC   |Weight sum");
				packageBll.listPostOffices(System.out);
			}
			catch (IOException ex)
			{
				log.error(ex);
			}
			try 
			{
				Thread.sleep(10000);
			} 
			catch (InterruptedException e) 
			{
				log.error(e);
			}
		}
	}

	public void setStop() 
	{
		this.stop = true;
	}
	
	
}
