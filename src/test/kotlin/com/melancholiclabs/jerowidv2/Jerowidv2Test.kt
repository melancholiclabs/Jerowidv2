package com.melancholiclabs.jerowidv2

import com.melancholiclabs.jerowidv2.jerowid.Jerowidv2

/** Created by Melancholic Labs on 11/24/2016. */
class Jerowidv2Test {

    /** Object providing static access within [Jerowidv2Test]. */
    companion object {

        /**
         * Main method which deletes an existing erowid database, creates a new erowid database, and updates index tables with data scraped using [Jerowidv2].
         *
         * @param[args] command line arguments.
         */
        @JvmStatic fun main(args: Array<String>) {
            println(Jerowidv2().getAllBasicSubstances().toString())
        }
    }
}