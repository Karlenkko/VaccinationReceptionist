package furhatos.app.vaccinationreceptionist.flow

import furhatos.app.vaccinationreceptionist.info
import furhatos.app.vaccinationreceptionist.nlu.*
import furhatos.app.vaccinationreceptionist.reentryCount
import furhatos.app.vaccinationreceptionist.userCorrectionIntentCount
import furhatos.app.vaccinationreceptionist.userFAQIntentCount
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.Number
import java.util.*

var reentryFromFAQorModification: Boolean = false
//　greeting + introduction + ask to start slot filling
val Start: State = state(Interaction) {
    onEntry {
        furhat.ledStrip.solid(java.awt.Color.WHITE)
        furhat.ask("Hi there. Welcome to the drop-in center for vaccination. I'm a self-service robot to help you go through some procedures of verification to see if you are in a good condition to receive a dose. If you have any questions, you can interrupt and ask me after we start the procedure. So, do you intend to receive a vaccination?")
    }

    onResponse<Yes> {
        furhat.say("We need some of your personal information to give you suggestions about the vaccination. Let's start.")
        goto(CheckEligibility)
    }

    onResponse<No> {
        furhat.say("Take care of yourself, and be well. Bye.")
        goto(Idle)
    }

    onReentry {
        furhat.ask("Do you intend to receive a vaccination?")
    }
}

// parent state for common answers
val General: State = state(Interaction) {
    onResponse<RequestSideEffects> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_side_effects_count += 1
        raise(TellSideEffects())
    }
    onEvent<TellSideEffects> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestCOVIDSymptoms> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_covid_symptoms_count += 1
        raise(TellCOVIDSymptoms())
    }
    onEvent<TellCOVIDSymptoms> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestBooster> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_booster_count += 1
        raise(TellBooster())
    }
    onEvent<TellBooster> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestBreastFeeding> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_breast_feeding_count += 1
        raise(TellBreastFeeding())
    }
    onEvent<TellBreastFeeding> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestPregnancy> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_pregnancy_count += 1
        raise(TellPregnancy())
    }
    onEvent<TellPregnancy> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestVaccineProtection> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_vaccine_protection_count += 1
        raise(TellVaccineProtection())
    }
    onEvent<TellVaccineProtection> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestVaccine> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_vaccine_count += 1

        raise(TellVaccine())
    }
    onEvent<TellVaccine> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestVaccineSafety> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_vaccine_safety_count += 1

        raise(TellVaccineSafety())
    }
    onEvent<TellVaccineSafety> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestNationality> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_nationality_count += 1

        raise(TellNationality())
    }
    onEvent<TellNationality> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestVaccinationFee> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_vaccination_fee_count += 1

        raise(TellVaccinationFee())
    }
    onEvent<TellVaccinationFee> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestAfterVaccinationRegulation> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_after_vaccination_regulation_count += 1

        raise(TellAfterVaccinationRegulation())
    }
    onEvent<TellAfterVaccinationRegulation> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestVaccinationCertificate> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_vaccination_certificate_count += 1

        raise(TellVaccinationCertificate())
    }
    onEvent<TellVaccinationCertificate> {
        furhat.say(it.resp)
        reentry()
    }

    onResponse<RequestVaccineType> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_vaccine_type_count += 1

        raise(TellVaccineType())
    }
    onEvent<TellVaccineType> {
        furhat.say(it.resp)
        reentry()
    }

    // change age
    onResponse<TellAgeFormally> {
        reentryFromFAQorModification = true
        users.current.userCorrectionIntentCount.tell_age_count += 1
        furhat.gesture(Gestures.Oh)
        furhat.say("You are ${it.intent.age} years old.")
        users.current.info.age = it.intent.age
        users.current.info.parent_consent = null
        if (users.current.info.age.value!! < 12) {
            goto(RefuseExplain)
        } else {
            goto(CheckEligibility)
        }
    }

    onResponse<TellNotPregnant> {
        reentryFromFAQorModification = true
        users.current.userCorrectionIntentCount.tell_pregnancy_count += 1
        furhat.gesture(Gestures.Oh)
        furhat.say("I see. You are not pregnant.")
        users.current.info.pregnant = false
        users.current.info.count_pregnancy.value = -1
        users.current.info.known_disease = null
        goto(CheckEligibility)
    }

    onResponse<TellIsPregnant> {
        reentryFromFAQorModification = true
        users.current.userCorrectionIntentCount.tell_pregnancy_count += 1
        furhat.gesture(Gestures.Oh)
        furhat.say("I see. You are pregnant.")
        users.current.info.pregnant = true
        users.current.info.count_pregnancy.value = -1
        users.current.info.known_disease = null
        goto(CheckEligibility)
    }

    onResponse<TellNotAnyDose> {
        reentryFromFAQorModification = true
        users.current.userCorrectionIntentCount.tell_number_dose_count += 1
        furhat.gesture(Gestures.Oh)
        furhat.say("OK, you haven't received any vaccine against Covid 19.")
        users.current.info.count_dose.value = 0
        users.current.info.last_dose_date = null
        users.current.info.last_dose_type = null
        users.current.info.last_dose_reaction = null
        goto(CheckEligibility)
    }

    onResponse<TellNumberDoseFormally> {
        reentryFromFAQorModification = true
        users.current.userCorrectionIntentCount.tell_number_dose_count += 1
        users.current.info.last_dose_date = null
        users.current.info.last_dose_type = null
        users.current.info.last_dose_reaction = null
        users.current.info.count_dose = it.intent.dose
        furhat.say("I see, you have received ${it.intent.dose} doses.")
        if (users.current.info.count_dose.value!! > 2 || (users.current.info.count_dose.value!! == 2 && users.current.info.age.value!! < 18 )) {
            goto(RefuseExplain)
        } else if (users.current.info.count_dose.value == 0) {
            reentry()
        } else {
            goto(CheckEligibility)
        }
    }

    onResponse<TellIsInfected> {
        reentryFromFAQorModification = true
        users.current.userCorrectionIntentCount.tell_infection_count += 1
        furhat.gesture(Gestures.Oh)
        furhat.say("I see, you are currently infected with Covid 19.")
        users.current.info.infection = true
        users.current.info.recovery = false
        users.current.info.six_months_after_recovery = null
        goto(RefuseExplain)
    }

    onResponse<TellWasInfected> {
        reentryFromFAQorModification = true
        users.current.userCorrectionIntentCount.tell_infection_count += 1
        furhat.gesture(Gestures.Oh)
        furhat.say("I see, you have Covid 19 infection history.")
        users.current.info.infection = true
        users.current.info.recovery = null
        users.current.info.six_months_after_recovery = null
        goto(CheckEligibility)
    }
}

