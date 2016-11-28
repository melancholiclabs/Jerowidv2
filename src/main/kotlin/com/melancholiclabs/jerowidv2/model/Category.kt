package com.melancholiclabs.jerowidv2.model

import java.io.Serializable

/** Enum representing various substance categories for erowid.org substances. */
enum class Category : Serializable {
    /** Chemical category. */
    CHEMICALS,
    /** Plant category. */
    PLANTS,
    /** Herb category. */
    HERBS,
    /** Pharm category. */
    PHARMS,
    /** Smart category. */
    SMARTS,
    /** Animal category. */
    ANIMALS
}
