package cz.pavlikj3.delivery.core.business;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.pavlikj3.delivery.core.dao.PackageDao;
import cz.pavlikj3.delivery.core.dao.PostalOfficeDao;
import cz.pavlikj3.delivery.core.dto.PostalOffice;

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
			os.write(String.format("%s %.4f\n", postalOffice.getPostalCode(), sum).getBytes());
		}
	}
}
