package net.mad.ads.db.condition.impl;


import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;

import net.mad.ads.db.AdDBConstants;
import net.mad.ads.db.condition.Condition;
import net.mad.ads.db.db.request.AdRequest;
import net.mad.ads.db.definition.BannerDefinition;
import net.mad.ads.db.definition.condition.CountryConditionDefinition;
import net.mad.ads.db.enums.ConditionDefinitions;
import net.mad.ads.db.enums.Country;

/**
 * Bedingung für das Land in dem ein Banner angezeigt werden soll
 * 
 * @author tmarx
 *
 */
public class CountryCondition implements Condition {

	@Override
	public void addQuery(AdRequest request, BooleanQuery mainQuery) {
		if (request.getCountry() == null) {
			return;
		}
		
		String country = request.getCountry().getCode();
		if (country.equals(Country.ALL.getCode()) || country.equals(Country.UNKNOWN.getCode())) {
			return;
		}
		BooleanQuery query = new BooleanQuery();
		
		BooleanQuery temp = new BooleanQuery();
		temp.add(new TermQuery(new Term(AdDBConstants.ADDB_BANNER_COUNTRY, String.valueOf(country))), Occur.SHOULD);
		temp.add(new TermQuery(new Term(AdDBConstants.ADDB_BANNER_COUNTRY, String.valueOf(Country.ALL.getCode()))), Occur.SHOULD);
		
		query.add(temp, Occur.MUST);
		mainQuery.add(query, Occur.MUST);
	}

	@Override
	public void addFields(Document bannerDoc, BannerDefinition bannerDefinition) {
		
		CountryConditionDefinition cdef = null;
		if (bannerDefinition.hasConditionDefinition(ConditionDefinitions.COUNTRY)) {
			cdef = (CountryConditionDefinition) bannerDefinition.getConditionDefinition(ConditionDefinitions.COUNTRY);
		}
		
		if (cdef != null && cdef.getCountries().size() > 0) {
			List<Country> list = cdef.getCountries();
			for (Country c : list) {
				bannerDoc.add(new Field(AdDBConstants.ADDB_BANNER_COUNTRY, c.getCode(), Field.Store.NO, Field.Index.NOT_ANALYZED_NO_NORMS));
			}
		} else {
			bannerDoc.add(new Field(AdDBConstants.ADDB_BANNER_COUNTRY, AdDBConstants.ADDB_BANNER_COUNTRY_ALL, Field.Store.NO, Field.Index.NOT_ANALYZED_NO_NORMS));
		}
	}

}