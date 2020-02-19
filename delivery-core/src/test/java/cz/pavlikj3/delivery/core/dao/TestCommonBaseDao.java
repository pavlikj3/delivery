/*-+-+-
 * Created by Jan Pavlik(pavlikj3 at seznam.cz) on 5.1.2011.
 * Licence LGPLv3(see:  http://www.gnu.org/licenses/lgpl-3.0.html).
 * This file is part of sokolovo framework project(http://duklan.org).
 * 
 * Enjoy my code style.
 */
package cz.pavlikj3.delivery.core.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import cz.pavlikj3.delivery.core.dto.BaseDto;

public abstract class TestCommonBaseDao<T extends BaseDto, F extends BaseSf> extends BaseTest
{
	
	public TestCommonBaseDao()
	{
	}
		
	protected abstract T createDto()  ;
	
	protected void setupSf(F sf) {}
		
	@Test 
	public void testNewDto() 
	{
		IDao<T,F> dao = this.getDao(); 

		BaseDto item = dao.newDto();
		Assert.assertNotNull(item);
	}
	
	@Test 
	public void testFind() 
	{
		IDao<T,F> dao = this.getDao(); 
		T item = createDto();
		dao.save(item);
		
		T item1 = dao.find(item.getId());		
		Assert.assertTrue(item1.getId() > 0);		
		Assert.assertEquals(item1.getId(), item.getId());
		
	}

	@Test 
	public void testCountBySf() 
	{
		IDao<T,F> dao = this.getDao();
		F sf = dao.newSf();
		setupSf(sf);
		Long size = dao.countBySf(sf);
		Assert.assertTrue(size >= 0 );
	}
	
	@Test  
	public void testFindListBySf() 
    {
		IDao<T,F> dao = this.getDao();
		F sf = dao.newSf();
		setupSf(sf);
		List<T> list = dao.findListBySf(sf);
		Assert.assertNotNull(list);
    }
	
	@Test  
	public void testSave() 
	{
		IDao<T,F> dao = this.getDao(); 

		T item = createDto();
		dao.save(item);
		Assert.assertTrue(item.getId() > 0);		
		Assert.assertEquals(item.getId(), item.getId());		
	}

	@Test  
	public void testDelete() 
	{
		
		IDao<T,F> dao = this.getDao(); 

		T item = createDto();
		dao.save(item);
		Assert.assertTrue(item.getId() > 0);		
		Assert.assertEquals(item.getId(), item.getId());		
		
		dao.delete(item.getId());		
	}

	protected abstract ListDao<T, F> getDao() ;
	
}
