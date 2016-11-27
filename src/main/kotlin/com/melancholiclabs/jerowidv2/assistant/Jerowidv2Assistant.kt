package com.melancholiclabs.jerowidv2assistant

import com.melancholiclabs.jerowidv2.extension.doubleQ
import com.melancholiclabs.jerowidv2.extension.invokeAll
import com.melancholiclabs.jerowidv2.jerowid.Jerowidv2
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

/** Created by Melancholic Labs on 11/27/2016. */
class Jerowidv2Assistant {

    /** Object providing static access within [Jerowidv2Assistant]. */
    companion object {

        /** Url to the mysql database. */
        val DB_URL = "jdbc:mysql://127.0.0.1:3306/?autoReconnect=true&useSSL=false"
        /** Username. */
        val USER = "root"
        /** Password */
        val PASS = "Bittorrent1"

        /** Instance of [Jerowidv2] to manipulate scraped erowid.org data. */
        val jerowidv2 = Jerowidv2()

        /** Mysql connection for database manipulation. */
        val connection: Connection = DriverManager.getConnection(DB_URL, USER, PASS)
        /** Mysql statement for making calls to the database. */
        val statement: Statement = connection.createStatement()

        /**
         * Main method which deletes an existing erowid database, creates a new erowid database, and updates index tables with data scraped using [Jerowidv2].
         *
         * @param[args] command line arguments.
         */
        @JvmStatic fun main(args: Array<String>) {
            statement.executeUpdate("DROP DATABASE IF EXISTS erowid")
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS erowid")
            statement.executeUpdate("USE erowid")

            invokeAll(loadChemTable(), loadPlantTable(), loadHerbTable(), loadPharmTable(), loadSmartTable(),
                    loadAnimalTable(), loadBasicsTable(), loadEffectsTable(), loadImagesTable(), loadHealthTable(),
                    loadLawTable(), loadDoseTable(), loadChemistryTable(), loadResearchChemicalTable())
        }

        // TODO check if update

        /**
         * Creates a chemIndex table in the erowid database and loads it with chemical substance data
         * scraped using [Jerowidv2].
         */
        fun loadChemTable() {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS chemIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(125), "
                    + "category VARCHAR(20)," + "commonNames VARCHAR(150),"
                    + "effectsClassification VARCHAR(100)," + "chemicalName VARCHAR(350),"
                    + "description VARCHAR(1000)," + "imageURL VARCHAR(150),"
                    + "basicsURL VARCHAR(150)," + "effectsURL VARCHAR(150),"
                    + "imagesURL VARCHAR(150)," + "healthURL VARCHAR(150)," + "lawURL VARCHAR(150),"
                    + "doseURL VARCHAR(150)," + "chemistryURL VARCHAR(150),"
                    + "researchChemicalsURL VARCHAR(150))")

            jerowidv2.getAllChemSubstances()?.forEach {
                statement.executeUpdate("INSERT INTO chemIndex VALUES ('"
                        + it.id + "', '"
                        + it.name.doubleQ() + "', '"
                        + it.url?.doubleQ() + "', '"
                        + it.category.toString().doubleQ() + "', '"
                        + it.botanicalClassification?.doubleQ() + "', '"
                        + it.effectsClassification?.doubleQ() + "', '"
                        + it.chemicalName?.doubleQ() + "', '"
                        + it.description?.doubleQ() + "', '"
                        + it.imageUrl?.doubleQ() + "', '"
                        + it.basicsURL?.doubleQ() + "', '"
                        + it.effectsURL?.doubleQ() + "', '"
                        + it.imagesURL?.doubleQ() + "', '"
                        + it.healthURL?.doubleQ() + "', '"
                        + it.lawURL?.doubleQ() + "', '"
                        + it.doseURL?.doubleQ() + "', '"
                        + it.chemistryURL?.doubleQ() + "', '"
                        + it.researchChemicalsURL?.doubleQ() + "')")
            }
        }

