package com.example.bca.presentation.contacts
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.bca.domain.model.contact.Contact

@Composable
fun EditContactScreen(
    contact: Contact,
    onSave: (Contact) -> Unit
) {
    val context = LocalContext.current

    var name by remember { mutableStateOf(contact.name) }
    var company by remember { mutableStateOf(contact.company) }
    var phone by remember { mutableStateOf(contact.phone) }
    var email by remember { mutableStateOf(contact.email) }
    var notes by remember { mutableStateOf(contact.notes) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = company,
            onValueChange = { company = it },
            label = { Text("Company") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                when {
                    name.isBlank() -> {
                        Toast.makeText(context, "Name can't be empty", Toast.LENGTH_SHORT).show()
                    }
                    company.isBlank() -> {
                        Toast.makeText(context, "Company can't be empty", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        onSave(
                            contact.copy(
                                name = name,
                                company = company,
                                phone = phone,
                                email = email,
                                notes = notes
                            )
                        )
                        Toast.makeText(context, "Contact saved", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}
