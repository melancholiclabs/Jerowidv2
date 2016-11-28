package com.melancholiclabs.jerowidv2.model

import java.io.Serializable

/** Contains inner classes of each page type. */
class Page {

    /**
     * Data class representing a basics page for a substance from erowid.org.
     *
     * @property[id] substance id.
     * @property[name] substance name.
     * @property[url] substance url.
     * @property[category] substance category.
     * @property[description] basics page description.
     * @property[descriptionSections] basics page descriptions sections.
     * @property[effects] basics page effects.
     * @property[effectsSections] basics page effects sections.
     * @property[problems] basics page problems.
     * @property[problemsSections] basics page problems sections.
     * @property[disclaimer] basics page disclaimer.
     * @constructor generic, set all fields, constructor.
     */
    data class Basics(val id: Int,
                      val name: String,
                      val url: String?,
                      val category: Category,
                      val description: String?,
                      val descriptionSections: String?,
                      val effects: String?,
                      val effectsSections: String?,
                      val problems: String?,
                      val problemsSections: String?,
                      val disclaimer: String?) : Serializable

    /**
     * Data class representing an effects page for a substance from erowid.org.
     *
     * @property[id] substance id.
     * @property[name] substance name.
     * @property[url] substance url.
     * @property[category] substance category.
     * @property[durationTable] effects page duration table.
     * @property[durationChartUrls] effects page duration chart urls.
     * @property[positiveEffects] effects page positive effects.
     * @property[neutralEffects] effects page neutral effects.
     * @property[negativeEffects] effects page negative effects.
     * @property[description] effects page description.
     * @property[experienceReports] effects page experience reports.
     * @property[disclaimer] effects page disclaimer.
     * @constructor generic, set all fields, constructor.
     */
    data class Effects(val id: Int,
                       val name: String,
                       val url: String?,
                       val category: Category,
                       val durationTable: String?,
                       val durationChartUrls: String?,
                       val positiveEffects: String?,
                       val neutralEffects: String?,
                       val negativeEffects: String?,
                       val description: String?,
                       val experienceReports: String?,
                       val disclaimer: String?) : Serializable

    /**
     * Data class representing an images page for a substance from erowid.org.
     *
     * @property[id] substance id.
     * @property[name] substance name.
     * @property[url] substance url.
     * @property[category] substance category.
     * @property[imageList] images page image list.
     * @constructor generic, set all fields, constructor.
     */
    data class Images(val id: Int,
                      val name: String,
                      val url: String?,
                      val category: Category,
                      val imageList: String?) : Serializable

    /**
     * Data class representing a health page for a substance from erowid.org.
     *
     * @property[id] substance id.
     * @property[name] substance name.
     * @property[url] substance url.
     * @property[category] substance category.
     * @property[notes] health page notes.
     * @property[deaths] health page deaths.
     * @property[warnings] health page warnings.
     * @property[cautions] health page cautions.
     * @property[benefits] health page benefits.
     * @constructor generic, set all fields, constructor.
     */
    data class Health(val id: Int,
                      val name: String,
                      val url: String?,
                      val category: Category,
                      val notes: String?,
                      val deaths: String?,
                      val warnings: String?,
                      val cautions: String?,
                      val benefits: String?) : Serializable

    /**
     * Data class representing a law page for a substance from erowid.org.
     *
     * @property[id] substance id.
     * @property[name] substance name.
     * @property[url] substance url.
     * @property[category] substance category.
     * @property[legalTable] law page legal table.
     * @property[federalLaw] law page federal law.
     * @property[stateLaw] law page state law.
     * @property[internationalLaw] law page international law.
     * @property[disclaimer] law page disclaimer.
     * @constructor generic, set all fields, constructor.
     */
    data class Law(val id: Int,
                   val name: String,
                   val url: String?,
                   val category: Category,
                   val legalTable: String?,
                   val federalLaw: String?,
                   val stateLaw: String?,
                   val internationalLaw: String?,
                   val disclaimer: String?) : Serializable

    /**
     * Data class representing a dose page for a substance from erowid.org.
     *
     * @property[id] substance id.
     * @property[name] substance name.
     * @property[url] substance url.
     * @property[category] substance category.
     * @property[doseTable] dose page dose table.
     * @property[doseText] dose page dose text.
     * @property[notes] dose page notes.
     * @property[disclaimer] dose page disclaimer.
     * @constructor generic, set all fields, constructor.
     */
    data class Dose(val id: Int,
                    val name: String,
                    val url: String?,
                    val category: Category,
                    val doseTable: String?,
                    val doseText: String?,
                    val notes: String?,
                    val disclaimer: String?) : Serializable

    /**
     * Data class representing a chemistry page for a substance from erowid.org.
     *
     * @property[id] substance id.
     * @property[name] substance name.
     * @property[url] substance url.
     * @property[category] substance category.
     * @property[chemTable] chemistry page chem table.
     * @property[moleculeUrl] chemistry page molecule url.
     * @constructor generic, set all fields, constructor.
     */
    data class Chemistry(val id: Int,
                         val name: String,
                         val url: String?,
                         val category: Category,
                         val chemTable: String?,
                         val moleculeUrl: String?) : Serializable

    /**
     * Data class representing a basics page for a substance from erowid.org.
     *
     * @property[id] substance id.
     * @property[name] substance name.
     * @property[url] substance url.
     * @property[category] substance category.
     * @property[summary] research chemical page summary.
     * @property[imageUrl] research chemical page image url.
     * @constructor generic, set all fields, constructor.
     */
    data class ResearchChemical(val id: Int,
                                val name: String,
                                val url: String?,
                                val category: Category,
                                val summary: String?,
                                val imageUrl: String?) : Serializable
}