/*-+-+-
 * Created by Jan Pavlik(pavlikj3 at seznam.cz) on 05-01-2008.
 * Licence LGPLv3(see:  http://www.gnu.org/licenses/lgpl-3.0.html).
 * This file is part of sokolovo framework project(http://duklan.org).
 * 
 * Enjoy my code style.
 */
package cz.pavlikj3.delivery.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

import cz.pavlikj3.delivery.core.business.PackageBll;
import cz.pavlikj3.delivery.core.exception.ValidationException;
import cz.pavlikj3.delivery.core.utils.SpringUtil;
import jline.internal.Log;

@Component
public class Delivery  implements CommandMarker 
{
	private static final Logger log = Logger.getLogger(Delivery.class);
	@Autowired
	private PackageBll packageBll;

	@CliCommand(value = "run", help = "Run it")
	public void run() throws IOException, InterruptedException
	{
		SpringUtil.getApplicationContext().getBean(PostOfficeThread.class).start();
		
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(">>>>");
        String line = reader.readLine();
        
		while (!("quit".equalsIgnoreCase(line) || "exit".equalsIgnoreCase(line)))
		{			
			try
			{
				packageBll.parseLine(line);
			}
			catch (ValidationException ex)
			{
				System.out.println(ex.getMessage());
				log.info(ex);
			}
			System.out.print(">>>>");
			line = reader.readLine();
		}
		System.out.print("Exiting");
		/*
		Thread.sleep(5000);
		
		PostalOffice postalOffice = postalOfficeDao.newDto();
		postalOffice.setPostalCode(28601);
		postalOfficeDao.save(postalOffice);
		
		Thread.sleep(5000);
		Package pack = packageDao.newDto();
		pack.setPostalOffice(postalOffice);
		pack.setWeight(1.2);
		packageDao.save(pack);
		
		Thread.sleep(10000);
		Package pack1 = packageDao.newDto();
		pack1.setPostalOffice(postalOffice);
		pack1.setWeight(10.5);
		packageDao.save(pack1);
		*/
	}
}