// All slot-filling states

// if succeeds, store info in the backend and thank the person
val End : State = state(parent = General) {
    onEntry() {
        random(
                { furhat.say("Please go to the waiting room. You will get vaccinated very soon! Have a nice day!") }
        )
        sendToElasticsearch(users.current.info)
        sendReentryLogToElasticsearch(users.current.reentryCount)
        sendCorrectionIntentToElasticsearch(users.current.userCorrectionIntentCount)
        sendFAQIntentToElasticsearch(users.current.userFAQIntentCount)
        goto(Idle)
    }
}

val RefuseExplain : State = state(parent = General) {
    onEntry() {
        furhat.ledStrip.solid(java.awt.Color.RED)
        furhat.gesture(Gestures.ExpressSad(duration = 1.0))
        val info = users.current.info
        when {
            info.fever == true -> furhat.say("Due to regulations, you need to recover from the fever.")
            info.recent_vaccination == true -> furhat.say("You need to wait for at least a week after your first dose, or 6 month after the second.")
            info.age.value!! < 12 -> furhat.say("You need to wait till you are more than 12 years old.")
            info.count_dose.value!! > 2 -> furhat.say("You have received all doses, including the booster.")
            (info.count_dose.value!! == 2 && info.age.value!! < 18) -> furhat.say("You are less than 18 years old, and there is no need for a third dose.")
            (info.count_dose.value!! == 1 && info.short_interval == true) -> furhat.say("You need to wait for at least 7 days after the first dose.")
            (info.count_dose.value!! == 2 && info.short_interval == true) -> furhat.say("You need to wait for at least 6 months after the second dose.")
            info.recovery == false -> furhat.say("You should wait for 6 months after you get fully recovered from the covid-19 symptoms.")
            info.six_months_after_recovery == false -> furhat.say("You should wait for 6 months after you get fully recovered from the covid-19 symptoms.")
            info.known_disease == false -> furhat.say("Pregnant women under 35 years of age and without risk factors are recommended to be vaccinated after week 12")
        }
        furhat.say("If you have further questions, please consult your doctor. Take care of yourself, and be well. Bye.")
        sendToElasticsearch(users.current.info)
        sendReentryLogToElasticsearch(users.current.reentryCount)
        sendCorrectionIntentToElasticsearch(users.current.userCorrectionIntentCount)
        sendFAQIntentToElasticsearch(users.current.userFAQIntentCount)

        goto(Idle)
    }
}

