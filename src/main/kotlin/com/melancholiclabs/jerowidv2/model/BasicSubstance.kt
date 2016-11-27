package com.melancholiclabs.jerowidv2.model

import com.melancholiclabs.jerowidv2.extension.getJsoupDoc
import com.melancholiclabs.jerowidv2.extension.selectFirstText
import java.io.Serializable

/**
 * Data class representing a substance from erowid.org with basic information.
 *
 * @property[id] unique substance id.
 * @property[name] substance name.
 * @property[url] substance url.
 * @property[category] substance category.
 * @constructor generic, sets all fields, constructor.
 */
data class BasicSubstance(val id: Int, val name: String, val url: String?, val category: Category) : Serializable {

    /**
     * Returns a [Substance] from a given [BasicSubstance].
     *
     * @return [Substance] object created using data scraped from erowid.org using this [BasicSubstance].
     */
    fun loadMore(): Substance {
        if (url == null) return Substance(id, name, url, category)

        val document = url.getJsoupDoc()

        var tempName = document?.select("div.ts-substance-name")?.first()?.text()
        if (tempName != null && !tempName.isEmpty()) tempName = name

        val sumCardText = document?.select("div.summary-card-text-surround")?.first()

        var tempBotanicalClassification: String? = null

        val botRows = sumCardText?.select("div.fgs-row")
        if (botRows?.html()?.isEmpty() == false) tempBotanicalClassification = botRows
                .map { "${it.text()}, ".substring(0, it.text().length - 2) }
                .reduceRight { s1, s2 -> s1 + s2 }

        val tempChemicalName: String? = sumCardText?.selectFirstText("div.sum-chem-name")
        val tempCommonName: String? = sumCardText?.selectFirstText("div.sum-common-name")
        val tempDescription: String? = sumCardText?.selectFirstText("div.sum-description")
        val tempEffectsClassification: String? = sumCardText?.selectFirstText("div.sum-effects")
        val tempUses: String? = sumCardText?.selectFirstText("div.sum-uses")
        val tempImageUrl: String? = sumCardText?.select("div.summary-card-topic-image img")?.first()?.absUrl("src")

        var tempBasicsPageUrl: String? = null
        var tempEffectsPageUrl: String? = null
        var tempImagesPageUrl: String? = null
        var tempHealthPageUrl: String? = null
        var tempLawPageUrl: String? = null
        var tempDosePageUrl: String? = null
        var tempChemistryPageUrl: String? = null
        var tempResearchChemicalPageUrl: String? = null

        document?.select("div.summary-card-icon-surround a[href]")?.forEach {
            if (it.attr("href").contains("basics")) tempBasicsPageUrl = it.absUrl("href")
            else if (it.attr("href").contains("effects")) tempEffectsPageUrl = it.absUrl("href")
            else if (it.attr("href").contains("images")) tempImagesPageUrl = it.absUrl("href")
            else if (it.attr("href").contains("health")) tempHealthPageUrl = it.absUrl("href")
            else if (it.attr("href").contains("law")) tempLawPageUrl = it.absUrl("href")
            else if (it.attr("href").contains("dose")) tempDosePageUrl = it.absUrl("href")
            else if (it.attr("href").contains("chemistry")) tempChemistryPageUrl = it.absUrl("href")
            else if (it.attr("href").contains("research_chems")) tempResearchChemicalPageUrl = it.absUrl("href")
        }

        return Substance(id, tempName!!, url, category, tempImageUrl, tempBotanicalClassification,
                tempEffectsClassification, tempChemicalName, tempCommonName, tempUses, tempDescription, tempBasicsPageUrl,
                tempEffectsPageUrl, tempImagesPageUrl, tempHealthPageUrl, tempLawPageUrl, tempDosePageUrl,
                tempChemistryPageUrl, tempResearchChemicalPageUrl)
    }
}
