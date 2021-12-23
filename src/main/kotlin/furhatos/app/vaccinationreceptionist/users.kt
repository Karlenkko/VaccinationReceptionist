package furhatos.app.vaccinationreceptionist

import furhatos.app.vaccinationreceptionist.flow.ReentryCount
import furhatos.app.vaccinationreceptionist.flow.UserCorrectionIntentCount
import furhatos.app.vaccinationreceptionist.flow.UserFAQIntentCount
import furhatos.app.vaccinationreceptionist.nlu.ReceiveVaccination
import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.records.User


val User.info by NullSafeUserDataDelegate { ReceiveVaccination() }

val User.reentryCount by NullSafeUserDataDelegate { ReentryCount() }

val User.userCorrectionIntentCount by NullSafeUserDataDelegate { UserCorrectionIntentCount() }

val User.userFAQIntentCount by NullSafeUserDataDelegate { UserFAQIntentCount() }