/**
 * Mad-Advertisement
 * Copyright (C) 2011 Thorsten Marx <thmarx@gmx.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package net.mad.ads.server.utils.renderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mad.ads.db.definition.AdDefinition;
import net.mad.ads.db.model.type.AdType;
import net.mad.ads.db.services.Lookup;

public class AdDefinitionRendererService {

	private static List<AdDefinitionRenderer<AdDefinition>> renderer = new ArrayList<AdDefinitionRenderer<AdDefinition>>();
	private static Map<AdType, AdDefinitionRenderer<AdDefinition>> typeLookup = new HashMap<AdType, AdDefinitionRenderer<AdDefinition>>();
	
	static {
		Collection<AdDefinitionRenderer<AdDefinition>> colRenderes = (Collection<AdDefinitionRenderer<AdDefinition>>) Lookup.lookupAll(AdDefinitionRenderer.class);
		renderer.addAll(colRenderes);
		
		for (AdDefinitionRenderer<AdDefinition> adt : colRenderes) {
			typeLookup.put(adt.getType(), adt);
		}
	}
	
	public static AdDefinitionRenderer<AdDefinition> forType (AdType type) {
		System.out.println(typeLookup.size());
		return typeLookup.get(type);
	}
	
	public static List<AdDefinitionRenderer<AdDefinition>> getRenderer () {
		return renderer;
	}
}