val CallMedicalStaff : State = state(parent = General) {
    onEntry() {
        furhat.ledStrip.solid(java.awt.Color.YELLOW)
        furhat.gesture(Gestures.Thoughtful(duration = 1.0))
        furhat.say("Sorry, I can't handle your situation.")
        furhat.say("Don't worry, our medical staff is waiting for you on the right hand side. Please consult the staff for further steps. Bye")
        sendToElasticsearch(users.current.info)
        sendReentryLogToElasticsearch(users.current.reentryCount)
        sendCorrectionIntentToElasticsearch(users.current.userCorrectionIntentCount)
        sendFAQIntentToElasticsearch(users.current.userFAQIntentCount)

        goto(Idle)
    }
}

val RequestFever : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.fever_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("Do you currently have an acute illness with fever?")
    }

    // go straight to RefuseExplain when not eligible
    onResponse<Yes> {
        furhat.say("I see, currently you have acute illness with fever.")
        users.current.info.fever = true
        goto(RefuseExplain)
    }

    onResponse<TellHaveProblem> {
        furhat.say("I see, currently you have acute illness with fever.")
        users.current.info.fever = true
        goto(RefuseExplain)
    }

    // go back to CheckEligibility only when eligible
    onResponse<No> {
        furhat.say("Great, you have no acute illness with fever")
        users.current.info.fever = false
        goto(CheckEligibility)
    }

    onResponse<TellNoProblem> {
        furhat.say("Great, you have no acute illness with fever")
        users.current.info.fever = false
        goto(CheckEligibility)
    }
}

val RequestRecentVaccination : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.recent_vaccination_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("Have your received any other vaccine in the past 7 days?")
    }

    // go straight to RefuseExplain when not eligible
    onResponse<Yes> {
        furhat.say("You do have received a dose in the last week.")
        users.current.info.recent_vaccination = true
        goto(RefuseExplain)
    }

    // go back to CheckEligibility only when eligible
    onResponse<No> {
        furhat.say("OK, no vaccination in the last 7 days.")
        users.current.info.recent_vaccination = false
        goto(CheckEligibility)
    }
}

val RequestAge : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.age_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("How old are you?")
    }

    onResponse<TellAge> {
        furhat.say("Okay, you are ${it.intent.age} years old.")
        users.current.info.age = it.intent.age
        if (users.current.info.age.value!! < 12) {
            goto(RefuseExplain)
        } else {
            goto(CheckEligibility)
        }
    }
}

val RequestParentConsent : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.parent_consent_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("Do you have the consent of your parents or a legal guardian?")
    }

    // go straight to RefuseExplain when not eligible
    onResponse<Yes> {
        furhat.say("Great, you shall need to show the certificate later.")
        users.current.info.parent_consent = true
        goto(CheckEligibility)
    }

    // go back to CheckEligibility only when eligible
    onResponse<No> {
        furhat.say("It's ok, but you need to meet our medical staff now.")
        users.current.info.parent_consent = false
        goto(CallMedicalStaff)
    }
}

val RequestCountDose : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.count_dose_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("How many doses of vaccines against Covid 19 have you ever received till now?")
    }

    // go straight to RefuseExplain when not eligible
    onResponse<TellNumberDose> {
        users.current.info.count_dose = it.intent.dose
        furhat.say("I see, you have received ${it.intent.dose} doses.")
        if (users.current.info.count_dose.value!! > 2 || (users.current.info.count_dose.value!! == 2 && users.current.info.age.value!! < 18 )) {
            goto(RefuseExplain)
        } else {
            goto(CheckEligibility)
        }
    }

    onResponse<TellNotAnyDose> {
        users.current.info.count_dose = Number(0)
        furhat.say("I see, you have not yet received any dose.")
        goto(CheckEligibility)
    }
}

