package com.example.peony.model

data class Openfda(
    val application_number: List<String>,
    val brand_name: List<String>,
    val generic_name: List<String>,
    val is_original_packager: List<Boolean>,
    val manufacturer_name: List<String>,
    val package_ndc: List<String>,
    val product_ndc: List<String>,
    val product_type: List<String>,
    val route: List<String>,
    val rxcui: List<String>,
    val spl_id: List<String>,
    val spl_set_id: List<String>,
    val substance_name: List<String>,
    val unii: List<String>
)