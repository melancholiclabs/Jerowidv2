package com.melancholiclabs.jerowidv2.jerowid

import com.melancholiclabs.jerowidv2.model.BasicSubstance
import com.melancholiclabs.jerowidv2.model.Category
import com.melancholiclabs.jerowidv2.model.Category.*
import com.melancholiclabs.jerowidv2.model.Substance
import org.jsoup.nodes.Document

/**
 * Class representing the big_chart on erowid.org and provides access to each of the categories of substances.
 *
 * @property[document] jsoup document of the big_chart on erowid.org from which to scrape [BasicSubstance] objects.
 * @constructor sets the field [document].
 */
class BigChart(val document: Document?) {

    /** Provides the counter from which to assign unique ids to each substance item. */
    var idIterator = 0

    /**
     * Gets a [List] of [BasicSubstance] objects that belong to the [Category] passed in as an argument.
     *
     * @param[category] [Category] of the [BasicSubstance] object.
     * @return list of [BasicSubstance] objects where it.category = [category].
     */
    fun getBasicSubstancesByCategory(category: Category) = document?.select("table#section-$category td.subname")
            ?.map {
                BasicSubstance(
                        idIterator.apply { idIterator++ },
                        it.select("a")?.first()?.text() ?: it.text(),
                        it.select("a")?.first()?.attr("abs:href"),
                        category)
            }

    /**
     * Gets a list of [BasicSubstance] objects that have the category [CHEMICALS].
     *
     * @return list of [BasicSubstance] objects where it.category = CHEMICALS.
     */
    fun getBasicChems() = getBasicSubstancesByCategory(CHEMICALS)

    /**
     * Gets a list of [BasicSubstance] objects that have the category [PLANTS].
     *
     * @return list of [BasicSubstance] objects where it.category = PLANTS.
     */
    fun getBasicPlants() = getBasicSubstancesByCategory(PLANTS)

    /**
     * Gets a list of [BasicSubstance] objects that have the category [HERBS].
     *
     * @return list of [BasicSubstance] objects where it.category = HERBS.
     */
    fun getBasicHerbs() = getBasicSubstancesByCategory(HERBS)

    /**
     * Gets a list of [BasicSubstance] objects that have the category [PHARMS].
     *
     * @return list of [BasicSubstance] objects where it.category = PHARMS.
     */
    fun getBasicPharms() = getBasicSubstancesByCategory(PHARMS)

    /**
     * Gets a list of [BasicSubstance] objects that have the category [SMARTS].
     *
     * @return list of [BasicSubstance] objects where it.category = SMARTS.
     */
    fun getBasicSmarts() = getBasicSubstancesByCategory(SMARTS)

    /**
     * Gets a list of [BasicSubstance] objects that have the category [ANIMALS].
     *
     * @return list of [BasicSubstance] objects where it.category = ANIMALS.
     */
    fun getBasicAnimals() = getBasicSubstancesByCategory(ANIMALS)

    /**
     * Gets a list of [Substance] objects that have the category [CHEMICALS].
     *
     * @return list of [Substance] objects where it.category = CHEMICALS.
     */
    fun getDetailedChems() = getBasicChems()?.map { it.loadMore() }

    /**
     * Gets a list of [Substance] objects that have the category [PLANTS].
     *
     * @return list of [Substance] objects where it.category = PLANTS.
     */
    fun getDetailedPlants() = getBasicPlants()?.map { it.loadMore() }

    /**
     * Gets a list of [Substance] objects that have the category [HERBS].
     *
     * @return list of [Substance] objects where it.category = HERBS.
     */
    fun getDetailedHerbs() = getBasicHerbs()?.map { it.loadMore() }

    /**
     * Gets a list of [Substance] objects that have the category [PHARMS].
     *
     * @return list of [Substance] objects where it.category = PHARMS.
     */
    fun getDetailedPharms() = getBasicPharms()?.map { it.loadMore() }

    /**
     * Gets a list of [Substance] objects that have the category [SMARTS].
     *
     * @return list of [Substance] objects where it.category = SMARTS.
     */
    fun getDetailedSmarts() = getBasicSmarts()?.map { it.loadMore() }

    /**
     * Gets a list of [Substance] objects that have the category [ANIMALS].
     *
     * @return list of [Substance] objects where it.category = ANIMALS.
     */
    fun getDetailedAnimals() = getBasicAnimals()?.map { it.loadMore() }

    /**
     * Gets a list of all basic pages.
     *
     * @return list of all basic pages.
     */
    fun getBasicsPages(substanceList: List<Substance>?) = substanceList?.mapNotNull { it.getBasicPage() }

    /**
     * Gets a list of all effects pages.
     *
     * @return list of all effects pages.
     */
    fun getEffectsPages(substanceList: List<Substance>?) = substanceList?.mapNotNull { it.getEffectsPage() }

    /**
     * Gets a list of all images pages.
     *
     * @return list of all images pages.
     */
    fun getImagesPages(substanceList: List<Substance>?) = substanceList?.mapNotNull { it.getImagesPage() }

    /**
     * Gets a list of all health pages.
     *
     * @return list of all health pages.
     */
    fun getHealthPages(substanceList: List<Substance>?) = substanceList?.mapNotNull { it.getHealthPage() }

    /**
     * Gets a list of all law pages.
     *
     * @return list of all law pages.
     */
    fun getLawPages(substanceList: List<Substance>?) = substanceList?.mapNotNull { it.getLawPage() }

    /**
     * Gets a list of all dose pages.
     *
     * @return list of all dose pages.
     */
    fun getDosePages(substanceList: List<Substance>?) = substanceList?.mapNotNull { it.getDosePage() }

    /**
     * Gets a list of all chemistry pages.
     *
     * @return list of all chemistry pages.
     */
    fun getChemistryPages(substanceList: List<Substance>?) = substanceList?.mapNotNull { it.getChemistryPage() }

    /**
     * Gets a list of all researchChemical pages.
     *
     * @return list of all researchChemical pages.
     */
    fun getResearchChemicalPages(substanceList: List<Substance>?) = substanceList?.mapNotNull { it.getResearchChemicalPage() }

    // TODO toString() functions
}
