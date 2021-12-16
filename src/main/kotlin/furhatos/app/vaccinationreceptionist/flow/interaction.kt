package furhatos.app.vaccinationreceptionist.flow

import furhatos.app.vaccinationreceptionist.nlu.*
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*

//　greeting + introduction + ask to start slot filling
val Start: State = state(Interaction) {

    onEntry {
        furhat.ask("Hi there. Do you like robots?")
    }

    onResponse<Yes> {
        furhat.say("I like humans.")
//        goto(General)
    }

    onResponse<No> {
        furhat.say("OK, take care of yourself, and be well.")
        goto(Idle)
    }
}

// parent state for common answers
val General: State = state(Interaction) {
//    onEntry {
//        random(
//                { furhat.ask("How about some fruits?") },
//                { furhat.ask("Do you want some fruits?") }
//        )
//    }
    onResponse<RequestSideEffects> {
        raise(TellSideEffects())
    }
    onEvent<TellSideEffects> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestCOVIDSymptoms> {
        raise(TellCOVIDSymptoms())
    }
    onEvent<TellCOVIDSymptoms> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestBooster> {
        raise(TellBooster())
    }
    onEvent<TellBooster> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestBreastFeeding> {
        raise(TellBreastFeeding())
    }
    onEvent<TellBreastFeeding> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestPregnancy> {
        raise(TellPregnancy())
    }
    onEvent<TellPregnancy> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestVaccineProtection> {
        raise(TellVaccineProtection())
    }
    onEvent<TellVaccineProtection> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestVaccine> {
        raise(TellVaccine())
    }
    onEvent<TellVaccine> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestVaccineSafety> {
        raise(TellVaccineSafety())
    }
    onEvent<TellVaccineSafety> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestNationality> {
        raise(TellNationality())
    }
    onEvent<TellNationality> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestVaccinationFee> {
        raise(TellVaccinationFee())
    }
    onEvent<TellVaccinationFee> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestAfterVaccinationRegulation> {
        raise(TellAfterVaccinationRegulation())
    }
    onEvent<TellAfterVaccinationRegulation> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestVaccinationCertificate> {
        raise(TellVaccinationCertificate())
    }
    onEvent<TellVaccinationCertificate> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestVaccineType> {
        raise(TellVaccineType())
    }
    onEvent<TellVaccineType> {
        furhat.say(it.resp)
        reentry()
    }
}
