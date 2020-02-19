package cz.pavlikj3.delivery.core.dao;

import org.springframework.beans.factory.annotation.Autowired;

import cz.pavlikj3.delivery.core.dto.Package;

public class TestPackageDao extends TestCommonBaseDao<Package, PackageSf>{

	@Autowired
	private PackageDao packageDao;
	
	@Autowired
	private TestPostalOfficeDao testPostalOfficeDao;
	
	@Override
	protected Package createDto() 
	{
		Package result = packageDao.newDto();
		result.setWeight(74.2);
		result.setPostalOffice(testPostalOfficeDao.returnDto());
		return result;
	}

	@Override
	protected ListDao<Package, PackageSf> getDao() {
		return packageDao;
	}

}
