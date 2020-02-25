package cz.pavlikj3.delivery.core.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.pavlikj3.delivery.core.dao.PackageDao;
import cz.pavlikj3.delivery.core.dao.PostalOfficeDao;
import cz.pavlikj3.delivery.core.dto.Package;
import cz.pavlikj3.delivery.core.dto.PostalOffice;
import cz.pavlikj3.delivery.core.exception.ValidationException;
import cz.pavlikj3.delivery.core.utils.StringUtil;

@Component
public class PackageBll 
{	
	private static final Logger log = Logger.getLogger(PackageBll.class);
	
	@Autowired
	private PostalOfficeDao postalOfficeDao;

	@Autowired
	private PackageDao packageDao;

	// for performance - 
	private static Pattern p = Pattern.compile("^([0-9]+\\.?[0-9]*)[ ]+([0-9]{5})$");
	
	/**
	 * Write to stream postal office with sum weight of packages
	 * @param os
	 * @throws IOException
	 */
	public void listPostOffices(OutputStream os) throws IOException
	{
		List<PostalOffice> postalOffices =  postalOfficeDao.findListBySf(null);
		for (PostalOffice postalOffice : postalOffices)
		{
			double sum = packageDao.findListBySf(packageDao.newSf().setPostalOfficeEquals(postalOffice)).stream().map(dto -> dto.getWeight()).mapToDouble(Double::doubleValue).sum();
			os.write(String.format("%05d %.3f\n", postalOffice.getPostalCode(), sum).getBytes());
		}
	}
	
	public void parseAllLines(InputStream is)
	{
		InputStreamReader ir = null;
		BufferedReader reader = null;
		try
		{
			ir = new InputStreamReader(is);
			reader = new BufferedReader(ir);
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				parseLine(line);
			}
			is.close();
		}
		catch (IOException ex)
		{
			throw new RuntimeException(ex);
		}
		finally 
		{
			if (ir != null)
			{
				try
				{
					ir.close();
				}
				catch (IOException ex)
				{
					
				}
			}
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (IOException ex)
				{
					
				}
			}

		}
	}
	
	public void parseLine(String line)
	{
		log.info(String.format("Trying to parse user input line: '%s'", line));
		/*
	Sample input: 

		3.4 08801

		2 90005

		12.56 08801

		5.5 08079 

		3.2 09300

		 

		Input line format:

		<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space><postal code: fixed 5 digits> 
		*/
		if (StringUtil.isEmpty(line)) // empty line is not error
		{
			return;
		}
		Matcher m = p.matcher(line);
		if (!m.matches())
		{
			throw new ValidationException("Input line has bad format");
		}
		double weight = Double.parseDouble(m.group(1));
		int po = Integer.parseInt(m.group(2));
		
		
		PostalOffice postalOffice = postalOfficeDao.findBySf(postalOfficeDao.newSf().setPostalCodeEquals(po));
		if (postalOffice == null)
		{
			postalOffice = postalOfficeDao.newDto();
			postalOffice.setPostalCode(po);
			postalOffice = postalOfficeDao.save(postalOffice);			
		}
		
		Package pack = packageDao.newDto();
		pack.setPostalOffice(postalOffice);
		pack.setWeight(weight);
		packageDao.save(pack);
	}
}
