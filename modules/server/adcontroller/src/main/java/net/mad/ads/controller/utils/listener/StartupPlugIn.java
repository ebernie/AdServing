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
package net.mad.ads.controller.utils.listener;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentNavigableMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.mad.ads.common.template.TemplateManager;
import net.mad.ads.common.template.impl.freemarker.FMTemplateManager;
import net.mad.ads.common.util.Properties2;
import net.mad.ads.common.util.Strings;
import net.mad.ads.controller.utils.RuntimeContext;
import net.mad.ads.db.AdDBManager;
import net.mad.ads.db.definition.AdDefinition;
import net.mad.ads.db.model.type.AdType;
import net.mad.ads.db.services.AdTypes;

import org.apache.log4j.PropertyConfigurator;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.Hazelcast;

/**
 * 
 * @author thorsten
 */
public class StartupPlugIn implements ServletContextListener {

	private static final Logger logger = LoggerFactory
			.getLogger(StartupPlugIn.class);

	public void contextInitialized(ServletContextEvent event) {
		try {
			// Konfiguration einlesen
			String enviroment = event.getServletContext().getInitParameter(
					"enviroment");

			String configDirectory = new File(".").getAbsolutePath(); // event.getServletContext().getInitParameter("configDirectory");

			if (System.getProperties().containsKey("mad.home")) {
				configDirectory = System.getProperty("mad.home");
			}

			if (!configDirectory.endsWith("/")) {
				configDirectory += "/";
			}

			System.setProperty("mad.home", configDirectory);

			configDirectory += "config/";

			// configure log4j

			PropertyConfigurator.configure(Properties2
					.loadProperties(configDirectory + "log4j.properties"));

			RuntimeContext.setEnviroment(enviroment);
			String path = event.getServletContext().getRealPath("/");
			RuntimeContext.setProperties(Properties2
					.loadProperties(configDirectory + "config_controller.properties"));

			DB db = DBMaker
					.newFileDB(
							new File(RuntimeContext.getProperties()
									.getProperty("db.dir")))
					.closeOnJvmShutdown().make();

			// open existing an collection (or create new)
			ConcurrentNavigableMap<String, AdDefinition> map = db
					.getTreeMap("ads");
			
			RuntimeContext.setDb(db);
			RuntimeContext.setPersistentAds(map);
			
			RuntimeContext.setHazelcastInstance(Hazelcast.newHazelcastInstance());
			Map<String, AdDefinition> adMap = RuntimeContext.getHazelcastInstance().getMap("ads");
			RuntimeContext.setDistributedAds(adMap);

		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		try {

		} catch (Exception e) {
			logger.error("", e);
		}
	}
}