package furhatos.app.vaccinationreceptionist.flow

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import furhatos.app.vaccinationreceptionist.nlu.ReceiveVaccination
import furhatos.app.vaccinationreceptionist.nlu.Vaccine
import furhatos.nlu.common.Number
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URI
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class PatientData constructor(data: ReceiveVaccination){
    @SerializedName("fever") var fever: Boolean? = data.fever                     //Do you currently have an acute illness with fever?
    @SerializedName("recent_vaccination") var recent_vaccination: Boolean? = data.recent_vaccination        //Have your received any other vaccine in the past 7 days?
    @SerializedName("age") var age: Int? = data.age.value             //How old are you?
    @SerializedName("parent_consent") var parent_consent: Boolean? = data.parent_consent            //Do you have the consent of your parents or a legal guardian?
    @SerializedName("count_dose") var count_dose: Int? = data.count_dose.value      //How many doses have you ever received till now?
    @SerializedName("last_dose_date") var last_dose_date: String? = data.last_dose_date?.value               //When was your last dose? Please tell me the exact date.
    @SerializedName("last_dose_type") var last_dose_type: String? = data.last_dose_type?.value            //What type of vaccine did you get last time?
    @SerializedName("last_dose_reaction") var last_dose_reaction: Boolean? = data.last_dose_reaction        //Did you develop an allergic reaction thereafter? Have you had any other unusual reactions after vaccination?
    @SerializedName("infection") var infection: Boolean? = data.infection                 //Has it been reliably proven that you were infected with the Covid-19 in the past?
    @SerializedName("recovery") var recovery: Boolean? = data.recovery                  //Did you recover from that?
    @SerializedName("short_interval") var short_interval: Boolean? = data.short_interval            //When was your last dose?
    @SerializedName("six_months_after_recovery") var six_months_after_recovery: Boolean? = data.six_months_after_recovery //Have you waited at least 6 months since recovery?
    @SerializedName("immunodeficiency") var immunodeficiency: Boolean? = data.immunodeficiency          //Do you have chronic diseases or do you suffer from immunodeficiency (e.g., due to chemotherapy, immunosuppressive therapy or other medications)?
    @SerializedName("allergy") var allergy: Boolean? = data.allergy                   //Do you have any allergies that at some point have caused such severe reactions that you needed hospital care?
    @SerializedName("severe_reaction") var severe_reaction: Boolean? = data.severe_reaction           //Have you ever had a severe reaction to previous vaccinations that needed hospital care?
    @SerializedName("bleeding") var bleeding: Boolean? = data.bleeding                  //Do you have an increased bleeding tendency due to disease or medicine?
    @SerializedName("pregnant") var pregnant: Boolean? = data.pregnant                  //Are you pregnant?
    @SerializedName("count_pregnancy") var count_pregnancy: Int? = data.count_pregnancy.value //For how many months have you been pregnant?
    @SerializedName("known_disease") var known_disease: Boolean? = data.known_disease             //Do you have any known diseases?
    @SerializedName("confirm_medical_info") var confirm_medical_info: Boolean? = data.confirm_medical_info      //Are you sure that you have correctly answered all the questions?
    //    @SerializedName("personal_num") var personal_num: String? = null
    @SerializedName("name") var name: String? = data.name
    //    @SerializedName("first_name") var first_name: PersonName? = null
//    @SerializedName("last_name") var last_name: PersonName? = null
    @SerializedName("recommended_type") var recommended_type: String? = data.recommended_type?.value
    @SerializedName("consent") var consent: Boolean? = data.consent                   //Would you like to get vaccinated according to our recommendation?
}

// data: ReceiveVaccination

