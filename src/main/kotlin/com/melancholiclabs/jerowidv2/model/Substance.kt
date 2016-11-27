package com.melancholiclabs.jerowidv2.model

import com.melancholiclabs.jerowidv2.extension.getJsoupDoc
import com.melancholiclabs.jerowidv2.extension.reformat
import com.melancholiclabs.jerowidv2.extension.selectFirstText
import com.melancholiclabs.jerowidv2.extension.tableToString
import org.jsoup.nodes.Element
import java.io.Serializable
import java.lang.IllegalStateException
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Data class representing a substance in the erowid api and provides methods for fetching available pages.
 *
 * @property[id] id of the substance.
 * @property[name] name of the substance.
 * @property[category] category of the substance.
 * @property[imageUrl] image url of the substance.
 * @property[botanicalClassification] botanical classification of the substance.
 * @property[effectsClassification] effects classification of the substance.
 * @property[chemicalName] chemical name of the substance.
 * @property[commonNames] common names of the substance.
 * @property[uses] uses of the substance.
 * @property[description] description of the substance.
 * @property[basicsURL] basics page url of the substance.
 * @property[effectsURL] effects page url of the substance.
 * @property[imagesURL] images page url of the substance.
 * @property[healthURL] health page url of the substance.
 * @property[lawURL] law page url of the substance.
 * @property[doseURL] dose page url of the substance.
 * @property[chemistryURL] chemistry page url of the substance.
 * @property[researchChemicalsURL] research chemical page url of the substance.
 * @constructor generic, sets all fields, constructor.
 */
