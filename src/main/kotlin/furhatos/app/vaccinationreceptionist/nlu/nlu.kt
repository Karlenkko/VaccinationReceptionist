package furhatos.app.vaccinationreceptionist.nlu

import furhatos.nlu.*
import furhatos.util.Language
import furhatos.nlu.common.Number
import java.util.*

// vaccine types
class Vaccine : EnumEntity() {

    override fun getEnum(lang: Language): List<String> {
        return listOf("Moderna", "BioNTech", "Janssen", "AstraZeneca")
    }

    override fun toText(lang: Language): String {
        return generate(lang, "$value")
    }
}

// main form with slots
class ReceiveVaccination: Intent(), TextGenerator {
    var fever: Boolean? = null                     //Do you currently have an acute illness with fever?
    var recent_vaccination: Boolean? = null        //Have your received any other vaccine in the past 7 days?
    var age: Number = Number(-1)             //How old are you?
    var parent_consent: Boolean? = null            //Do you have the consent of your parents or a legal guardian?
    var count_dose: Number = Number(-1)      //How many doses have you ever received till now?
    var last_dose_date: Date? = null               //When was your last dose? Please tell me the exact date.
    var last_dose_type: Vaccine? = null            //What type of vaccine did you get last time?
    var last_dose_reaction: Boolean? = null        //Did you develop an allergic reaction thereafter? Have you had any other unusual reactions after vaccination?
    var infection: Boolean? = null                 //Has it been reliably proven that you were infected with the Covid-19 in the past?
    var recovery: Boolean? = null                  //Did you recover from that?
    var six_months_after_recovery: Boolean? = null //Have you waited at least 6 months since recovery?
    var immunodeficiency: Boolean? = null          //Do you have chronic diseases or do you suffer from immunodeficiency (e.g., due to chemotherapy, immunosuppressive therapy or other medications)?
    var allergy: Boolean? = null                   //Do you have any allergies that at some point have caused such severe reactions that you needed hospital care?
    var severe_reaction: Boolean? = null           //Have you ever had a severe reaction to previous vaccinations that needed hospital care?
    var bleeding: Boolean? = null                  //Do you have an increased bleeding tendency due to disease or medicine?
    var pregnant: Boolean? = null                  //Are you pregnant?
    var count_pregnancy: Number = Number(-1) //For how many months have you been pregnant?
    var known_disease: Boolean? = null             //Do you have any known diseases?
    var confirm_medical_info: Boolean? = null      //Are you sure that you have correctly answered all the questions?
    var personal_num: String? = null
    var name: String? = null
    var recommended_type: Vaccine? = null
    var consent: Boolean? = null                   //Would you like to get vaccinated according to our recommendation?

    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "I want to take a vaccine.",
                "I want to receive a vaccine.",
                "I want to get vaccinated.",
                "I would like to receive a vaccine."
        )
    }

    override fun toText(lang: Language): String {
        return generate(lang, "[You are $name]")
    }

    override fun toString(): String {
        return toText()
    }
}

// tell the number of months of pregnancy
class TellCountPregnancy : Intent() {
    var count_pregnancy: Number = Number(-1)

    override fun getExamples(lang: Language): List<String> {
        return listOf("I have been pregnant for @count_pregnancy months", "I'm pregnant for @count_pregnancy months", "I'm @count_pregnancy months pregnant",
                "@count_pregnancy months", "@count_pregnancy")
    }
}