fun sendToElasticsearch(data: ReceiveVaccination) {
    val url = URL("http://127.0.0.1:9200/vaccination_patient_data/_doc")
    var body = Gson().toJson(PatientData(data))
//    var body = "{\"fever\":false,\"recent_vaccination\":false,\"age\":17,\"parent_consent\":true,\"count_dose\":1,\"last_dose_date\":\"2021-11-01\",\"last_dose_type\":\"BioNTech\",\"last_dose_reaction\":false,\"infection\":true,\"recovery\":true,\"short_interval\":false,\"six_months_after_recovery\":true,\"immunodeficiency\":true,\"allergy\":false,\"severe_reaction\":false,\"bleeding\":true,\"pregnant\":true,\"count_pregnancy\":6,\"confirm_medical_info\":true,\"name\":\"John Watson\",\"recommended_type\":\"BioNTech\",\"consent\":true}"
    println(body)

//    with(url.openConnection() as HttpURLConnection) {
//        // optional default is GET
//        requestMethod = "POST"
//        addRequestProperty("Content-Type", "application/json")
//        setDoOutput(true)
//        val wr = OutputStreamWriter(getOutputStream())
//        wr.write(body)
//        wr.flush()
//
//        println("URL : $url")
//        println("Response Code : $responseCode")
//
//        BufferedReader(InputStreamReader(inputStream)).use {
//            val response = StringBuffer()
//
//            var inputLine = it.readLine()
//            while (inputLine != null) {
//                response.append(inputLine)
//                inputLine = it.readLine()
//            }
//            println("Response : $response")
//        }
//    }
}

fun sendReentryLogToElasticsearch(reentry: ReentryCount) {
    val url = URL("http://127.0.0.1:9200/reentry_count/_doc")
    var body = Gson().toJson(reentry)
    println(body)
    //    with(url.openConnection() as HttpURLConnection) {
//        // optional default is GET
//        requestMethod = "POST"
//        addRequestProperty("Content-Type", "application/json")
//        setDoOutput(true)
//        val wr = OutputStreamWriter(getOutputStream())
//        wr.write(body)
//        wr.flush()
//
//        println("URL : $url")
//        println("Response Code : $responseCode")
//
//        BufferedReader(InputStreamReader(inputStream)).use {
//            val response = StringBuffer()
//
//            var inputLine = it.readLine()
//            while (inputLine != null) {
//                response.append(inputLine)
//                inputLine = it.readLine()
//            }
//            println("Response : $response")
//        }
//    }
}

fun sendCorrectionIntentToElasticsearch(correctionIntent: UserCorrectionIntentCount) {
    val url = URL("http://127.0.0.1:9200/correction_count/_doc")
    var body = Gson().toJson(correctionIntent)
    println(body)
    //    with(url.openConnection() as HttpURLConnection) {
//        // optional default is GET
//        requestMethod = "POST"
//        addRequestProperty("Content-Type", "application/json")
//        setDoOutput(true)
//        val wr = OutputStreamWriter(getOutputStream())
//        wr.write(body)
//        wr.flush()
//
//        println("URL : $url")
//        println("Response Code : $responseCode")
//
//        BufferedReader(InputStreamReader(inputStream)).use {
//            val response = StringBuffer()
//
//            var inputLine = it.readLine()
//            while (inputLine != null) {
//                response.append(inputLine)
//                inputLine = it.readLine()
//            }
//            println("Response : $response")
//        }
//    }
}

fun sendFAQIntentToElasticsearch(faqIntentCount: UserFAQIntentCount) {
    val url = URL("http://127.0.0.1:9200/faq_count/_doc")
    var body = Gson().toJson(faqIntentCount)
    println(body)
    //    with(url.openConnection() as HttpURLConnection) {
//        // optional default is GET
//        requestMethod = "POST"
//        addRequestProperty("Content-Type", "application/json")
//        setDoOutput(true)
//        val wr = OutputStreamWriter(getOutputStream())
//        wr.write(body)
//        wr.flush()
//
//        println("URL : $url")
//        println("Response Code : $responseCode")
//
//        BufferedReader(InputStreamReader(inputStream)).use {
//            val response = StringBuffer()
//
//            var inputLine = it.readLine()
//            while (inputLine != null) {
//                response.append(inputLine)
//                inputLine = it.readLine()
//            }
//            println("Response : $response")
//        }
//    }
}