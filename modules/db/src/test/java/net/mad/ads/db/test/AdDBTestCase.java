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
package net.mad.ads.db.test;

import java.io.IOException;

import junit.framework.TestCase;
import net.mad.ads.db.AdDBManager;
import net.mad.ads.db.db.AdDB;

import org.junit.After;
import org.junit.Before;

public abstract class AdDBTestCase extends TestCase {

	protected static AdDB db = null;
	protected static AdDBManager manager = null;
	
	@Before
	public void setUp () throws IOException {
		manager = AdDBManager.builder().build();
		db = manager.getAdDB();
		db.open();
	}
	@After
	public void tearDown () throws IOException {
		db.close();
	}
}
