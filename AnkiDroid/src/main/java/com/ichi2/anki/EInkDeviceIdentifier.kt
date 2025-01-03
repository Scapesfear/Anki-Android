package com.ichi2.anki

import android.os.Build
import org.acra.ACRA
import timber.log.Timber
import java.util.Locale

class EInkDeviceIdentifier {
    data class DeviceInfo(
        private val originalManufacturer: String,
        private val originalModel: String,
    ) {
        val manufacturer: String
            get() = originalManufacturer.lowercase(Locale.ROOT).trim()

        val model: String
            get() = originalModel.lowercase(Locale.ROOT).trim()

        companion object {
            val current: DeviceInfo
                get() = DeviceInfo(Build.MANUFACTURER, Build.MODEL)
        }
    }

    private val knownEInkDevices = setOf(
        // Source: https://github.com/Hagb/decryptBooxUpdateUpx/blob/162c29f99bc6b725d1be265cfc17359aa5b55150/BooxKeys.csv
        DeviceInfo("onyx", "FLOW"),
        DeviceInfo("onyx", "FLOWPro"),
        DeviceInfo("onyx", "FLOW.Pro Max"),
        DeviceInfo("onyx", "GALILEO"),
        DeviceInfo("onyx", "Go103"),
        DeviceInfo("onyx", "Go6"),
        DeviceInfo("onyx", "GoColor7"),
        DeviceInfo("onyx", "MC_GULLIVER"),
        DeviceInfo("onyx", "KANT"),
        DeviceInfo("onyx", "Kon_Tiki2"),
        DeviceInfo("onyx", "Leaf"),
        DeviceInfo("onyx", "Leaf2"),
        DeviceInfo("onyx", "Leaf2_P"),
        DeviceInfo("onyx", "Leaf3"),
        DeviceInfo("onyx", "Leaf3C"),
        DeviceInfo("onyx", "LIVINGSTONE"),
        DeviceInfo("onyx", "Lomonosov"),
        DeviceInfo("onyx", "Max2"),
        DeviceInfo("onyx", "Max2Pro"),
        DeviceInfo("onyx", "Max3"),
        DeviceInfo("onyx", "MaxLumi"),
        DeviceInfo("onyx", "MaxLumi2"),
        DeviceInfo("onyx", "Note"),
        DeviceInfo("onyx", "Note2"),
        DeviceInfo("onyx", "Note3"),
        DeviceInfo("onyx", "NoteAir"),
        DeviceInfo("onyx", "NoteAir2"),
        DeviceInfo("onyx", "NoteAir2P"),
        DeviceInfo("onyx", "NoteAir3"),
        DeviceInfo("onyx", "NoteAir3C"),
        DeviceInfo("onyx", "NoteAir4C"),
        DeviceInfo("onyx", "NotePro"),
        DeviceInfo("onyx", "NoteS"),
        DeviceInfo("onyx", "NoteX"),
        DeviceInfo("onyx", "NoteX2"),
        DeviceInfo("onyx", "NoteX3"),
        DeviceInfo("onyx", "NoteX3S"),
        DeviceInfo("onyx", "NoteX3Pro"),
        DeviceInfo("onyx", "Note_YDT"),
        DeviceInfo("onyx", "Nova"),
        DeviceInfo("onyx", "Nova2"),
        DeviceInfo("onyx", "Nova3"),
        DeviceInfo("onyx", "Nova3Color"),
        DeviceInfo("onyx", "Nova5"),
        DeviceInfo("onyx", "NovaAir"),
        DeviceInfo("onyx", "NovaAir2"),
        DeviceInfo("onyx", "NovaAirC"),
        DeviceInfo("onyx", "NovaPlus"),
        DeviceInfo("onyx", "NovaPro"),
        DeviceInfo("onyx", "PadMuAP3"),
        DeviceInfo("onyx", "Page"),
        DeviceInfo("onyx", "Palma"),
        DeviceInfo("onyx", "Palma2"),
        DeviceInfo("onyx", "Poke2"),
        DeviceInfo("onyx", "Poke2Color"),
        DeviceInfo("onyx", "Poke3"),
        DeviceInfo("onyx", "Poke4"),
        DeviceInfo("onyx", "Poke4Lite"),
        DeviceInfo("onyx", "Poke4S"),
        DeviceInfo("onyx", "Poke5"),
        DeviceInfo("onyx", "Poke5P"),
        DeviceInfo("onyx", "Poke5S"),
        DeviceInfo("onyx", "Poke_Pro"),
        DeviceInfo("onyx", "SP_NoteS"),
        DeviceInfo("onyx", "SP_PokeL"),
        DeviceInfo("onyx", "T10C"),
        DeviceInfo("onyx", "Tab10"),
        DeviceInfo("onyx", "Tab10C"),
        DeviceInfo("onyx", "Tab10CPro"),
        DeviceInfo("onyx", "Tab13"),
        DeviceInfo("onyx", "Tab8"),
        DeviceInfo("onyx", "Tab8C"),
        DeviceInfo("onyx", "TabMiniC"),
        DeviceInfo("onyx", "TabUltra"),
        DeviceInfo("onyx", "TabUltraC"),
        DeviceInfo("onyx", "TabUltraCPro"),
        DeviceInfo("onyx", "TabX"),

        // Source: https://github.com/koreader/android-luajit-launcher/blob/6bba3f4bb4da8073d0f4ea4f270828c8603aa54d/app/src/main/java/org/koreader/launcher/device/DeviceInfo.kt
        DeviceInfo("boyue", "rk30sdk"),
        DeviceInfo("boeye", "rk30sdk"),
        DeviceInfo("fidibo", "fidibook"),
        DeviceInfo("hyread", "k06nu"),
        DeviceInfo("rockchip", "inkpalmplus"),
        DeviceInfo("onyx", "jdread"),
        DeviceInfo("linfiny", "ent-13t1"),
        DeviceInfo("haoqing", "m6"),
        DeviceInfo("haoqing", "m7"),
        DeviceInfo("haoqing", "p6"),
        DeviceInfo("rockchip", "moaanmix7"),
        DeviceInfo("onyx", "nabukreg_hd"),
        DeviceInfo("barnesandnoble", "bnrv1000"),
        DeviceInfo("barnesandnoble", "bnrv1100"),
        DeviceInfo("barnesandnoble", "bnrv1300"),
        DeviceInfo("barnesandnoble", "bnrv510"),
        DeviceInfo("barnesandnoble", "bnrv520"),
        DeviceInfo("barnesandnoble", "bnrv700"),
        DeviceInfo("barnesandnoble", "evk_mx6s1"),
        DeviceInfo("barnesandnoble","ereader"), // probably a eink device
        DeviceInfo("freescale", "bnrv510"),
        DeviceInfo("freescale", "bnrv520"),
        DeviceInfo("freescale", "bnrv700"),
        DeviceInfo("freescale", "evk_mx6s1"),
        DeviceInfo("onyx", "rk30sdk"),
        DeviceInfo("onyx", "mc_note4"),
        DeviceInfo("rockchip", "pubook"),
        DeviceInfo("sony", "dpt-cp1"),
        DeviceInfo("sony", "dpt-rp1"),
        DeviceInfo("onyx", "tagus_pokep"),
        DeviceInfo("xiaomi", "xiaomi_reader"),
        DeviceInfo("artatech", "pri"), // probably a eink device
        DeviceInfo("crema", "crema-0710c"), // probably a eink device
        DeviceInfo("crema", "crema-0670c"), // probably a eink device

        // Source: https://github.com/plotn/coolreader/blob/e5baf0607e678468aa045053ba5f092164aa1dd7/android/src/org/coolreader/crengine/DeviceInfo.java
        DeviceInfo("barnesandnoble", "NOOK"),
        DeviceInfo("barnesandnoble", "bnrv350"),
        DeviceInfo("barnesandnoble", "bnrv300"),
        DeviceInfo("barnesandnoble", "bnrv500"),
        DeviceInfo("sony", "PRS-T"), // probably a eink device
        DeviceInfo("dns", "DNS Airbook EGH"),

        // Source: https://github.com/ankidroid/Anki-Android/issues/17618
        DeviceInfo("Viwoods", "Viwoods AiPaper"),
    )

