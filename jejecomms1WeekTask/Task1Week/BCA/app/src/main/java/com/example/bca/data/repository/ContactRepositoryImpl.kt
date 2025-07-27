/*
package com.example.bca.data.repository


import com.example.bca.domain.model.Contact
import com.example.bca.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
class ContactRepositoryImpl : ContactRepository {

    private val contacts = listOf(
        Contact(1, "Saif Ikram", "saif@email.com", "1234567890", "JejeComms", "Android Dev"),
        Contact(2, "Akbar", "akbar@email.com", "9876543210", "Educator App", "YouTuber"),
        Contact(3, "Risalat", "risalat@email.com", "8888888888", "Haters Ltd.", "Don't trust"),
    )

    override fun getContacts(): Flow<List<Contact>> = flow {
        emit(contacts)
    }

    override suspend fun getContactById(id: Int): Contact? {
        return contacts.find { it.id == id }
    }
}
*/
