package io.untungs.nutrisport.profile.util

import androidx.compose.ui.graphics.vector.ImageVector
import io.untungs.nutrisport.core.domain.model.Country
import io.untungs.nutrisport.core.ui.flags.Flags
import io.untungs.nutrisport.core.ui.flags.India
import io.untungs.nutrisport.core.ui.flags.Indonesia
import io.untungs.nutrisport.core.ui.flags.Usa

val Country.flagIcon: ImageVector
    get() = when (this) {
        Country.INDONESIA -> Flags.Indonesia
        Country.INDIA -> Flags.India
        Country.USA -> Flags.Usa
    }