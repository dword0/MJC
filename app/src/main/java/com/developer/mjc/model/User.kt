package com.developer.mjc.model

import java.io.Serializable

data class User(
    var id: String = "",
    val name: String= "",
    val email: String= "",
    val phone: String= "",
    val profileImage: String= "",
    val isEngineer: Boolean,
    val idProof: String= "",
    val address: Address,
    val about: String= "",
    val doj: String= "",
    val fees: String? = null,
    val occupation: String,
    val works: List<String>? = null


): Serializable

data class Address(
    val houseName: String,
    val district: String,
    val state: String,
    val pin: String
)

data class Work(
    val id: String,
    val name: String,
    val buildingArea: Int,
    val ownerName: Int,
    val dateOfCompletion: String,
    val description: String,
    val imageGallery: List<String>

)