        /**
         * Creates a plantIndex table in the erowid database and loads it with plant substance data
         * scraped using [Jerowidv2].
         */
        fun loadPlantTable() {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS plantIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(125), "
                    + "category VARCHAR(20)," + "botanicalClassification VARCHAR(200),"
                    + "commonNames VARCHAR(150)," + "effectsClassification VARCHAR(50),"
                    + "description VARCHAR(550)," + "imageURL VARCHAR(100),"
                    + "basicsURL VARCHAR(100)," + "effectsURL VARCHAR(100),"
                    + "imagesURL VARCHAR(100)," + "healthURL VARCHAR(100)," + "lawURL VARCHAR(100),"
                    + "doseURL VARCHAR(100)," + "chemistryURL VARCHAR(100))")

            jerowidv2.getAllPlantSubstances()?.forEach {
                statement.executeUpdate("INSERT INTO plantIndex VALUES ('"
                        + it.id + "', '"
                        + it.name.doubleQ() + "', '"
                        + it.url?.doubleQ() + "', '"
                        + it.category.toString().doubleQ() + "', '"
                        + it.botanicalClassification?.doubleQ() + "', '"
                        + it.commonNames?.doubleQ() + "', '"
                        + it.effectsClassification?.doubleQ() + "', '"
                        + it.description?.doubleQ() + "', '"
                        + it.imageUrl?.doubleQ() + "', '"
                        + it.basicsURL?.doubleQ() + "', '"
                        + it.effectsURL?.doubleQ() + "', '"
                        + it.imagesURL?.doubleQ() + "', '"
                        + it.healthURL?.doubleQ() + "', '"
                        + it.lawURL?.doubleQ() + "', '"
                        + it.doseURL?.doubleQ() + "', '"
                        + it.chemistryURL?.doubleQ() + "')")
            }
        }

        /**
         * Creates a herbIndex table in the erowid database and loads it with herb substance data
         * scraped using [Jerowidv2].
         */
        fun loadHerbTable() {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS herbIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(125), "
                    + "category VARCHAR(20)," + "botanicalClassification VARCHAR(200),"
                    + "commonNames VARCHAR(300)," + "effectsClassification VARCHAR(50),"
                    + "description VARCHAR(800)," + "imageURL VARCHAR(100),"
                    + "basicsURL VARCHAR(100)," + "effectsURL VARCHAR(100),"
                    + "imagesURL VARCHAR(100)," + "healthURL VARCHAR(100)," + "lawURL VARCHAR(100),"
                    + "doseURL VARCHAR(100)," + "chemistryURL VARCHAR(100))")

            jerowidv2.getAllHerbSubstances()?.forEach {
                statement.executeUpdate("INSERT INTO herbIndex VALUES ('"
                        + it.id + "', '"
                        + it.name.doubleQ() + "', '"
                        + it.url?.doubleQ() + "', '"
                        + it.category.toString().doubleQ() + "', '"
                        + it.botanicalClassification?.doubleQ() + "', '"
                        + it.commonNames?.doubleQ() + "', '"
                        + it.effectsClassification?.doubleQ() + "', '"
                        + it.description?.doubleQ() + "', '"
                        + it.imageUrl?.doubleQ() + "', '"
                        + it.basicsURL?.doubleQ() + "', '"
                        + it.effectsURL?.doubleQ() + "', '"
                        + it.imagesURL?.doubleQ() + "', '"
                        + it.healthURL?.doubleQ() + "', '"
                        + it.lawURL?.doubleQ() + "', '"
                        + it.doseURL?.doubleQ() + "', '"
                        + it.chemistryURL?.doubleQ() + "')")
            }
        }

        /**
         * Creates a pharmIndex table in the erowid database and loads it with pharm substance data
         * scraped using [Jerowidv2].
         */
        fun loadPharmTable() {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS pharmIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(125), "
                    + "category VARCHAR(20)," + "commonNames VARCHAR(150),"
                    + "effectsClassification VARCHAR(100)," + "chemicalName VARCHAR(150),"
                    + "description VARCHAR(500)," + "imageURL VARCHAR(150),"
                    + "basicsURL VARCHAR(100)," + "effectsURL VARCHAR(100),"
                    + "imagesURL VARCHAR(100)," + "healthURL VARCHAR(100)," + "lawURL VARCHAR(100),"
                    + "doseURL VARCHAR(100)," + "chemistryURL VARCHAR(100))")

            jerowidv2.getAllPharmSubstances()?.forEach {
                statement.executeUpdate("INSERT INTO pharmIndex VALUES ('"
                        + it.id + "', '"
                        + it.name.doubleQ() + "', '"
                        + it.url?.doubleQ() + "', '"
                        + it.category.toString().doubleQ() + "', '"
                        + it.commonNames?.doubleQ() + "', '"
                        + it.effectsClassification?.doubleQ() + "', '"
                        + it.chemicalName?.doubleQ() + "', '"
                        + it.description?.doubleQ() + "', '"
                        + it.imageUrl?.doubleQ() + "', '"
                        + it.basicsURL?.doubleQ() + "', '"
                        + it.effectsURL?.doubleQ() + "', '"
                        + it.imagesURL?.doubleQ() + "', '"
                        + it.healthURL?.doubleQ() + "', '"
                        + it.lawURL?.doubleQ() + "', '"
                        + it.doseURL?.doubleQ() + "', '"
                        + it.chemistryURL?.doubleQ() + "')")
            }
        }

        /**
         * Creates a smartIndex table in the erowid database and loads it with smart substance data
         * scraped using [Jerowidv2].
         */
        fun loadSmartTable() {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS smartIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(125), "
                    + "category VARCHAR(20)," + "commonNames VARCHAR(50),"
                    + "effectsClassification VARCHAR(75)," + "chemicalName VARCHAR(75),"
                    + "description VARCHAR(750)," + "imageURL VARCHAR(141),"
                    + "basicsURL VARCHAR(100)," + "effectsURL VARCHAR(100),"
                    + "imagesURL VARCHAR(100)," + "healthURL VARCHAR(100)," + "lawURL VARCHAR(100),"
                    + "doseURL VARCHAR(100)," + "chemistryURL VARCHAR(100))")

            jerowidv2.getAllSmartSubstances()?.forEach {
                statement.executeUpdate("INSERT INTO smartIndex VALUES ('"
                        + it.id + "', '"
                        + it.name.doubleQ() + "', '"
                        + it.url?.doubleQ() + "', '"
                        + it.category.toString().doubleQ() + "', '"
                        + it.commonNames?.doubleQ() + "', '"
                        + it.effectsClassification?.doubleQ() + "', '"
                        + it.chemicalName?.doubleQ() + "', '"
                        + it.description?.doubleQ() + "', '"
                        + it.imageUrl?.doubleQ() + "', '"
                        + it.basicsURL?.doubleQ() + "', '"
                        + it.effectsURL?.doubleQ() + "', '"
                        + it.imagesURL?.doubleQ() + "', '"
                        + it.healthURL?.doubleQ() + "', '"
                        + it.lawURL?.doubleQ() + "', '"
                        + it.doseURL?.doubleQ() + "', '"
                        + it.chemistryURL?.doubleQ() + "')")
            }
        }

        /**
         * Creates an animalIndex table in the erowid database and loads it with animal substance data
         * scraped using [Jerowidv2].
         */
        fun loadAnimalTable() {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS animalIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(125), "
                    + "category VARCHAR(20)," + "botanicalClassification VARCHAR(150),"
                    + "commonNames VARCHAR(50)," + "effectsClassification VARCHAR(50),"
                    + "description VARCHAR(550)," + "imageURL VARCHAR(150),"
                    + "basicsURL VARCHAR(100)," + "effectsURL VARCHAR(100),"
                    + "imagesURL VARCHAR(100)," + "healthURL VARCHAR(100)," + "lawURL VARCHAR(100),"
                    + "doseURL VARCHAR(100)," + "chemistryURL VARCHAR(100))")

            jerowidv2.getAllAnimalSubstances()?.forEach {
                statement.executeUpdate("INSERT INTO animalIndex VALUES ('"
                        + it.id + "', '"
                        + it.name.doubleQ() + "', '"
                        + it.url?.doubleQ() + "', '"
                        + it.category.toString().doubleQ() + "', '"
                        + it.botanicalClassification?.doubleQ() + "', '"
                        + it.commonNames?.doubleQ() + "', '"
                        + it.effectsClassification?.doubleQ() + "', '"
                        + it.description?.doubleQ() + "', '"
                        + it.imageUrl?.doubleQ() + "', '"
                        + it.basicsURL?.doubleQ() + "', '"
                        + it.effectsURL?.doubleQ() + "', '"
                        + it.imagesURL?.doubleQ() + "', '"
                        + it.healthURL?.doubleQ() + "', '"
                        + it.lawURL?.doubleQ() + "', '"
                        + it.doseURL?.doubleQ() + "', '"
                        + it.chemistryURL?.doubleQ() + "')")
            }
        }

        /**
         * Creates a basicsIndex table in the erowid database and loads it with basic page data
         * scraped using [Jerowidv2].
         */
        fun loadBasicsTable() {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS basicsIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(250), "
                    + "description VARCHAR(2100)," + "descriptionSections TEXT,"
                    + "effects VARCHAR(1500)," + "effectsSections VARCHAR(2400),"
                    + "problems VARCHAR(2400)," + "problemsSections TEXT,"
                    + "cautionDisclaimer VARCHAR(325))")

            jerowidv2.getAllBasicsPages()?.forEach {
                println(it.toString())
                statement.executeUpdate("INSERT INTO basicsIndex VALUES ('"
                        + it?.id + "', '"
                        + it?.name?.doubleQ() + "', '"
                        + it?.url?.doubleQ() + "', '"
                        + it?.description?.doubleQ() + "', '"
                        + it?.descriptionSections?.doubleQ() + "', '"
                        + it?.effects?.doubleQ() + "', '"
                        + it?.effectsSections?.doubleQ() + "', '"
                        + it?.problems?.doubleQ() + "', '"
                        + it?.problemsSections?.doubleQ() + "', '"
                        + it?.disclaimer?.doubleQ() + "')")
            }
        }

        /**
         * Creates an effectsIndex table in the erowid database and loads it with effects page data
         * scraped using [Jerowidv2].
         */
        fun loadEffectsTable() {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS effectsIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(125), "
                    + "duration VARCHAR(275)," + "positiveEffects VARCHAR(1325),"
                    + "neutralEffects VARCHAR(725)," + "negativeEffects VARCHAR(1650),"
                    + "description VARCHAR(9200)," + "experienceReports VARCHAR(6425),"
                    + "cautionDisclaimer VARCHAR(400))")

            jerowidv2.getAllEffectsPages()?.forEach {
                println(it.toString())
                statement.executeUpdate("INSERT INTO effectsIndex VALUES ('"
                        + it?.id + "', '"
                        + it?.name?.doubleQ() + "', '"
                        + it?.url?.doubleQ() + "', '"
                        + it?.durationChartUrls?.doubleQ() + "', '" // TODO fix this
                        + it?.positiveEffects?.doubleQ() + "', '"
                        + it?.neutralEffects?.doubleQ() + "', '"
                        + it?.negativeEffects?.doubleQ() + "', '"
                        + it?.description?.doubleQ() + "', '"
                        + it?.experienceReports?.doubleQ() + "', '"
                        + it?.disclaimer?.doubleQ() + "')")
            }
        }

        /**
         * Creates an imagesIndex table in the erowid database and loads it with images page data
         * scraped using [Jerowidv2].
         */
        fun loadImagesTable() {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS imagesIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(125), "
                    + "imageEntryList VARCHAR(20000))")

            jerowidv2.getAllImagesPages()?.forEach {
                println(it.toString())
                statement.executeUpdate("INSERT INTO imagesIndex VALUES ('"
                        + it?.id + "', '"
                        + it?.name?.doubleQ() + "', '"
                        + it?.url?.doubleQ() + "', '"
                        + it?.imageList?.doubleQ() + "')")
            }
        }

        /**
         * Creates a healthIndex table in the erowid database and loads it with health page data
         * scraped using [Jerowidv2].
         */
        fun loadHealthTable() { // TODO a health page is created for every substance
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS healthIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(125), "
                    + "notes TEXT," + "deaths VARCHAR(900)," + "warnings TEXT,"
                    + "cautions TEXT," + "benefits VARCHAR(1625))")

            jerowidv2.getAllHealthPages()?.forEach {
                println(it.toString())
                statement.executeUpdate("INSERT INTO healthIndex VALUES ('"
                        + it?.id + "', '"
                        + it?.name?.doubleQ() + "', '"
                        + it?.url?.doubleQ() + "', '"
                        + it?.notes?.doubleQ() + "', '"
                        + it?.deaths?.doubleQ() + "', '"
                        + it?.warnings?.doubleQ() + "', '"
                        + it?.cautions?.doubleQ() + "', '"
                        + it?.benefits?.doubleQ() + "')")
            }
        }

        /**
         * Creates a lawIndex table in the erowid database and loads it with law page data
         * scraped using [Jerowidv2].
         */
        fun loadLawTable() { // TODO a health page is created for every substance
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS lawIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(125), "
                    + "legalTable VARCHAR(175)," + "federalLawText TEXT,"
                    + "stateLaw TEXT," + "internationalLaw TEXT,"
                    + "cautionDisclaimer VARCHAR(1000))")

            jerowidv2.getAllLawPages()?.forEach {
                println(it.toString())
                statement.executeUpdate("INSERT INTO lawIndex VALUES ('"
                        + it?.id + "', '"
                        + it?.name?.doubleQ() + "', '"
                        + it?.url?.doubleQ() + "', '"
                        + it?.legalTable?.doubleQ() + "', '"
                        + it?.federalLaw?.doubleQ() + "', '"
                        + it?.stateLaw?.doubleQ() + "', '"
                        + it?.internationalLaw?.doubleQ() + "', '"
                        + it?.disclaimer?.doubleQ() + "')")
            }
        }

        /**
         * Creates a doseIndex table in the erowid database and loads it with dose page data
         * scraped using [Jerowidv2].
         */
        fun loadDoseTable() {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS doseIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(125), "
                    + "doseTable VARCHAR(750)," + "doseText VARCHAR(4775)," + "notes VARCHAR(2275),"
                    + "cautionDisclaimer VARCHAR(425))")

            jerowidv2.getAllDosePages()?.forEach {
                println(it.toString())
                statement.executeUpdate("INSERT INTO doseIndex VALUES ('"
                        + it?.id + "', '"
                        + it?.name?.doubleQ() + "', '"
                        + it?.url?.doubleQ() + "', '"
                        + it?.doseTable?.doubleQ() + "', '"
                        + it?.doseText?.doubleQ() + "', '"
                        + it?.notes?.doubleQ() + "', '"
                        + it?.disclaimer?.doubleQ() + "')")
            }
        }

        /**
         * Creates a chemistryIndex table in the erowid database and loads it with chemistry page data
         * scraped using [Jerowidv2].
         */
        fun loadChemistryTable() { // TODO chemistry isn't scrapping properly
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS chemistryIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(125), "
                    + "chemTable VARCHAR(950)," + "moleculeURL VARCHAR(100))")

            jerowidv2.getAllChemistryPages()?.forEach {
                println(it.toString())
                statement.executeUpdate("INSERT INTO chemistryIndex VALUES ('"
                        + it?.id + "', '"
                        + it?.name?.doubleQ() + "', '"
                        + it?.url?.doubleQ() + "', '"
                        + it?.chemTable?.doubleQ() + "', '"
                        + it?.moleculeUrl?.doubleQ() + "')")
            }
        }

        /**
         * Creates a researchChemicalIndex table in the erowid database and loads it with researchChemical page data
         * scraped using [Jerowidv2].
         */
        fun loadResearchChemicalTable() {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS researchChemicalIndex ("
                    + "id SMALLINT UNSIGNED," + "name VARCHAR(50), " + "url VARCHAR(125), "
                    + "summaryText VARCHAR(1575)," + "imageURL VARCHAR(75))")

            jerowidv2.getAllResearchChemicalPages()?.forEach {
                println(it.toString())
                statement.executeUpdate("INSERT INTO researchChemicalIndex VALUES ('"
                        + it?.id + "', '"
                        + it?.name?.doubleQ() + "', '"
                        + it?.url?.doubleQ() + "', '"
                        + it?.summary?.doubleQ() + "', '"
                        + it?.imageUrl?.doubleQ() + "')")
            }
        }
    }
}