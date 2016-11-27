package com.melancholiclabs.jerowidv2.model

import java.io.Serializable

/** Created by Melancholic Labs on 11/27/2016. */
class Page {

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

    data class Images(val id: Int,
                      val name: String,
                      val url: String?,
                      val category: Category,
                      val imageList: String?) : Serializable

    data class Health(val id: Int,
                      val name: String,
                      val url: String?,
                      val category: Category,
                      val notes: String?,
                      val deaths: String?,
                      val warnings: String?,
                      val cautions: String?,
                      val benefits: String?) : Serializable


    data class Law(val id: Int,
                   val name: String,
                   val url: String?,
                   val category: Category,
                   val legalTable: String?,
                   val federalLaw: String?,
                   val stateLaw: String?,
                   val internationalLaw: String?,
                   val disclaimer: String?) : Serializable

    data class Dose(val id: Int,
                    val name: String,
                    val url: String?,
                    val category: Category,
                    val doseTable: String?,
                    val doseText: String?,
                    val notes: String?,
                    val disclaimer: String?) : Serializable

    data class Chemistry(val id: Int,
                         val name: String,
                         val url: String?,
                         val category: Category,
                         val chemTable: String?,
                         val moleculeUrl: String?) : Serializable

    data class ResearchChemical(val id: Int,
                                val name: String,
                                val url: String?,
                                val category: Category,
                                val summary: String?,
                                val imageUrl: String?) : Serializable
}