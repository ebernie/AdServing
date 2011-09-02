package net.mad.ads.server.utils.renderer.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.mad.ads.base.api.render.BannerRenderer;
import net.mad.ads.base.api.render.RenderContext;
import net.mad.ads.base.api.render.impl.freemarker.FreemarkerBannerRenderer;
import net.mad.ads.db.definition.BannerDefinition;
import net.mad.ads.db.definition.impl.banner.image.ImageBannerDefinition;
import net.mad.ads.db.enums.BannerType;
import net.mad.ads.server.utils.AdServerConstants;
import net.mad.ads.server.utils.RuntimeContext;
import net.mad.ads.server.utils.renderer.BannerDefinitionRenderer;

/**
 * Renderer für die Bannerdefinitionen des Types Image
 * 
 * @author tmarx
 *
 */
public class ImageBannerDefinitionRenderer implements BannerDefinitionRenderer<ImageBannerDefinition> {
	
	public static final Logger logger = LoggerFactory.getLogger(ImageBannerDefinitionRenderer.class);
	
	public static BannerDefinitionRenderer<ImageBannerDefinition> INSTANCE = null;
	
	private ImageBannerDefinitionRenderer () {
	}
	
	public static synchronized BannerDefinitionRenderer getInstance () {
		if (INSTANCE == null) {
			INSTANCE = new ImageBannerDefinitionRenderer();
		}
		
		return INSTANCE;
	}
	
	/* (non-Javadoc)
	 * @see net.mad.ads.server.utils.renderer.BannerDefinitionRenderer#render(net.mad.ads.api.definition.impl.image.ImageBannerDefinition)
	 */
	@Override
	public String render (ImageBannerDefinition banner, HttpServletRequest request) {
		
		String clickurl = RuntimeContext.getProperties().getProperty(AdServerConstants.CONFIG.PROPERTIES.CLICK_URL);
		String staticurl = RuntimeContext.getProperties().getProperty(AdServerConstants.CONFIG.PROPERTIES.STATIC_URL);
		if (!staticurl.endsWith("/")) {
			staticurl += "/";
		}
		
		RenderContext context = new RenderContext();
		context.put("banner", banner);
		context.put("staticUrl", staticurl);
		context.put("clickUrl", clickurl + "?id=" + banner.getId());
		
		try {
			return RuntimeContext.getBannerRenderer().render(BannerType.IMAGE.getName().toLowerCase(), context);
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return "";
	}
}