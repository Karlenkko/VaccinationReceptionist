package furhatos.app.vaccinationreceptionist.flow

import furhatos.app.vaccinationreceptionist.info
import furhatos.app.vaccinationreceptionist.nlu.*
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.nlu.common.Number
import java.util.*

//　greeting + introduction + ask to start slot filling
val Start: State = state(Interaction) {

    onEntry {
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
}

// parent state for common answers
val General: State = state(Interaction) {
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
    onEntry() {
        random(
                { furhat.say("Please go to the waiting room. You will get vaccinated very soon! Have a nice day!") }
        )
        // TODO
        // store info in the backend or log system
        goto(Idle)
    }
}

val RefuseExplain : State = state(parent = General) {
    onEntry() {
        val info = users.current.info
        when {
            info.fever == true -> furhat.say("Due to regulations, you need to recover from the fever.")
            info.recent_vaccination == true -> furhat.say("You need to wait for at least a week after your first dose, or 6 month after the second.")
            info.age.value!! < 12 -> furhat.say("You need to wait till you are more than 12 years old.")
            info.count_dose.value!! > 2 -> furhat.say("You have received all doses, including the booster.")
            (info.count_dose.value!! == 2 && info.age.value!! < 18) -> furhat.say("You are less than 18 years old, and there is no need for a third dose.")
            info.count_dose.value!! == 1 -> furhat.say("You need to wait for at least 7 days after the first dose.")
            info.count_dose.value!! == 2 -> furhat.say("You need to wait for at least 6 months after the second dose.")
            info.recovery == false -> furhat.say("You should wait for 6 months after you get fully recovered from the covid-19 symptoms.")
            info.six_months_after_recovery == false -> furhat.say("You should wait for 6 months after you get fully recovered from the covid-19 symptoms.")
        }
        furhat.say("If you have further questions, please consult your doctor. Take care of yourself, and be well. Bye.")
        goto(Idle)
    }
}

val CallMedicalStaff : State = state(parent = General) {
    onEntry() {
        furhat.say("Our medical staff is waiting for you on the right hand side. Please consult the staff for further steps. Bye")
        goto(Idle)
    }
}

val RequestFever : State = state(parent = General) {
    onEntry() {
        furhat.ask("Do you currently have an acute illness with fever?")
    }

    // go straight to RefuseExplain when not eligible
    onResponse<Yes> {
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
}

val RequestRecentVaccination : State = state(parent = General) {
    onEntry() {
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
        furhat.ask("How many doses have you ever received till now?")
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
        var date = furhat.askFor<furhatos.nlu.common.Date>("When was your last dose? Please tell me the exact date.") {
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
        if (temp > today && users.current.info.count_dose.value!! == 1) {
            goto(RefuseExplain)
        }
        temp = converted_last_dose_date
        temp.add(Calendar.MONTH, 6)
        if (temp > today && users.current.info.count_dose.value!! == 2) {
            goto(RefuseExplain)
        }

        goto(CheckEligibility)
    }
}

val RequestLastDoseType : State = state(parent = General) {
    onEntry() {
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
        furhat.ask("Do you have chronic diseases or do you suffer from immunodeficiency (e.g., due to chemotherapy, immunosuppressive therapy or other medications)?")
    }

    onResponse<Yes> {
        random(
                { furhat.say("You have higher change of being seriously ill due to Covid 19, therefore we advice you to receive extra booster dose after the second dose.") }
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
        furhat.ask("Do you have any allergies that at some point have caused such severe reactions that you needed hospital care?")
    }

    onResponse<Yes> {
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
}

val RequestSevereReaction : State = state(parent = General) {
    onEntry() {
        furhat.ask("Have you ever had a severe reaction to previous vaccinations that needed hospital care?")
    }

    onResponse<Yes> {
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
}

val RequestBleeding : State = state(parent = General) {
    onEntry() {
        furhat.ask("Do you have an increased bleeding tendency due to disease or medicine?")
    }

    onResponse<Yes> {
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

    //TODO
    // onResponse<No>
}

val RequestPersonalNum : State = state(parent = General) {
    onEntry() {
        furhat.ask("What is your personal number?")
    }

    onResponse {
        var personal_num_response = it.text.toLowerCase()
        personal_num_response = personal_num_response.filter { it.isDigit() }

        users.current.info.personal_num = personal_num_response
        furhat.say("OK, your personal number is $personal_num_response")
        goto(CheckEligibility)
    }
}

val RequestName : State = state(parent = General) {
    onEntry() {
        furhat.ask("What is your full name?")
    }

    onResponse {
        var name_response = it.text
        name_response = name_response.replace("I'm ", "")
        name_response = name_response.replace("my name is ", "")
        name_response = name_response.replace("My name is ", "")
        name_response = name_response.replace("the name is ", "")
        name_response = name_response.replace("The name is ", "")
        name_response = name_response.replace(" is my name", "")

        name_response = name_response.replace(".", "")
        name_response = name_response.trim()

        users.current.info.name = name_response
        furhat.say("OK, the name is $name_response")
        goto(CheckEligibility)
    }
}

val RequestConsent : State = state(parent = General) {
    onEntry() {
        // rule-based vaccine recommendation
        var typeVaccine = Vaccine()
        val info = users.current.info
        when {
            info.last_dose_type != null && info.last_dose_reaction == false -> typeVaccine = info.last_dose_type!!
            info.age.value!! in 12..30 -> typeVaccine.value = "BioNTech"
            else -> random({ typeVaccine.value = "BioNTech" }, { typeVaccine.value = "Moderna" })
        }
        users.current.info.recommended_type = typeVaccine
        furhat.ask("According to your conditions, we recommend $typeVaccine to you. Would you like to get vaccinated according to our recommendation?")
    }

    onResponse<Yes> {
        random(
                { furhat.say("I'm glad to hear that.") }
        )
        users.current.info.consent = true
        goto(End)
    }

    onResponse<No> {
        random(
                { furhat.say("I'm sorry to hear that. Vaccination protects both you and the ones you love. I respect your choice and hope to see you again.") }
        )
        users.current.info.consent = false
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
            info.personal_num == null -> goto(RequestPersonalNum)

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
