/*-+-+-
 * Created by Jan Pavlik(pavlikj3 at seznam.cz) on 26.9.2015.
 * Licence LGPLv3(see:  http://www.gnu.org/licenses/lgpl-3.0.html).
 * This file is part of sokolovo framework project(http://duklan.org).
 * 
 * Enjoy my code style.
 */
package cz.pavlikj3.delivery.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware
{

	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		SpringUtil.applicationContext = applicationContext;
		
	}

	public static ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

}
