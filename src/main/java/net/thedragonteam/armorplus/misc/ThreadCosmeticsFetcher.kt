/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.misc

import net.thedragonteam.thedragonlib.util.LogHelper
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.*

class ThreadCosmeticsFetcher : Thread() {
    init {
        this.name = "ArmorPlus" + " cosmetics fetcher"
        this.isDaemon = true
        this.start()
    }

    override fun run() {
        LogHelper.info("Fetching cosmetics for people...")
        try {
            val url = URL("http://fdn.redstone.tech/TheDragonTeam/armorplus/thedragonteam.properties")
            val specialProperties = Properties()
            specialProperties.load(InputStreamReader(url.openStream()))
            CosmeticsRenderInit.parse(specialProperties)

            LogHelper.info("Fetching cosmetics for people done!")
        } catch (e: IOException) {
            LogHelper.error(ThreadCosmeticsFetcher::class.java.toString() + " Failed", e)
        }

    }
}