val RequestLastDoseDate : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.last_dose_date_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        val date = furhat.askFor<furhatos.nlu.common.Date>("When was your last dose? Please tell me the exact date.") {
            onResponse<DontKnow> {
                furhat.say("That is important, you should really know that!")
                reentry()
            }
        }
        users.current.info.last_dose_date = date
        furhat.say("Your last dose was on $date.")
        val today = Calendar.getInstance()
        val converted_last_dose_date = Calendar.getInstance()
        val arr = users.current.info.last_dose_date?.value?.split("-")
        converted_last_dose_date.set(Integer.parseInt(arr?.get(0)), Integer.parseInt(arr?.get(1)) - 1, Integer.parseInt(arr?.get(2)))
        if (converted_last_dose_date > today) {
            converted_last_dose_date.add(Calendar.YEAR, -1)
        }
        var temp = converted_last_dose_date
        temp.add(Calendar.DATE, 7)
        users.current.info.short_interval = false
        if (temp > today && users.current.info.count_dose.value!! == 1) {
            users.current.info.short_interval = true
            goto(RefuseExplain)
        }
        temp = converted_last_dose_date
        temp.add(Calendar.MONTH, 6)
        if (temp > today && users.current.info.count_dose.value!! == 2) {
            users.current.info.short_interval = true
            goto(RefuseExplain)
        }

        goto(CheckEligibility)
    }
}

val RequestLastDoseType : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.last_dose_type_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("What type of vaccine did you get last time?")
    }

    onResponse<Vaccine> {
        users.current.info.last_dose_type = it.intent
        furhat.say("OK, you had ${it.intent} last time.")
        goto(CheckEligibility)
    }
}

val RequestLastDoseReaction : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.last_dose_reaction_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("Did you develop an allergic reaction thereafter? Have you had any other unusual reactions after vaccination?")
    }

    // go straight to CallMedicalStaff when not eligible
    onResponse<Yes> {
        furhat.say("Could you please give more details about the symptoms to our medical staff?")
        users.current.info.last_dose_reaction = true
        goto(CallMedicalStaff)
    }

    // go back to CheckEligibility only when eligible
    onResponse<No> {
        furhat.say("Great, you had no unusual reactions.")
        users.current.info.last_dose_reaction = false
        goto(CheckEligibility)
    }
}

val RequestInfection : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.infection_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("Has it been reliably proven that you were infected with the Covid-19 in the past?")
    }

    onResponse<Yes> {
        furhat.say("I see.")
        users.current.info.infection = true
        goto(CheckEligibility)
    }

    onResponse<No> {
        furhat.say("OK, you haven't been infected with the Covid-19 in the past.")
        users.current.info.infection = false
        goto(CheckEligibility)
    }
}

val RequestRecovery : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.recovery_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("Did you recover from that?")
    }

    // go straight to RefuseExplain when not eligible
    onResponse<No> {
        furhat.say("OK, you still have symptoms of covid-19.")
        users.current.info.recovery = false
        goto(RefuseExplain)
    }

    // go back to CheckEligibility only when eligible
    onResponse<Yes> {
        furhat.say("Now you feel good.")
        users.current.info.recovery = true
        goto(CheckEligibility)
    }
}

val RequestSixMonthsAfterRecovery : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.six_months_after_recovery_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("Have you waited at least 6 months since recovery?")
    }

    // go straight to RefuseExplain when not eligible
    onResponse<No> {
        furhat.say("You recovered less than 6 months ago.")
        users.current.info.six_months_after_recovery = false
        goto(RefuseExplain)
    }

    // go back to CheckEligibility only when eligible
    onResponse<Yes> {
        furhat.say("OK, you recovered more than 6 months ago.")
        users.current.info.six_months_after_recovery = true
        goto(CheckEligibility)
    }
    onResponse<DontKnow> {
        furhat.say("In this case, please look for our medical staff.")
        goto(CallMedicalStaff)
    }
}

