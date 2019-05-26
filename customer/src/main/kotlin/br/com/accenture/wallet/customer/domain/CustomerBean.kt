package br.com.accenture.wallet.customer.domain

import com.fasterxml.jackson.annotation.JsonInclude
import java.text.ParseException
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import java.text.SimpleDateFormat
import java.util.Objects.isNull

@JsonInclude(JsonInclude.Include.NON_NULL)
class CustomerBean {
    private val df = SimpleDateFormat("dd/MM/yyyy")

    var id: String = ""

    @NotBlank @Size(min=5)
    var name: String = ""

    @NotBlank
    var email: String = ""

    @Pattern(regexp = "male|female|other")
    var gender: String? = null

    var birthday: String? = null

    fun fromEntity(entity: Customer): CustomerBean {
        this.id = entity.id
        this.name = entity.name
        this.email = entity.email
        this.gender = toGender(entity.gender)
        this.birthday = toBirthday(entity.birthday)
        return this
    }

    fun toEntity(): Customer {
        val customer = Customer(this.name)
        customer.id = if (this.id != "") this.id else ""
        customer.email = this.email
        customer.gender = toGender(this.gender)
        customer.birthday = toBirthday(this.birthday)
        return customer
    }

    private fun toGender(value: Int?): String {
        if (isNull(value)) return ""
        return when(value) {
            1 -> "male"
            2 -> "female"
            else -> "other"
        }
    }

    private fun toGender(value: String?): Int? {
        return when(value) {
            "male" -> 1
            "female" -> 2
            "other" -> 3
            else -> null
        }
    }

    private fun toBirthday(value: Date?): String {
        if (isNull(value)) return ""
        return df.format(value)
    }

    private fun toBirthday(value: String?): Date? {
        if (isNull(value)) return null
        return try {
            df.parse(value)
        } catch (ex: ParseException) {
            null
        }
    }
}
