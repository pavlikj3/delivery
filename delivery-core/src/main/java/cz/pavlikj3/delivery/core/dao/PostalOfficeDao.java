package cz.pavlikj3.delivery.core.dao;

import org.springframework.stereotype.Component;

import cz.pavlikj3.delivery.core.dto.PostalOffice;

@Component
public class PostalOfficeDao extends ListDao<PostalOffice, PostalOfficeSf>
{
	public PostalOfficeDao() 
	{
		setDtoClass(PostalOffice.class);
		setSfClass(PostalOfficeSf.class);
	}
}
