package furhatos.app.vaccinationreceptionist.flow

import furhatos.app.vaccinationreceptionist.info
import furhatos.app.vaccinationreceptionist.nlu.*
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*

//　greeting + introduction + ask to start slot filling
val Start: State = state(Interaction) {

    onEntry {
        furhat.ask("Hi there. Welcome to the drop-in center for vaccination. I'm a self-service robot to help you go through some procedures of verification to see if you are in a good condition to receive a dose. If you have any questions, you can interrupt and ask me after we start the procedure. So, do you intend to receive a vaccination?")
    }

    onResponse<Yes> {
        furhat.say("We need some of your personal information to give you suggestions about the vaccination. Let's start.")
//        goto(General)
    }

    onResponse<No> {
        furhat.say("OK, take care of yourself, and be well. Bye.")
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

// All slot-filling states

// if succeeds, store info in the backend and thank the person
val End : State = state(parent = General) {
    //onEntry() {
    //}
}

val RefuseExplain : State = state(parent = General) {
    //onEntry() {
    //    when {
    //      if-else for different reasons
    //    }
    //    goto(idle)
    //}
}

val CallMedicalStaff : State = state(parent = General) {
    //onEntry() {
    //    furhat.say("")
    //    goto(idle)
    //}
}

val RequestFever : State = state(parent = General) {
    onEntry() {
        furhat.ask("Do you currently have an acute illness with fever?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestRecentVaccination : State = state(parent = General) {
    onEntry() {
        furhat.ask("Have your received any other vaccine in the past 7 days?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestAge : State = state(parent = General) {
    onEntry() {
        furhat.ask("How old are you?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestParentConsent : State = state(parent = General) {
    onEntry() {
        furhat.ask("Do you have the consent of your parents or a legal guardian?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestCountDose : State = state(parent = General) {
    onEntry() {
        furhat.ask("How many doses have you ever received till now?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestLastDoseDate : State = state(parent = General) {
    onEntry() {
        furhat.ask("When was your last dose? Please tell me the exact date.")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestLastDoseType : State = state(parent = General) {
    onEntry() {
        furhat.ask("What type of vaccine did you get last time?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestLastDoseReaction : State = state(parent = General) {
    onEntry() {
        furhat.ask("Did you develop an allergic reaction thereafter? Have you had any other unusual reactions after vaccination?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestInfection : State = state(parent = General) {
    onEntry() {
        furhat.ask("Has it been reliably proven that you were infected with the Covid-19 in the past?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestRecovery : State = state(parent = General) {
    onEntry() {
        furhat.ask("Did you recover from that?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestSixMonthsAfterRecovery : State = state(parent = General) {
    onEntry() {
        furhat.ask("Have you waited at least 6 months since recovery?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestImmunodeficiency : State = state(parent = General) {
    onEntry() {
        furhat.ask("Do you have chronic diseases or do you suffer from immunodeficiency (e.g., due to chemotherapy, immunosuppressive therapy or other medications)?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestAllergy : State = state(parent = General) {
    onEntry() {
        furhat.ask("Do you have any allergies that at some point have caused such severe reactions that you needed hospital care?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestSevereReaction : State = state(parent = General) {
    onEntry() {
        furhat.ask("Have you ever had a severe reaction to previous vaccinations that needed hospital care?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestBleeding : State = state(parent = General) {
    onEntry() {
        furhat.ask("Do you have an increased bleeding tendency due to disease or medicine?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestPregnant : State = state(parent = General) {
    onEntry() {
        furhat.ask("Are you pregnant?")
    }
    
    onResponse<Yes> {
        random(
                { furhat.say("Okay, you are pregnant.") }
        )
        users.current.info.pregnant = true
        goto(CheckEligibility)
    }

    onResponse<No> {
        random(
                { furhat.say("Okay, you are not pregnant.") }
        )
        users.current.info.pregnant = false
        goto(CheckEligibility)
    }
}

val RequestCountPregnancy : State = state(parent = General) {
    onEntry() {
        furhat.ask("For how many months have you been pregnant?")
    }

    onResponse<TellCountPregnancy> {
        furhat.say("Okay, ${it.intent.count_pregnancy} months.")
        users.current.info.count_pregnancy = it.intent.count_pregnancy
        goto(CheckEligibility)
    }
}

val RequestKnownDisease : State = state(parent = General) {
    onEntry() {
        furhat.ask("Do you have any known diseases?")
    }

    onResponse<Yes> {
        users.current.info.known_disease = true
        goto(CallMedicalStaff)
    }

    onResponse<No> {
        random(
                { furhat.say("Okay, no known diseases.") }
        )
        users.current.info.known_disease = false
        goto(RefuseExplain)
    }
}

val RequestConfirmMedicalInfo : State = state(parent = General) {
    onEntry() {
        furhat.ask("Are you sure that you have correctly answered all the questions?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestPersonalNum : State = state(parent = General) {
    onEntry() {
        furhat.ask("What is your personal number?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestName : State = state(parent = General) {
    onEntry() {
        furhat.ask("What is your full name?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(RefuseExplain)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CheckEligibility)
    //}
}

val RequestConsent : State = state(parent = General) {
    onEntry() {
        val typeVaccine = users.current.info.recommended_type
        furhat.ask("According to your conditions, we recommend $typeVaccine to you. Would you like to get vaccinated according to our recommendation?")
    }

    // go straight to RefuseExplain when not eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(CallMedicalStaff)
    //}

    // go back to CheckEligibility only when eligible
    //onResponse<example> {
    //    furhat.say("")
    //    users.current.info.attribute = it.intent.attribute
    //    goto(End)
    //}
}

// control the questions to ask
val CheckEligibility = state {
    onEntry {
        val info = users.current.info
        when {
            info.fever == null -> goto(RequestFever)
            info.recent_vaccination == null -> goto(RequestRecentVaccination)
            info.age.value == -1 -> goto(RequestAge)
            info.age.value!! < 18 && info.parent_consent == null -> goto(RequestParentConsent)
            info.count_dose.value == -1 -> goto(RequestCountDose)
            (info.count_dose.value == 1 || info.count_dose.value == 2) && info.last_dose_date == null -> goto(RequestLastDoseDate)
            info.last_dose_date != null && info.last_dose_type == null -> goto(RequestLastDoseType)
            info.last_dose_type != null && info.last_dose_reaction == null -> goto(RequestLastDoseReaction)
            info.infection == null -> goto(RequestInfection)
            info.infection == true && info.recovery == null -> goto(RequestRecovery)
            info.recovery != null && info.six_months_after_recovery == null -> goto(RequestSixMonthsAfterRecovery)
            info.immunodeficiency == null -> goto(RequestImmunodeficiency)
            info.allergy == null -> goto(RequestAllergy)
            info.severe_reaction == null -> goto(RequestSevereReaction)
            info.bleeding == null -> goto(RequestBleeding)
            info.pregnant == null -> goto(RequestPregnant)
            info.pregnant == true && info.count_pregnancy.value == -1 -> goto(RequestCountPregnancy)
            info.count_pregnancy.value != -1 && info.count_pregnancy.value!! < 4 && info.age.value!! <= 35 && info.known_disease == null -> goto(RequestKnownDisease)
            info.confirm_medical_info == null -> goto(RequestConfirmMedicalInfo)   // should be revised!!!
            info.personal_num == null -> goto(RequestPersonalNum)
            info.name == null -> goto(RequestName)
            info.consent == null -> goto(RequestConsent)
            else -> goto(CallMedicalStaff)
        }
    }
}
