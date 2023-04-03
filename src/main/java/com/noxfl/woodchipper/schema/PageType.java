/**
 * 
 */
package com.noxfl.woodchipper.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Fernando Nathanael
 *
 */
public enum PageType {
	@JsonProperty("CATEGORY")
	CATEGORY,
	@JsonProperty("DETAIL")
	DETAIL
}

