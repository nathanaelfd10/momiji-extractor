/**
 * 
 */
package com.noxfl.woodchipper.schema.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Fernando Nathanael
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

	private String id;
	private String url;
	private List<String> categoryBreadcrumb;

}
