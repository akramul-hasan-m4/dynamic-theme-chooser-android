package com.mvvm.ati.mail.model

class Contacts {

    var id: String? = null
    var email: String? = null
    var name: String? = null

    constructor(email: String?, name: String?) {
        this.email = email
        this.name = name
    }
    constructor()

    override fun toString(): String {
        return "$email"
    }

}