val RequestImmunodeficiency : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.immunodeficiency_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("Do you have chronic diseases or do you suffer from immunodeficiency (e.g., due to chemotherapy, immunosuppressive therapy or other medications)?")
    }

    onResponse<Yes> {
        random(
                { furhat.say("You have higher chance of being seriously ill due to Covid 19, therefore we advice you to receive extra booster dose after the second dose.") }
        )
        users.current.info.immunodeficiency = true
        goto(CheckEligibility)
    }

    onResponse<No> {
        random(
                { furhat.say("Okay, no such diseases.") }
        )
        users.current.info.immunodeficiency = false
        goto(CheckEligibility)
    }
}

val RequestAllergy : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.allergy_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("Do you have any allergies that at some point have caused such severe reactions that you needed hospital care?")
    }

    onResponse<Yes> {
        random(
                { furhat.say("Okay, you have experienced severe allergic reactions.") }
        )
        users.current.info.allergy = true
        goto(CallMedicalStaff)
    }

    onResponse<TellHaveProblem> {
        random(
                { furhat.say("Okay, you have experienced severe allergic reactions.") }
        )
        users.current.info.allergy = true
        goto(CallMedicalStaff)
    }

    onResponse<No> {
        random(
                { furhat.say("Okay, you don't have.") }
        )
        users.current.info.allergy = false
        goto(CheckEligibility)
    }

    onResponse<TellNoProblem> {
        random(
                { furhat.say("Okay, you don't have.") }
        )
        users.current.info.allergy = false
        goto(CheckEligibility)
    }
}

val RequestSevereReaction : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.severe_reaction_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("Have you ever had a severe reaction to previous vaccinations that needed hospital care?")
    }

    onResponse<Yes> {
        random(
                { furhat.say("Okay, you have had a severe reaction to previous vaccinations.") }
        )
        users.current.info.severe_reaction = true
        goto(CallMedicalStaff)
    }

    onResponse<TellHaveProblem> {
        random(
                { furhat.say("Okay, you have had a severe reaction to previous vaccinations.") }
        )
        users.current.info.severe_reaction = true
        goto(CallMedicalStaff)
    }

    onResponse<No> {
        random(
                { furhat.say("Okay, you haven't had any.") }
        )
        users.current.info.severe_reaction = false
        goto(CheckEligibility)
    }

    onResponse<TellNoProblem> {
        random(
                { furhat.say("Okay, you haven't had any.") }
        )
        users.current.info.severe_reaction = false
        goto(CheckEligibility)
    }
}

val RequestBleeding : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.bleeding_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("Do you have an increased bleeding tendency due to disease or medicine?")
    }

    onResponse<Yes> {
        random(
                { furhat.say("You are still qualified to get vaccinated, but please inform our medical staff of your conditions so that they can pay special attention.") }
        )
        users.current.info.bleeding = true
        goto(CheckEligibility)
    }

    onResponse<TellHaveProblem> {
        random(
                { furhat.say("You are still qualified to get vaccinated, but please inform our medical staff of your conditions so that they can pay special attention.") }
        )
        users.current.info.bleeding = true
        goto(CheckEligibility)
    }

    onResponse<No> {
        random(
                { furhat.say("Okay, no increased bleeding tendency.") }
        )
        users.current.info.bleeding = false
        goto(CheckEligibility)
    }

    onResponse<TellNoProblem> {
        random(
                { furhat.say("Okay, no increased bleeding tendency.") }
        )
        users.current.info.bleeding = false
        goto(CheckEligibility)
    }
}

val RequestPregnant : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.pregnant_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("Are you pregnant?")
    }

    onResponse<TellNotPregnant> {
        random(
                { furhat.say("Okay, you are not pregnant.") }
        )
        users.current.info.pregnant = false
        goto(CheckEligibility)
    }

    onResponse<No> {
        random(
                { furhat.say("Okay, you are not pregnant.") }
        )
        users.current.info.pregnant = false
        goto(CheckEligibility)
    }

    onResponse<Yes> {
        random(
                { furhat.say("Okay, you are pregnant.") }
        )
        users.current.info.pregnant = true
        goto(CheckEligibility)
    }
}

val RequestCountPregnancy : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.count_pregnancy_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
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
        if (!reentryFromFAQorModification) {
        users.current.reentryCount.known_disease_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        furhat.ask("Do you have any known diseases or risk factors like obesity, high blood pressure or diabetes?")
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

    onResponse<Yes> {
        users.current.info.confirm_medical_info = true
        goto(CheckEligibility)
    }

    onResponse<No> {
        furhat.say("Don't worry. Let me ask you something.")
        goto(RequestChangeAge)
    }
}

