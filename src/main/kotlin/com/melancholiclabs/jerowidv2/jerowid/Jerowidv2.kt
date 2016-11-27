package com.melancholiclabs.jerowidv2.jerowid

import com.melancholiclabs.jerowidv2.extension.getJsoupDoc
import com.melancholiclabs.jerowidv2.model.BasicSubstance
import nl.komponents.kovenant.task

/**
 * Main class that coordinates scraping and organizing of data scraped from erowid.org.
 *
 * @author Melancholic Labs
 */
class Jerowidv2 {

    /** Url to the big_chart on erowid.org. */
    val EROWID_CHART_URL = "https://www.erowid.org/general/big_chart.shtml"

    /** [BigChart] object using the Jsoup document returned using the [EROWID_CHART_URL]. */
    val bigChart = BigChart(EROWID_CHART_URL.getJsoupDoc())

    /**
     * Lazily gets all [BasicSubstance] objects by concatenating each list within [bigChart].
     *
     * @return list of all [BasicSubstance] objects.
     */
    fun getAllBasicSubstances(): List<BasicSubstance>? = lazy {
        bigChart.getBasicChems()
                ?.plus(bigChart.getBasicPlants()!!.asSequence())
                ?.plus(bigChart.getBasicHerbs()!!.asSequence())
                ?.plus(bigChart.getBasicPharms()!!.asSequence())
                ?.plus(bigChart.getBasicSmarts()!!.asSequence())
                ?.plus(bigChart.getBasicAnimals()!!.asSequence())
    }.value

    /**
     * Lazily gets all substances with the category CHEMICALS.
     *
     * @return list of all substances with the category CHEMICALS.
     */
    fun getAllChemSubstances() = lazy { task { bigChart.getDetailedChems() }.get() }.value

    /**
     * Lazily gets all substances with the category PLANTS.
     *
     * @return list of all substances with the category PLANTS.
     */
    fun getAllPlantSubstances() = lazy { task { bigChart.getDetailedPlants() }.get() }.value

    /**
     * Lazily gets all substances with the category HERBS.
     *
     * @return list of all substances with the category HERBS.
     */
    fun getAllHerbSubstances() = lazy { task { bigChart.getDetailedHerbs() }.get() }.value

    /**
     * Lazily gets all substances with the category PHARMS.
     *
     * @return list of all substances with the category PHARMS.
     */
    fun getAllPharmSubstances() = lazy { task { bigChart.getDetailedPharms() }.get() }.value

    /**
     * Lazily gets all substances with the category SMARTS.
     *
     * @return list of all substances with the category SMARTS.
     */
    fun getAllSmartSubstances() = lazy { task { bigChart.getDetailedSmarts() }.get() }.value

    /**
     * Lazily gets all substances with the category ANIMALS.
     *
     * @return list of all substances with the category ANIMALS.
     */
    fun getAllAnimalSubstances() = lazy { task { bigChart.getDetailedAnimals() }.get() }.value

    /**
     * Lazily gets all substances by concatenating each list within [bigChart].
     *
     * @return list of all substances.
     */
    fun getAllSubstances() = lazy {
        task {
            getAllChemSubstances()?.asIterable()
                    ?.plus(getAllPlantSubstances()!!.asIterable())
                    ?.plus(getAllHerbSubstances()!!.asIterable())
                    ?.plus(getAllPharmSubstances()!!.asIterable())
                    ?.plus(getAllSmartSubstances()!!.asIterable())
                    ?.plus(getAllAnimalSubstances()!!.asIterable())
        }.get()
    }.value

    /**
     * Lazily gets all basics pages.
     *
     * @return list of all basics pages.
     */
    fun getAllBasicsPages() = lazy { task { getAllSubstances()?.map { it.getBasicPage() } }.get() }.value

    /**
     * Lazily gets all effects pages.
     *
     * @return list of all effects pages.
     */
    fun getAllEffectsPages() = lazy { task { getAllSubstances()?.map { it.getEffectsPage() } }.get() }.value

    /**
     * Lazily gets all images pages.
     *
     * @return list of all images pages.
     */
    fun getAllImagesPages() = lazy { task { getAllSubstances()?.map { it.getImagesPage() } }.get() }.value

    /**
     * Lazily gets all health pages.
     *
     * @return list of all health pages.
     */
    fun getAllHealthPages() = lazy { task { getAllSubstances()?.map { it.getHealthPage() } }.get() }.value

    /**
     * Lazily gets all law pages.
     *
     * @return list of all law pages.
     */
    fun getAllLawPages() = lazy { task { getAllSubstances()?.map { it.getLawPage() } }.get() }.value

    /**
     * Lazily gets all dose pages.
     *
     * @return list of all dose pages.
     */
    fun getAllDosePages() = lazy { task { getAllSubstances()?.map { it.getDosePage() } }.get() }.value

    /**
     * Lazily gets all chemistry pages.
     *
     * @return list of all chemistry pages.
     */
    fun getAllChemistryPages() = lazy { task { getAllSubstances()?.map { it.getChemistryPage() } }.get() }.value

    /**
     * Lazily gets all researchChemical pages.
     *
     * @return list of all researchChemical pages.
     */
    fun getAllResearchChemicalPages() = lazy { task { getAllSubstances()?.map { it.getResearchChemicalPage() } }.get() }.value

    // TODO implement writing logs to file

    // TODO implement (de)serializing data to file
}