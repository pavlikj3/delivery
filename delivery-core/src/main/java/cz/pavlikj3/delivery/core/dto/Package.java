package cz.pavlikj3.delivery.core.dto;

public class Package extends BaseDto
{
	private Double weight;
	private PostalOffice postalOffice;
	
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public PostalOffice getPostalOffice() {
		return postalOffice;
	}

	public void setPostalOffice(PostalOffice postalOffice) {
		this.postalOffice = postalOffice;
	}
}
