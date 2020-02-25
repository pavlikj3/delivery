/*-+-+-
 * Created by Jan Pavlik(pavlikj3 at seznam.cz) on 05-01-2008.
 * Licence LGPLv3(see:  http://www.gnu.org/licenses/lgpl-3.0.html).
 * This file is part of sokolovo framework project(http://duklan.org).
 * 
 * Enjoy my code style.
 */
package cz.pavlikj3.delivery.console;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cz.pavlikj3.delivery.core.business.PackageBll;
import cz.pavlikj3.delivery.core.exception.ValidationException;

@Component
public class Delivery  implements CommandMarker 
{
	private static final Logger log = Logger.getLogger(Delivery.class);
	@Autowired
	private PackageBll packageBll;

	@Autowired
	private PostOfficeThread postOfficeThread;
	
	@CliCommand(value = "run", help = "Run it")
	public void run( @CliOption(key= {"import"}) String fileName) throws IOException, InterruptedException
	{
		try
		{
			log.info("Starting");
			
			if (!StringUtils.isEmpty(fileName))
			{
				log.info(String.format("Importing data from file: '%s'", fileName));
				packageBll.parseAllLines(new FileInputStream(fileName));
			}
			postOfficeThread.start();
			
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
				line = reader.readLine();
			}
			postOfficeThread.setStop();
			System.out.print("Exiting");
		}
		catch (Exception ex)
		{
			System.err.println(String.format("Exiting with error: '%s'",ex.getMessage()));
			log.error(ex);
			System.exit(1);
		}
	}
}
