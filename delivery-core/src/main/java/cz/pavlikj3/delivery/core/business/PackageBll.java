package cz.pavlikj3.delivery.core.business;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.pavlikj3.delivery.core.dao.PostalOfficeDao;
import cz.pavlikj3.delivery.core.dto.PostalOffice;

@Component
public class PackageBll 
{	
	@Autowired
	private PostalOfficeDao postalOfficeDao;
	
	public void listPostOffices(OutputStream os) throws IOException
	{
		List<PostalOffice> postalOffices =  postalOfficeDao.findListBySf(null);
		for (PostalOffice postalOffice : postalOffices)
		{
			os.write(String.format("%s\n", postalOffice.getPostalCode()).getBytes());
		}
	}
}
