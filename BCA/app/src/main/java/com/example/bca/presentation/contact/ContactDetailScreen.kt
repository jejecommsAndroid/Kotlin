package com.example.bca.presentation.contact
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.bca.domain.model.contact.Contact
import com.example.bca.presentation.components.AvatarView
import com.example.bca.presentation.components.InfoCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    contact: Contact,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scrollState = rememberLazyListState()
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { Text(contact.name) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        scope.launch { sheetState.show() }
                    }) {
                        Icon(Icons.Default.Share, contentDescription = "Share Contact")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onEditClick) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            state = scrollState,
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    AvatarView(name = contact.name)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Phone
            item {
                InfoCard(
                    icon = Icons.Default.Phone,
                    title = "Phone",
                    value = contact.phone,
                    trailing = {
                        IconButton(onClick = {
                            clipboardManager.setText(AnnotatedString(contact.phone))
                            Toast.makeText(context, "Phone copied", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy Phone")
                        }
                    }
                )
            }

            // Email
            item {
                InfoCard(
                    icon = Icons.Default.Email,
                    title = "Email",
                    value = contact.email,
                    trailing = {
                        IconButton(onClick = {
                            clipboardManager.setText(AnnotatedString(contact.email))
                            Toast.makeText(context, "Email copied", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy Email")
                        }
                    }
                )
            }

            // Company
            item {
                InfoCard(
                    icon = Icons.Default.Business,
                    title = "Company",
                    value = contact.company,
                    trailing = {
                        IconButton(onClick = {
                            clipboardManager.setText(AnnotatedString(contact.company))
                            Toast.makeText(context, "Company copied", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy Company")
                        }
                    }
                )
            }

            // Notes
            item {
                InfoCard(
                    icon = Icons.Default.Notes,
                    title = "Notes",
                    value = contact.notes,
                    trailing = {
                        IconButton(onClick = {
                            clipboardManager.setText(AnnotatedString(contact.notes))
                            Toast.makeText(context, "Notes copied", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy Notes")
                        }
                    }
                )
            }
        }
    }

    // Bottom sheet for sharing
    if (sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = { scope.launch { sheetState.hide() } },
            sheetState = sheetState
        ) {
            Text(
                text = "Share Contact",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
            ShareOption(title = "Share via...", onClick = {
                shareContact(context, contact)
                scope.launch { sheetState.hide() }
            })
        }
    }
}

@Composable
fun ShareOption(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Send, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title)
    }
}

fun shareContact(context: Context, contact: Contact) {
    val shareText = """
        Name: ${contact.name}
        Phone: ${contact.phone}
        Email: ${contact.email}
        Company: ${contact.company}
        Notes: ${contact.notes}
    """.trimIndent()

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, shareText)
    }
    context.startActivity(Intent.createChooser(intent, "Share contact via"))
}
