/*-+-+-
 * Created by Jan Pavlik(pavlikj3 at seznam.cz) on 05-01-2008.
 * Licence LGPLv3(see:  http://www.gnu.org/licenses/lgpl-3.0.html).
 * This file is part of sokolovo framework project(http://duklan.org).
 * 
 * Enjoy my code style.
 */
package cz.pavlikj3.delivery.console;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

import cz.pavlikj3.delivery.core.dao.PackageDao;
import cz.pavlikj3.delivery.core.dao.PostalOfficeDao;
import cz.pavlikj3.delivery.core.dto.Package;
import cz.pavlikj3.delivery.core.dto.PostalOffice;
import cz.pavlikj3.delivery.core.utils.SpringUtil;

@Component
public class Delivery  implements CommandMarker 
{
	@Autowired
	private PostalOfficeDao postalOfficeDao;

	@Autowired
	private PackageDao packageDao;

	@CliCommand(value = "run", help = "Run it")
	public void run() throws IOException, InterruptedException
	{
		SpringUtil.getApplicationContext().getBean(PostOfficeThread.class).start();
		Thread.sleep(5000);
		
		PostalOffice postalOffice = postalOfficeDao.newDto();
		postalOffice.setPostalCode("28601");
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

	}
}
