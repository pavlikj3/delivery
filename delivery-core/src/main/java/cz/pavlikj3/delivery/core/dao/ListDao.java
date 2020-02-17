package cz.pavlikj3.delivery.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cz.pavlikj3.delivery.core.dto.BaseDto;
import cz.pavlikj3.delivery.core.utils.ListUtil;
import cz.pavlikj3.delivery.core.utils.LongUtil;

public class ListDao<T extends BaseDto, F extends BaseSf> implements IDao<T,F>
{
	private Class<T> dtoClass;
	private Class<F> sfClass;
	private List<T> list;
	private long maxId;
	
	public ListDao() 
	{
		list = new ArrayList<T>();
		maxId = 0;
	}
	
	public T find(Long id) 
	{
		T result = ListUtil.getFirst(list.stream().filter(dto -> LongUtil.equals(id, dto.getId(), true)).collect(Collectors.<T>toList()));
		return result;
	}
	
	
	public T findBySf(F sf) 
	{
		return null;
	}
	
	public Long countBySf(F sf) 
	{
		return null;
	}
	
	public List<T> findListBySf(F sf) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void delete(Long id) 
	{
		assert id != null;
		List<T> newList = list.stream().filter(dto -> !LongUtil.equals(id, dto.getId(), true)).collect(Collectors.<T>toList());
		if (newList.size() == list.size())
		{
			throw new RuntimeException(String.format("Unknown dto of class: '%s' with id: %d", getDtoClass().getSimpleName(), id));
		}
		list = newList;
	}
	
	public T save(T dto) 
	{
		assert dto != null;
		if (LongUtil.isEmpty(dto.getId()))
		{
			dto.setId(++maxId);
			list.add(dto);
		}
		else
		{
			if (list.remove(dto))
			{
				list.add(dto);
			}
		}
		return dto;
	}
	
	public F newSf() 
	{
		try
		{
			return getSfClass().newInstance();
		}
		catch (IllegalAccessException|InstantiationException ex)
		{
			throw new RuntimeException(ex);
		}
	}
	public T newDto() 
	{
		try
		{
			return getDtoClass().newInstance();
		}
		catch (IllegalAccessException|InstantiationException ex)
		{
			throw new RuntimeException(ex);
		}
	}

	public Class<T> getDtoClass() {
		return dtoClass;
	}

	public void setDtoClass(Class<T> dtoClass) {
		this.dtoClass = dtoClass;
	}

	public Class<F> getSfClass() {
		return sfClass;
	}

	public void setSfClass(Class<F> sfClass) {
		this.sfClass = sfClass;
	}

	
	
}
