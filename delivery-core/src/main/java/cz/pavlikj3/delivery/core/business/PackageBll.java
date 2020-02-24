package cz.pavlikj3.delivery.core.business;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cz.pavlikj3.delivery.core.dao.PackageDao;
import cz.pavlikj3.delivery.core.dao.PostalOfficeDao;
import cz.pavlikj3.delivery.core.dto.Package;
import cz.pavlikj3.delivery.core.dto.PostalOffice;
import cz.pavlikj3.delivery.core.exception.ValidationException;
import cz.pavlikj3.delivery.core.utils.StringUtil;

@Component
public class PackageBll 
{	
	@Autowired
	private PostalOfficeDao postalOfficeDao;

	@Autowired
	private PackageDao packageDao;

	public void listPostOffices(OutputStream os) throws IOException
	{
		List<PostalOffice> postalOffices =  postalOfficeDao.findListBySf(null);
		for (PostalOffice postalOffice : postalOffices)
		{
			double sum = packageDao.findListBySf(packageDao.newSf().setPostalOfficeEquals(postalOffice)).stream().map(dto -> dto.getWeight()).mapToDouble(Double::doubleValue).sum();
			os.write(String.format("%05d %.3f\n", postalOffice.getPostalCode(), sum).getBytes());
		}
	}
	
	public void parseLine(String line)
	{
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
		Pattern p = Pattern.compile("^([0-9]+\\.?[0-9]*)[ ]+([0-9]{5})$");
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
			postalOffice.setPostalCode(28601);
			postalOffice = postalOfficeDao.save(postalOffice);			
		}
		
		Package pack = packageDao.newDto();
		pack.setPostalOffice(postalOffice);
		pack.setWeight(weight);
		packageDao.save(pack);
	}
}
