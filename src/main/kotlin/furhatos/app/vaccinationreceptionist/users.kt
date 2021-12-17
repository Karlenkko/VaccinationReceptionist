package furhatos.app.vaccinationreceptionist

import furhatos.app.vaccinationreceptionist.nlu.ReceiveVaccination
import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.records.User


val User.info by NullSafeUserDataDelegate { ReceiveVaccination() }

