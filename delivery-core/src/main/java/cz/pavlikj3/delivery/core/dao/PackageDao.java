package cz.pavlikj3.delivery.core.dao;

import org.springframework.stereotype.Component;

@Component
public class PackageDao extends ListDao<cz.pavlikj3.delivery.core.dto.Package, PackageSf>
{
	public PackageDao() 
	{
		setDtoClass(cz.pavlikj3.delivery.core.dto.Package.class);
		setSfClass(PackageSf.class);
	}
}
