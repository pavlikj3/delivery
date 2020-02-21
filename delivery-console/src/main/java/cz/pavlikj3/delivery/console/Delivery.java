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

import cz.pavlikj3.delivery.core.business.PackageBll;

@Component
public class Delivery  implements CommandMarker 
{
	@Autowired
	private PackageBll packageBll;

	@CliCommand(value = "run", help = "Run it")
	public void scheduler() throws IOException
	{
		packageBll.listPostOffices(System.out);
	}
}