val RequestChangeAge : State = state(parent = General) {
    onEntry() {
        furhat.say("Your age is ${users.current.info.age.value}.")
        if (users.current.info.parent_consent == true) {
            furhat.say("And you have the consent of your parents or a legal guardian.")
        }
        random(
                { furhat.ask("It is correct?") },
                { furhat.ask("Am I correct?") }
        )
    }

    onResponse<Yes> {
        goto(RequestChangeDoseHistory)
    }

    onResponse<No> {
        furhat.gesture(Gestures.Nod(strength=0.6))
        users.current.info.age.value = -1
        users.current.info.parent_consent = null
        furhat.say("It's noted. We will come back to these questions later.")
        goto(RequestChangeDoseHistory)
    }
}

val RequestChangeDoseHistory : State = state(parent = General) {
    onEntry() {
        furhat.say("You haven't received any kind of vaccine in the past 7 days.")
        furhat.say("You have already received ${users.current.info.count_dose.value} dose against Covid 19.")
        if (users.current.info.last_dose_date != null) {
            furhat.say("Your most recent dose was on ${users.current.info.last_dose_date}.")
            furhat.say("You had ${users.current.info.last_dose_type} last time and nothing unusual happened thereafter.")
        }
        random(
                { furhat.ask("It is correct?") },
                { furhat.ask("Am I correct?") }
        )
    }

    onResponse<Yes> {
        goto(RequestChangeInfectionHistory)
    }

    onResponse<No> {
        furhat.gesture(Gestures.Nod(strength=0.6))
        users.current.info.recent_vaccination = null
        users.current.info.count_dose.value = -1
        users.current.info.last_dose_date = null
        users.current.info.last_dose_type = null
        users.current.info.last_dose_reaction = null
        furhat.say("It's noted. We will come back to these questions later.")
        goto(RequestChangeInfectionHistory)
    }
}

val RequestChangeInfectionHistory : State = state(parent = General) {
    onEntry() {
        if (users.current.info.infection == true) {
            furhat.say("You were infected with the Covid 19 in the past. And you have recovered from that since at least 6 months.")
        }else{
            furhat.say("You don't have Covid 19 infection history.")
        }
        random(
                { furhat.ask("It is correct?") },
                { furhat.ask("Am I correct?") }
        )
    }

    onResponse<Yes> {
        goto(RequestChangePregnancy)
    }

    onResponse<No> {
        furhat.gesture(Gestures.Nod(strength=0.6))
        users.current.info.infection = null
        users.current.info.recovery = null
        users.current.info.six_months_after_recovery = null
        furhat.say("It's noted. We will come back to these questions later.")
        goto(RequestChangePregnancy)
    }
}

val RequestChangePregnancy : State = state(parent = General) {
    onEntry() {
        if (users.current.info.pregnant == true) {
            furhat.say("You are currently pregnant for ${users.current.info.count_pregnancy.value} months.")
        }else{
            furhat.say("You are not pregnant.")
        }
        random(
                { furhat.ask("It is correct?") },
                { furhat.ask("Am I correct?") }
        )
    }

    onResponse<Yes> {
        goto(RequestChangePhysicalConditions)
    }

    onResponse<No> {
        furhat.gesture(Gestures.Nod(strength=0.6))
        users.current.info.pregnant = null
        users.current.info.count_pregnancy.value = -1
        users.current.info.known_disease = null
        furhat.say("It's noted. We will come back to these questions later.")
        goto(RequestChangePhysicalConditions)
    }
}

val RequestChangePhysicalConditions : State = state(parent = General) {
    onEntry() {
        furhat.say("You don't have fever now.")
        if (users.current.info.immunodeficiency == true) {
            furhat.say("But you have some problems with your immune system.")
        }else{
            furhat.say("And there's no problem with your immune system.")
        }
        if (users.current.info.bleeding == true) {
            furhat.say("You have an increased bleeding tendency due to disease or medicine.")
        }else{
            furhat.say("You don't have an increased bleeding tendency.")
        }
        random(
                { furhat.ask("It is correct?") },
                { furhat.ask("Am I correct?") }
        )
    }

    onResponse<Yes> {
        goto(RequestChangeAllergy)
    }

    onResponse<No> {
        furhat.gesture(Gestures.Nod(strength=0.6))
        users.current.info.immunodeficiency = null
        users.current.info.bleeding = null
        users.current.info.fever = null
        furhat.say("It's noted. We will come back to these questions later.")
        goto(RequestChangeAllergy)
    }
}

