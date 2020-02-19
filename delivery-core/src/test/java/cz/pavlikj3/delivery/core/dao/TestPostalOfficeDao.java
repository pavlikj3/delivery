package cz.pavlikj3.delivery.core.dao;

import org.springframework.beans.factory.annotation.Autowired;

import cz.pavlikj3.delivery.core.dto.PostalOffice;

public class TestPostalOfficeDao extends TestCommonBaseDao<PostalOffice, PostalOfficeSf>{

	@Autowired
	private PostalOfficeDao postalOfficeDao;
	
	@Override
	protected PostalOffice createDto() 
	{
		PostalOffice result = postalOfficeDao.newDto();
		result.setPostalCode("28601");
		return result;
	}

	@Override
	protected ListDao<PostalOffice, PostalOfficeSf> getDao() {
		return postalOfficeDao;
	}

}
