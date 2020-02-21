package cz.pavlikj3.delivery.core.dao;

import cz.pavlikj3.delivery.core.dto.PostalOffice;

public class PackageSf extends BaseSf
{
	private PostalOffice postalOfficeEquals;

	public PostalOffice getPostalOfficeEquals() {
		return postalOfficeEquals;
	}

	public PackageSf setPostalOfficeEquals(PostalOffice postalOfficeEquals) {
		this.postalOfficeEquals = postalOfficeEquals;
		return this;
	}
}
