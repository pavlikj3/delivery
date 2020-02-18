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

import cz.patos.sokolovofw.core.base.dao.BaseDao;
import cz.patos.sokolovofw.core.base.dao.ListSP;
import cz.patos.sokolovofw.core.base.model.BaseModel;
import cz.pavlikj3.delivery.core.dto.BaseDto;

public abstract class TestCommonBaseDao<T extends BaseDto, F extends BaseSf> extends BaseTest
{
	
	public TestCommonBaseDao()
	{
	}
		
	protected abstract T createDto()  ;
	
	protected void setupSf(F sf) {}
		
	@Test 
	public void testCreate() 
	{
		if (!isTestCreate())
		{
			return;
		}
		BaseDao<T,F> dao = this.getDao(); 

		BaseModel item = dao.create();
		Assert.assertNotNull(item);
		Assert.assertEquals(0, item.getId());
		Assert.assertEquals(dao.getModelClass(), item.getClass());
	}
	
	@Test 
	public void testGet() 
	{
		if (!isTestGet())
		{
			return;
		}
		BaseDao<T,F> dao = this.getDao(); 
		T item = createModel();
		dao.save(item);
		
		BaseModel item1 = dao.get(item.getId());		
		Assert.assertTrue(item1.getId() > 0);		
		Assert.assertEquals(dao.getModelClass(), item.getClass());
		Assert.assertEquals(item1.getId(), item.getId());
		
	}

	@Test 
	public void testGetSize() 
	{
		BaseDao<T,F> dao = this.getDao();
		F sf = dao.createSF();
		setupSF(sf);
		int size = dao.getSize(sf);
		Assert.assertTrue(size >= 0 );
	}
	
	@Test  
	public void testGetList() 
    {
		BaseDao<T,F> dao = this.getDao();
		F sf = dao.createSF();
		setupSF(sf);
		List<T> list = dao.getList(sf, new ListSP());
		Assert.assertNotNull(list);
    }
	
	@Test  
	public void testSave() 
	{
		if (!isTestSave())
		{
			return;
		}
		
		BaseDao<T,F> dao = this.getDao(); 

		T item = createModel();
		dao.save(item);
		Assert.assertTrue(item.getId() > 0);		
		Assert.assertEquals(dao.getModelClass(), item.getClass());
		Assert.assertEquals(item.getId(), item.getId());		
	}

	@Test  
	public void testDelete() 
	{
		if (!isTestDelete())
		{
			return;
		}
		
		BaseDao<T,F> dao = this.getDao(); 

		T item = createModel();
		dao.save(item);
		Assert.assertTrue(item.getId() > 0);		
		Assert.assertEquals(dao.getModelClass(), item.getClass());
		Assert.assertEquals(item.getId(), item.getId());		
		
		dao.delete(item);		
	}

	protected abstract IDao<T, F> getDao() ;
	
}
