package com.brettchild.springmvc.dao.impl.util;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class VerifyDataUtil {

	private String tableName;
	private String primaryKeyName;
	
	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	public VerifyDataUtil(String tableName, String primaryKeyName, SimpleJdbcTemplate simpleJdbcTemplate) {
		this.tableName = tableName;
		this.primaryKeyName = primaryKeyName;
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}
	
	/**
	 * @param field
	 * @param expected
	 * @param userIds
	 * @return
	 */
	public boolean verifyDataInTable(String field, List<String> expectedValues, Integer... keyIds) {

		boolean exists = false;
		
		int size = keyIds.length;
		
		StringBuilder userIdStringBuilder = new StringBuilder();; 
		
		for(int i = 0; i < size; i++) {
			
			userIdStringBuilder.append(keyIds[i]);
			
			if(i + 1 != size) {
				userIdStringBuilder.append(", ");
			}
		}

		// Verify in the table
		List<Map<String, Object>> list = simpleJdbcTemplate
				.queryForList("SELECT * FROM " + tableName + " WHERE " + primaryKeyName + " IN (" + userIdStringBuilder.toString() + ")");

		assertNotNull(list);

		for (Map<String, Object> item : list) {

			Set<String> keys = item.keySet();

			for (String key : keys) {

				if ( field.equals(key) && expectedValues.contains( String.valueOf( item.get(key) ) ) ) {

					exists = true;

					break;

				} else {
					
					exists = false;
				
				}

			}

		}

		return exists;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPrimaryKeyName() {
		return primaryKeyName;
	}

	public void setPrimaryKeyName(String primaryKeyName) {
		this.primaryKeyName = primaryKeyName;
	}

	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}
	
	
	

}
