package com.example.peony.model

data class Result(
    val boxed_warning: List<String>,
    val description: List<String>,
//    val dosage_and_administration: List<String>,
    val dosage_forms_and_strengths: List<String>,
    val drug_interactions: List<String>,
    val indications_and_usage: List<String>,
    val openfda: Openfda,
    val purpose: List<String>,
    val spl_medguide: List<String>,
    val warnings: List<String>,
    val warnings_and_cautions: List<String>
)