val RequestChangeAllergy : State = state(parent = General) {
    onEntry() {
        furhat.say("You have never ever experienced any severe allergic reactions to any food or vaccines.")
        random(
                { furhat.ask("It is correct?") },
                { furhat.ask("Am I correct?") }
        )
    }

    onResponse<Yes> {
        goto(CheckEligibility)
    }

    onResponse<No> {
        furhat.gesture(Gestures.Nod(strength=0.6))
        users.current.info.allergy = null
        users.current.info.severe_reaction = null
        furhat.say("It's noted. We will come back to these questions later.")
        goto(CheckEligibility)
    }
}

//val RequestPersonalNum : State = state(parent = General) {
//    onEntry() {
//        furhat.ask("What is your personal number?", timeout = 12000)
//    }
//
////    onResponse<TellPersonalNumber> {
////        var personal_num_response = it.intent.personal_number
////        if (personal_num_response != null) {
////            personal_num_response = personal_num_response.filter { it.isDigit() }
////            print(personal_num_response)
////            if (personal_num_response.length != 10) {
////                reentry()
////            }
////        } else {
////            reentry()
////        }
////
////        users.current.info.personal_num = personal_num_response
////        furhat.say("OK, your personal number is $personal_num_response")
////        goto(CheckEligibility)
////    }
//
//    onResponse<TellNoPersonalNumber> {
//        users.current.info.personal_num = "0000000000"
//        furhat.say("OK, you can still get vaccinated, even without personal number.")
//        goto(CheckEligibility)
//    }
//
//    onResponse {
//        var personal_num_response = it.text.toLowerCase()
//        personal_num_response = personal_num_response.filter { it.isDigit() }
////        print(personal_num_response)
//        if (personal_num_response.length != 10) {
//            reentry()
//        }
//        users.current.info.personal_num = personal_num_response
//        furhat.say("OK, your personal number is $personal_num_response.")
//        goto(CheckEligibility)
//    }
//}

val RequestName : State = state(parent = General) {
    onEntry() {
        if (!reentryFromFAQorModification) {
        users.current.reentryCount.name_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
//        print(users.current.reentryCount.name_reentry_count)
        furhat.ask("What is your full name?")
    }
    onResponse<RequestSideEffects> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_side_effects_count += 1
        raise(TellSideEffects())
    }

    onResponse<RequestCOVIDSymptoms> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_covid_symptoms_count += 1
        raise(TellCOVIDSymptoms())
    }

    onResponse<RequestBooster> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_booster_count += 1
        raise(TellBooster())
    }

    onResponse<RequestBreastFeeding> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_breast_feeding_count += 1
        raise(TellBreastFeeding())
    }

    onResponse<RequestPregnancy> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_pregnancy_count += 1
        raise(TellPregnancy())
    }

    onResponse<RequestVaccineProtection> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_vaccine_protection_count += 1
        raise(TellVaccineProtection())
    }

    onResponse<RequestVaccine> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_vaccine_count += 1

        raise(TellVaccine())
    }

    onResponse<RequestVaccineSafety> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_vaccine_safety_count += 1

        raise(TellVaccineSafety())
    }

    onResponse<RequestNationality> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_nationality_count += 1

        raise(TellNationality())
    }

    onResponse<RequestVaccinationFee> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_vaccination_fee_count += 1

        raise(TellVaccinationFee())
    }

    onResponse<RequestAfterVaccinationRegulation> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_after_vaccination_regulation_count += 1

        raise(TellAfterVaccinationRegulation())
    }

    onResponse<RequestVaccinationCertificate> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_vaccination_certificate_count += 1

        raise(TellVaccinationCertificate())
    }

    onResponse<RequestVaccineType> {
        reentryFromFAQorModification = true
        users.current.userFAQIntentCount.request_vaccine_type_count += 1

        raise(TellVaccineType())
    }

    onResponse {
        var name_response = it.text
        if (!name_response.contains("I'm") &&
                !name_response.contains("my name is") &&
                !name_response.contains("My name is") &&
                !name_response.contains("the name is") &&
                !name_response.contains("The name is") &&
                !name_response.contains("is my name") &&
                name_response.split(" ").size != 2) {
            reentry()
        }
        name_response = name_response.replace("I'm ", "")
        name_response = name_response.replace("my name is ", "")
        name_response = name_response.replace("My name is ", "")
        name_response = name_response.replace("the name is ", "")
        name_response = name_response.replace("The name is ", "")
        name_response = name_response.replace(" is my name", "")

        name_response = name_response.replace(".", "")
        name_response = name_response.trim()
        val names = name_response.split(" ")
        if (names.size < 2) {
            reentry()
        }

        users.current.info.name = name_response
        furhat.say("OK, the name is $name_response")
        goto(CheckEligibility)
    }
