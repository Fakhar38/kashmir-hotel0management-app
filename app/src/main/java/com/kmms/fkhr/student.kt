package com.kmms.fkhr
import java.io.Serializable

data class student (

    val Department: String = "",
    val HostelName: String = "",
    val MessNo: String = "",
    val Name: String = "",
    val password: Int = 0,
    val RegistrationNo: String = "",
    val UserID: String = ""

    /*constructor (Department: String, HostelName: String, MessNo: String, Name: String, password: Int, RegistrationNo: String, UserID: String)
    {
        this.Department =Department
        this.HostelName = HostelName
        this.MessNo = MessNo
        this.Name = Name
        this.password = password
        this.RegistrationNo = RegistrationNo
        this.UserID = UserID
    }

    fun getName(): String? {
        return MessNo
    }
    fun getPassword(): String? {
        return password.toString()
    }
*/

    ): Serializable