data class Substance(val id: Int,
                     val name: String,
                     val url: String?,
                     val category: Category,
                     val imageUrl: String? = null,
                     val botanicalClassification: String? = null,
                     val effectsClassification: String? = null,
                     val chemicalName: String? = null,
                     val commonNames: String? = null,
                     val uses: String? = null,
                     val description: String? = null,
                     val basicsURL: String? = null,
                     val effectsURL: String? = null,
                     val imagesURL: String? = null,
                     val healthURL: String? = null,
                     val lawURL: String? = null,
                     val doseURL: String? = null,
                     val chemistryURL: String? = null,
                     val researchChemicalsURL: String? = null) : Serializable {

    /**
     * Gets a Page.Basic using the given basicsPageUrl.
     *
     * @return basics page for this [Substance].
     */
    fun getBasicPage(): Page.Basics? = basicsURL?.getJsoupDoc().let {
        val contentSection = it?.select("div.content-section")?.first()

        fun getBasicsSections(element: Element?): String? {
            val sections = element?.select("div.basics-indent-text > *")
            if (sections?.html()?.isEmpty() == true) return null
            return sections?.map {
                "${it.select("div.section-title2").text().trim()} \t ${it.select("div.basics-text").text()} \n"
            }?.reduceRight { s1, s2 -> s1 + s2 }?.trim()
        }

        val basicsDescription = contentSection?.select("div.basics-description")?.first()
        val tempDescriptionText = basicsDescription?.selectFirstText("div.basics-text")
        val tempDescriptionSections = getBasicsSections(basicsDescription)

        val basicsEffects = contentSection?.select("div.basics-effects")?.first()
        val tempEffectsText = basicsEffects?.selectFirstText("div.basics-text")
        val tempEffectsSections = getBasicsSections(basicsEffects)

        val basicsProblems = contentSection?.select("div.basics-problems")?.first()
        val tempProblemsText = basicsProblems?.selectFirstText("div.basics-text")
        val tempProblemsSections = getBasicsSections(basicsProblems)

        val basicsCaution = contentSection?.select("div.basics-caution")?.first()
        val tempDisclaimer = basicsCaution?.selectFirstText("div.basics-text")

        Page.Basics(id, name, url, category, tempDescriptionText, tempDescriptionSections, tempEffectsText, tempEffectsSections,
                tempProblemsText, tempProblemsSections, tempDisclaimer)
    }

    /**
     * Gets a Page.Effects using the given effectsPageUrl.
     *
     * @return effects page for this [Substance].
     */
    fun getEffectsPage(): Page.Effects? = effectsURL?.getJsoupDoc().let {
        val contentSection = it?.select("div.content-section")?.first()

        val effectsDuration = contentSection?.select("div.effects-duration")?.first()

        val durationTables = effectsDuration?.select("table.duration-summary") // TODO parse tables

        val durationCharts = effectsDuration?.select("img.duration-chart") // TODO this doesn't work
        val tempDurationChartUrls: String? =
                if (durationCharts?.html()?.isEmpty() == false) durationCharts.map { "${it.absUrl("src")} \t" }
                        .reduceRight { s1, s2 -> s1 + s2 }
                else null

        val effectsListPositive = contentSection?.select("div.effects-list-positive > ul.effects-item > li")
        val tempPositiveEffects: String? =
                if (effectsListPositive?.html()?.isEmpty() == false) effectsListPositive.map { "${it.absUrl("src")} \t" }
                        .reduceRight { s1, s2 -> s1 + s2 }
                else null

        val effectsListNeutral = contentSection?.select("div.effects-list-neutral > ul.effects-item > li")
        val tempNeutralEffects: String? =
                if (effectsListNeutral?.html()?.isEmpty() == false) effectsListNeutral.map { "${it.absUrl("src")} \t" }
                        .reduceRight { s1, s2 -> s1 + s2 }
                else null

        val effectsListNegative = contentSection?.select("div.effects-list-negative > ul.effects-item > li")
        val tempNegativeEffects: String? =
                if (effectsListNegative?.html()?.isEmpty() == false) effectsListNegative.map { "${it.absUrl("src")} \t" }
                        .reduceRight { s1, s2 -> s1 + s2 }
                else null

        val tempDescription = contentSection?.selectFirstText("div.effects-description > div.effects-text")
        val tempReports = contentSection?.selectFirstText("div.effects-exp-excerpts div.effects-exp-excerpt")
        val tempDisclaimer = contentSection?.selectFirstText("div.effects-caution > div.effects-text")

        Page.Effects(id, name, url, category, null, tempDurationChartUrls, tempPositiveEffects, tempNeutralEffects,
                tempNegativeEffects, tempDescription, tempReports, tempDisclaimer) // TODO tables
    }

    /**
     * Gets a Page.Images using the given imagesPageUrl.
     *
     * @return images page for this [Substance].
     */
    fun getImagesPage(): Page.Images? = imagesURL?.getJsoupDoc().let {
        val imageEntries = it?.select("div.image-entry")

        var tempImageList: String? = null
        if (imageEntries?.html()?.isEmpty() == false) tempImageList = imageEntries.map {
            "${it.select("div.image > a").first().text()} \t ${it.select("div.image > a").first().absUrl("href")} \t " +
                    "${it.select("div.desc").text()} \t ${it.select("div.credit")} \n"
        }.reduceRight { s1, s2 -> s1 + s2 }

        Page.Images(id, name, url, category, tempImageList)
    }


    /**
     * Gets a Page.Health using the given healthPageUrl.
     *
     * @return health page for this [Substance].
     */
    fun getHealthPage(): Page.Health? = healthURL?.getJsoupDoc().let {
        val contentSection = it?.select("div.content-section")?.first()

        val tempNotes = contentSection?.selectFirstText("div.health-notes")
        val tempDeaths = contentSection?.selectFirstText("div.health-deaths")
        val tempWarnings = contentSection?.selectFirstText("div.health-warnings")
        val tempCautions = contentSection?.selectFirstText("div.health-cautions")

        Page.Health(id, name, url, category, tempNotes, tempDeaths, tempWarnings, tempCautions, null) // TODO benefits
    }

    /**
     * Gets a Page.Law using the given lawPageUrl.
     *
     * @return law page for this [Substance].
     */
    fun getLawPage(): Page.Law? = lawURL?.getJsoupDoc().let {
        val contentSection = it?.select("div.content-section")?.first()

        val lawFederal = contentSection?.select("div.law-federal")?.first()

        val lawTable = lawFederal?.select("table.law-summary")?.first() // TODO parse table

        val tempFederalLaw: String? = lawFederal?.selectFirstText("div.law-text")

        val tempStateLaw: String? = contentSection?.selectFirstText("div.law-states") // TODO tab deliminate

        val tempInternationalLaw: String? = contentSection?.selectFirstText("div.law-contries") // TODO tab deliminate

        val tempDisclaimer: String? = contentSection?.selectFirstText("div.law-caution")

        Page.Law(id, name, url, category, null, tempFederalLaw, tempStateLaw, tempInternationalLaw, tempDisclaimer)
        // TODO tables
    }

    /**
     * Gets a Page.Dose using the given dosePageUrl.
     *
     * @return dose page for this [Substance].
     */
    fun getDosePage(): Page.Dose? = doseURL?.getJsoupDoc().let {
        val topics = it?.select("div.content-section")?.first()?.select("> div") ?:
                it?.select("div#content-body-frame")?.first()?.select("> div")

        var tempDoseTable: String? = null
        var tempDoseText: String? = null
        var tempNotes: String? = null
        topics?.forEach {
            if (it.html().contains("dose-description")) {
                tempDoseTable = (it.select("table.dose-summary")?.first() ?: it.select("table.chart")?.first())?.tableToString()
                tempDoseText = it.selectFirstText("div.dose-text")
            } else if (it.html().contains("dose-notes")) {
                val temp = it.selectFirstText("div.dose-notes-text")?.trim()
                tempNotes = Scanner(temp).run {
                    var tempString: String? = null
                    while (hasNextLine()) {
                        val line = nextLine()
                        if (line == "") tempString += "\n" else tempString += "${line.reformat()} \t"
                        tempString = line.trim()
                    }
                    tempString
                }
            } else if (it.html().contains("body1")) {
                tempDoseText = it.text().reformat()
                tempDoseTable = it.select("table cellpadding=5").first().tableToString()
            }
        }

        val tempDisclaimer = "Every individual reacts differently to every chemical. Erowid's dosage information is a summary of data gathered from users, research, and other resources. This information is intended to describe the range of dosages people report using. It should not be construed as a recommendation of any sort. Individuals can respond very differently to the same dosage. What is safe for one can be deadly for another."

        Page.Dose(id, name, url, category, tempDoseTable, tempDoseText, tempNotes, tempDisclaimer)
    }

    /**
     * Gets a Page.Chemistry using the given chemistryPageUrl.
     *
     * @return chemistry page for this [Substance].
     */
    fun getChemistryPage(): Page.Chemistry? = chemistryURL?.getJsoupDoc().let {
        val tempChemTable = it?.select("table.chem-table")?.first()?.tableToString()

        val tempMoleculeUrl = it?.select("div.content-section")?.first()?.select("img")?.first()?.let {
            val p: Pattern = Pattern.compile("src=\"(.*?)\"")
            val m: Matcher = p.matcher(it.html())
            var tempString: String? = null
            try {
                tempString = m.group(0).substring(5, m.group(0).lastIndexOf('"'))
            } catch (e: IllegalStateException) {
            }
            tempString
        }

        Page.Chemistry(id, name, url, category, tempChemTable, tempMoleculeUrl)
    }

    /**
     * Gets a Page.ResearchChemical using the given researchChemicalPageUrl.
     *
     * @return researchChemical page for this [Substance].
     */
    fun getResearchChemicalPage(): Page.ResearchChemical? = researchChemicalsURL?.getJsoupDoc()?.let {
        val tempSummaryText: String? = Scanner(it.select("div.sum-text").first().text().trim()).run {
            var tempString: String? = null
            while (hasNextLine()) {
                val line = nextLine()
                if (line == "") tempString += "\n" else tempString += "${line.reformat()} \t"
            }
            tempString?.trim()
        }

        val tempImageUrl = "https://www.erowid.org/images/buttons/research_chems.gif"

        Page.ResearchChemical(id, name, url, category, tempSummaryText, tempImageUrl)
    }
}