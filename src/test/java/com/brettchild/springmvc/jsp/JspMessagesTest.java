package com.brettchild.springmvc.jsp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class JspMessagesTest {

	private JspMessages messages = new JspMessages();
	
	@Test
	public void testAdd() {
		messages.add(new JspMessage("code", "message"));
		
		assertEquals(1, messages.size());
	}

	@Test
	public void testRemove() {
		
		messages.add(new JspMessage("code", "message"));
		
		assertEquals(1, messages.size());
		
		messages.remove(0);
		
		assertEquals(0, messages.size());
		
	}

//	@Test
//	public void testSize() {
		// Already tested with testRemove()
//	}

	@Test
	public void testHasErrors() {
		
		messages.add(new JspMessage("code", "message"));
		
		assertTrue(!messages.hasErrors());
		
		messages.add(new JspMessage("error", "error", JspMessageLevel.ERROR));
		
		assertTrue(messages.hasErrors());
	}

	@Test
	public void testHasLevel() {
		
		// Nothing there yet
		assertTrue(!messages.hasLevel(JspMessageLevel.INFO));
		
		// Now INFO level
		messages.add(new JspMessage("code", "message"));
		
		assertTrue(messages.hasLevel(JspMessageLevel.INFO));
		
		// Test removal
		messages.remove(0);
		
		assertTrue(!messages.hasLevel(JspMessageLevel.INFO));
		
	}

	@Test
	public void testGetMessages() {
		
		messages.add(new JspMessage("code", "message"));
		
		List<JspMessage> list = messages.getMessages();
		
		assertEquals(messages.size(), list.size());
		
		
	}

	@Test
	public void testGetErrors() {
		
		// No Errors yet
		messages.add(new JspMessage("code", "message"));
		
		List<JspMessage> errors = messages.getErrors();
		
		assertEquals(0, errors.size());
		
		// Errors now
		messages.add(new JspMessage("error", "error", JspMessageLevel.ERROR));
		
		errors = messages.getErrors();
		
		assertEquals(1, errors.size());
		
	}

//	@Test
//	public void testGetMessagesJspMessageLevel() {
		// Already tested with getErrors()
//	}

}
