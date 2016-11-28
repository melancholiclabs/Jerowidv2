package com.melancholiclabs.jerowidv2.extension

import org.jsoup.Jsoup
import org.jsoup.UnsupportedMimeTypeException
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.lang.UnsupportedOperationException

/**
 * Returns a Jsoup document using the string as the url.
 *
 * @return jsoup document fetched using the given string as the url.
 */
fun String.getJsoupDoc(): Document? {
    try {
        return Jsoup.connect(this).timeout(10000).ignoreHttpErrors(true).get()
    } catch (e: UnsupportedMimeTypeException) {
        return null
    }
}

/**
 * Gets the text of the first selected query of the Element.
 *
 * @return text of the first selected query of the Element.
 */
fun Element.selectFirstText(query: String) = select(query)?.first()?.text()

/**
 * Returns a string with formatting applicable for use with mysql.
 *
 * @return [String] formatted for use with mysql.
 */
fun String.reformat() = trim().replace(" +".toRegex(), " ").replace("\\r\\n|\\r|\\n".toRegex(), " ")

/**
 * Returns a tab/new-line deliminated string of the given Element representing an html table.
 *
 * @return string of the table formatted using tab/new-line delimiters.
 */
fun Element.tableToString(): String? {
    try {
        return select("tr")
                ?.map { it.select("> td|th").map { "${it.text()} \t" }.reduceRight { s1, s2 -> "${s1 + s2.trim()} \n" } }
                ?.reduceRight { s1, s2 -> s1 + s2 }
                ?.trim()
    } catch (e: UnsupportedOperationException) {
        return null // TODO not the best way to handle this
    }
}

/**
 * Gets the given string with all (') single quotes replaced by ('') two single quotes.
 *
 * @return string with all (') single quotes replaced by ('') two single quotes.
 */
fun String.doubleQ(): String = replace("'", "''")

/**
 * Runs all functions passed as arguments so that multiple void function calls can be invoked in a single line.
 *
 * @param[unit] array of [Unit] objects that will be invoked.
 */
fun invokeAll(vararg unit: Unit) = unit.reduceRight { f1, f2 -> f1.apply { f2.apply { } } }
