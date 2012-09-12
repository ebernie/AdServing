/**
 * Mad-Advertisement
 * Copyright (C) 2011 Thorsten Marx <thmarx@gmx.net>
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.mad.ads.server.utils.renderer;

import javax.servlet.http.HttpServletRequest;

import net.mad.ads.db.definition.AdDefinition;
import net.mad.ads.db.model.type.AdType;
import net.mad.ads.server.utils.context.AdContext;

public interface AdDefinitionRenderer<T extends AdDefinition> {

	public String render(T banner, HttpServletRequest request, AdContext context);
	
	public AdType getType();
}