//    onResponse<TellName> {
//        users.current.info.name = it.intent.names?.toText()
//        furhat.say("OK, the name is ${users.current.info.name}")
//        goto(CheckEligibility)
//    }
}

//val RequestFirstName : State = state(parent = General) {
//    onEntry() {
//        furhat.ask("What is your first name?")
//    }
//
//    onResponse<PersonName> {
//        users.current.info.first_name = it.intent
//        furhat.say("OK, your first name is ${users.current.info.first_name}")
//        goto(CheckEligibility)
//    }
//}

//val RequestLastName : State = state(parent = General) {
//    onEntry() {
//        furhat.ask("What is your last name?")
//    }
//
//    onResponse<PersonName> {
//        users.current.info.last_name = it.intent
//        furhat.say("OK, your last name is ${users.current.info.last_name}")
//        goto(CheckEligibility)
//    }
//}

val RequestConsent : State = state(parent = General) {
    onEntry() {
        // rule-based vaccine recommendation
        if (!reentryFromFAQorModification) {
            users.current.reentryCount.consent_reentry_count += 1
        } else {
            reentryFromFAQorModification = false
        }
        var typeVaccine = Vaccine()
        val info = users.current.info
        when {
            info.last_dose_type != null && info.last_dose_reaction == false && (info.last_dose_type!!.value == "Moderna" || info.last_dose_type!!.value == "BioNTech") -> typeVaccine = info.last_dose_type!!
            info.age.value!! in 12..30 -> typeVaccine.value = "BioNTech"
            else -> random({ typeVaccine.value = "BioNTech" }, { typeVaccine.value = "Moderna" })
        }
        users.current.info.recommended_type = typeVaccine
        furhat.ask("According to your conditions, we recommend $typeVaccine to you. Would you like to get vaccinated according to our recommendation?")
    }

    onResponse<Yes> {
        furhat.gesture(Gestures.Smile(duration = 1.0))
        furhat.ledStrip.solid(java.awt.Color.GREEN)
        random(
                { furhat.say("I'm glad to hear that.") }
        )
        users.current.info.consent = true
        goto(End)
    }

    onResponse<No> {
        furhat.ledStrip.solid(java.awt.Color.RED)
        furhat.gesture(Gestures.ExpressSad(duration = 1.0))
        users.current.info.consent = false
        sendToElasticsearch(users.current.info)
        sendReentryLogToElasticsearch(users.current.reentryCount)
        sendCorrectionIntentToElasticsearch(users.current.userCorrectionIntentCount)
        sendFAQIntentToElasticsearch(users.current.userFAQIntentCount)
        random(
                { furhat.say("I'm sorry to hear that. Vaccination protects both you and the ones you love. I respect your choice and hope to see you again.") }
        )
        goto(Idle)
    }
}

// control the questions to ask
val CheckEligibility = state {
    onEntry {
        val info = users.current.info
        when {
            // the sequence here will decide the sequence of the slots
            info.name == null -> goto(RequestName)
//            info.first_name == null -> goto(RequestFirstName)
//            info.last_name == null -> goto(RequestLastName)
//            info.personal_num == null -> goto(RequestPersonalNum)

            // medical
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
            info.consent == null -> goto(RequestConsent)
            else -> goto(CallMedicalStaff)
        }
    }
}
