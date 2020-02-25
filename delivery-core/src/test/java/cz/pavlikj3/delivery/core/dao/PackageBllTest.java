package cz.pavlikj3.delivery.core.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cz.pavlikj3.delivery.core.business.PackageBll;

public class PackageBllTest extends BaseTest
{
	@Autowired
	private PackageBll packageBll;
	
	@Test
	public void testParseAllLines() throws Exception
	{
		String data = "1.0 28601\n2.0 11150\n10 28601";
		
		String expectedResult = "28601 11.000\n11150 2.000\n";
		
		packageBll.parseAllLines(new ByteArrayInputStream(data.getBytes()));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		packageBll.listPostOffices(os);
		String result = new String(os.toByteArray());
		System.out.println(result);
		Assert.assertEquals(expectedResult, result);
	}
}
