package cz.pavlikj3.delivery.core.dao;

import cz.pavlikj3.delivery.core.dto.PostalOffice;

public class PackageSf extends BaseSf
{
	private PostalOffice postalOfficeEquals;

	public PostalOffice getPostalOfficeEquals() {
		return postalOfficeEquals;
	}

	public void setPostalOfficeEquals(PostalOffice postalOfficeEquals) {
		this.postalOfficeEquals = postalOfficeEquals;
	}
}
