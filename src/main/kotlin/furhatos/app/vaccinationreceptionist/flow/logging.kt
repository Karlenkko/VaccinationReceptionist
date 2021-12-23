package furhatos.app.vaccinationreceptionist.flow

import com.google.gson.annotations.SerializedName
import furhatos.app.vaccinationreceptionist.nlu.Vaccine
import furhatos.nlu.common.Number

class ReentryCount {
    @SerializedName("fever_reentry_count") var fever_reentry_count: Number = Number(-1)                     //Do you currently have an acute illness with fever?
    @SerializedName("recent_vaccination_reentry_count") var recent_vaccination_reentry_count: Number = Number(-1)        //Have your received any other vaccine in the past 7 days?
    @SerializedName("age_reentry_count") var age_reentry_count: Number = Number(-1)             //How old are you?
    @SerializedName("parent_consent_reentry_count") var parent_consent_reentry_count: Number = Number(-1)            //Do you have the consent of your parents or a legal guardian?
    @SerializedName("count_dose_reentry_count") var count_dose_reentry_count: Number = Number(-1)      //How many doses have you ever received till now?
    @SerializedName("last_dose_date_reentry_count") var last_dose_date_reentry_count: Number = Number(-1)               //When was your last dose? Please tell me the exact date.
    @SerializedName("last_dose_type_reentry_count") var last_dose_type_reentry_count: Number = Number(-1)            //What type of vaccine did you get last time?
    @SerializedName("last_dose_reaction_reentry_count") var last_dose_reaction_reentry_count: Number = Number(-1)        //Did you develop an allergic reaction thereafter? Have you had any other unusual reactions after vaccination?
    @SerializedName("infection_reentry_count") var infection_reentry_count: Number = Number(-1)                 //Has it been reliably proven that you were infected with the Covid-19 in the past?
    @SerializedName("recovery_reentry_count") var recovery_reentry_count: Number = Number(-1)                  //Did you recover from that?
    @SerializedName("short_interval_reentry_count") var short_interval_reentry_count: Number = Number(-1)            //When was your last dose?
    @SerializedName("six_months_after_recovery_reentry_count") var six_months_after_recovery_reentry_count: Number = Number(-1) //Have you waited at least 6 months since recovery?
    @SerializedName("immunodeficiency_reentry_count") var immunodeficiency_reentry_count: Number = Number(-1)          //Do you have chronic diseases or do you suffer from immunodeficiency (e.g., due to chemotherapy, immunosuppressive therapy or other medications)?
    @SerializedName("allergy_reentry_count") var allergy_reentry_count: Number = Number(-1)                   //Do you have any allergies that at some point have caused such severe reactions that you needed hospital care?
    @SerializedName("severe_reaction_reentry_count") var severe_reaction_reentry_count: Number = Number(-1)           //Have you ever had a severe reaction to previous vaccinations that needed hospital care?
    @SerializedName("bleeding_reentry_count") var bleeding_reentry_count: Number = Number(-1)                  //Do you have an increased bleeding tendency due to disease or medicine?
    @SerializedName("pregnant_reentry_count") var pregnant_reentry_count: Number = Number(-1)                  //Are you pregnant?
    @SerializedName("count_pregnancy_reentry_count") var count_pregnancy_reentry_count: Number = Number(-1) //For how many months have you been pregnant?
    @SerializedName("known_disease_reentry_count") var known_disease_reentry_count: Number = Number(-1)             //Do you have any known diseases?
    @SerializedName("confirm_medical_info_reentry_count") var confirm_medical_info_reentry_count: Number = Number(-1)      //Are you sure that you have correctly answered all the questions?
    //    @SerializedName("personal_num") var personal_num: String? = null
    @SerializedName("name_reentry_count") var name_reentry_count: Number = Number(-1)
    //    @SerializedName("first_name") var first_name: PersonName? = null
//    @SerializedName("last_name") var last_name: PersonName? = null
    @SerializedName("recommended_type_reentry_count") var recommended_type_reentry_count: Number = Number(-1)
    @SerializedName("consent_reentry_count") var consent_reentry_count: Number = Number(-1)                   //Would you like to get vaccinated according to our recommendation?
}

class UserCorrectionIntentCount {
    @SerializedName("tell_age_count") var tell_age_count: Number = Number(-1)
    @SerializedName("tell_number_dose_count") var tell_number_dose_count: Number = Number(-1)
    @SerializedName("tell_pregnancy_count") var tell_pregnancy_count: Number = Number(-1)
    @SerializedName("tell_infection_count") var tell_infection_count: Number = Number(-1)
}

class UserFAQIntentCount {
    @SerializedName("request_side_effects_count") var request_side_effects_count: Number = Number(0)
    @SerializedName("request_covid_symptoms_count") var request_covid_symptoms_count: Number = Number(0)
    @SerializedName("request_booster_count") var request_booster_count: Number = Number(0)
    @SerializedName("request_breast_feeding_count") var request_breast_feeding_count: Number = Number(0)
    @SerializedName("request_pregnancy_count") var request_pregnancy_count: Number = Number(0)
    @SerializedName("request_vaccine_protection_count") var request_vaccine_protection_count: Number = Number(0)
    @SerializedName("request_vaccine_count") var request_vaccine_count: Number = Number(0)
    @SerializedName("request_vaccine_safety_count") var request_vaccine_safety_count: Number = Number(0)
    @SerializedName("request_nationality_count") var request_nationality_count: Number = Number(0)
    @SerializedName("request_vaccination_fee_count") var request_vaccination_fee_count: Number = Number(0)
    @SerializedName("request_after_vaccination_regulation_count") var request_after_vaccination_regulation_count: Number = Number(0)
    @SerializedName("request_vaccination_certificate_count") var request_vaccination_certificate_count: Number = Number(0)
    @SerializedName("request_vaccine_type_count") var request_vaccine_type_count: Number = Number(0)
}