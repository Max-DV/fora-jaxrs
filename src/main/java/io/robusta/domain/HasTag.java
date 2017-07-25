package io.robusta.domain;

import java.util.List;

public interface HasTag {

	public boolean isTagged();
	public List<? extends Tag> getTags();
	
}
