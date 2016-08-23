package com.jdbc.helper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;


public class TestBroker  {
	
	@Test
	public void testQuery() throws Exception{
		String sql="select * from suser";
		List<Map<String, Object>>  ret=Broker.query(sql);
		for (int i = 0; i < ret.size(); i++) {
			Map<String, Object> map=ret.get(i);
			System.out.println(map.get("website"));
		}
	}
}
