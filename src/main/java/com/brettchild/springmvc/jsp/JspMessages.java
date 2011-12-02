package com.brettchild.springmvc.jsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JspMessages {

	private List<JspMessage> messages;
	private Map<JspMessageLevel, Boolean> hasLevelCache;

	public JspMessages() {
		messages = new ArrayList<JspMessage>();
		hasLevelCache = new HashMap<JspMessageLevel, Boolean>(
				JspMessageLevel.values().length);
	}

	public void add(JspMessage message) {
		messages.add(message);
		hasLevelCache.put(message.getLevel(), true);
	}

	public void remove(int index) {
		JspMessage message = messages.get(index);
		hasLevelCache.remove(message.getLevel());
		messages.remove(index);
	}

	public int size() {
		return messages.size();
	}

	public boolean hasErrors() {
		return hasLevel(JspMessageLevel.ERROR);
	}

	public boolean hasLevel(JspMessageLevel level) {

		Boolean hasLevel = hasLevelCache.get(level);

		if (hasLevel == null) {

			for (JspMessage message : messages) {

				if (message.getLevel().equals(level)) {
					hasLevel = true;
					break;
				}

			}

			// Add non-null value to cache
			if(hasLevel == null) {
				hasLevel = false;
			}
			
			hasLevelCache.put(level, hasLevel);
		}
		
		return hasLevel;
	}

	public List<JspMessage> getMessages() {
		return messages;
	}

	public List<JspMessage> getErrors() {
		return getMessages(JspMessageLevel.ERROR);
	}

	public List<JspMessage> getMessages(JspMessageLevel level) {

		List<JspMessage> levelMessages = new ArrayList<JspMessage>();

		for (JspMessage message : messages) {

			if (message.getLevel().equals(level))
				levelMessages.add(message);

		}

		return levelMessages;

	}

	public boolean isEmpty() {
		return messages.isEmpty();
	}

}
