package cz.pavlikj3.delivery.core.dao;

import java.util.List;

import cz.pavlikj3.delivery.core.dto.BaseDto;

public interface IDao<T extends BaseDto, F extends BaseSf> 
{
	T find(Long id);
	T findBySf(F sf);
	Long countBySf(F sf);
	List<T> findListBySf(F sf );
	void delete(Long id);
	T save(T dto);
	F newSf();
	T newDto();
}
