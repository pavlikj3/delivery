package cz.pavlikj3.delivery.core.dto;

import java.io.Serializable;

import cz.pavlikj3.delivery.core.utils.LongUtil;

public class BaseDto implements Serializable
{
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (obj == null)
		{
			return false;
		}
		if (!obj.getClass().equals(this.getClass()))
		{
			return false;
		}
		boolean result = LongUtil.equals(getId(), ((BaseDto)obj).getId(), true);
		return result;
	}
}
