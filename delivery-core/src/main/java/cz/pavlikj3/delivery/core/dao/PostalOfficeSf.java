package cz.pavlikj3.delivery.core.dao;

public class PostalOfficeSf extends BaseSf
{
	private Integer postalCodeEquals;

	public Integer getPostalCodeEquals() {
		return postalCodeEquals;
	}

	public PostalOfficeSf setPostalCodeEquals(Integer postalCodeEquals) {
		this.postalCodeEquals = postalCodeEquals;
		return this;
	}
	
}