    private val eInkManufacturersList = setOf(
        "onyx",
        "boyue",
        "boeye",
        "fidibo",
        "hyread",
        "rockchip",
        "linfiny",
        "haoqing",
        "sony",
        "barnesandnoble",
        "freescale",
        "Viwoods",
        "artatech",
        "dns",
        "crema",
        "kindle",
        "bigme",
    )

    /**
     * @return `true` if a match is found, `false` otherwise.
     */
    // Checks if the device has an E-Ink display by matching its manufacturer and model.
    fun isEInkDevice(): Boolean {
        val currentDevice = DeviceInfo.current
        Timber.v("Checking device: $currentDevice")

        var isExactMatch = false
        var isPartialMatch = false

        // Check if the device is an exact match or a partial match.
        for (device in knownEInkDevices) {
            // Check if the device is an exact match.
            if (currentDevice.manufacturer == device.manufacturer &&
                currentDevice.model == device.model) {
                isExactMatch = true
                break
            }
            // Check if the device is a partial match. Partial matches are detected using substring matching.
            if ((currentDevice.manufacturer.contains(device.manufacturer) ||
                        device.manufacturer.contains(currentDevice.manufacturer)) &&
                (currentDevice.model.contains(device.model) ||
                        device.model.contains(currentDevice.model))) {
                isPartialMatch = true
            }
        }

        if (isExactMatch) {
            Timber.d("Confirmed E-ink device: $currentDevice")
            return true
        }
        //if the device is a partial match or if the manufacturer is in the list of known E-ink manufacturers then report it
        if (isPartialMatch || eInkManufacturersList.contains(currentDevice.manufacturer)) {
            Timber.w("Potential E-ink device: $currentDevice")
            ACRA.errorReporter.handleSilentException(Exception("Potential E-ink device: $currentDevice"))
        }

        return false
    }
}