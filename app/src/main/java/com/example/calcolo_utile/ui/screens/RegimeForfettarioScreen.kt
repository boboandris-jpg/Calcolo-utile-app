package com.example.calcolo_utile.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Row
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calcolo_utile.ui.components.CustomInputField
import com.example.calcolo_utile.ui.components.ResultCard
import com.example.calcolo_utile.viewmodel.CalcoloViewModel

/**
 * Schermata per il calcolo in regime forfettario
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegimeForfettarioScreen(
    onBackClick: () -> Unit,
    context: Context,
    viewModel: CalcoloViewModel = viewModel(
        factory = CalcoloViewModelFactory(context)
    )
) {
    var prezzoAcquisto by remember { mutableStateOf("") }
    var prezzoVendita by remember { mutableStateOf("") }
    var usaImpostaBassa by remember { mutableStateOf(false) }
    var applicaContributi by remember { mutableStateOf(false) }

    val risultati by viewModel.risultatiForfettario.collectAsStateWithLifecycle()

    // Calcolo in tempo reale
    LaunchedEffect(prezzoAcquisto, prezzoVendita, usaImpostaBassa, applicaContributi) {
        val acq = prezzoAcquisto.toDoubleOrNull() ?: 0.0
        val vend = prezzoVendita.toDoubleOrNull() ?: 0.0
        viewModel.calcolaRegimeForfettario(acq, vend, usaImpostaBassa, applicaContributi)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Regime Forfettario") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Indietro")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Input
            CustomInputField(
                label = "Prezzo di acquisto (€)",
                value = prezzoAcquisto,
                onValueChange = { prezzoAcquisto = it },
                modifier = Modifier.padding(bottom = 12.dp)
            )

            CustomInputField(
                label = "Prezzo di vendita (€)",
                value = prezzoVendita,
                onValueChange = { prezzoVendita = it },
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Checkbox Imposta bassa
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = usaImpostaBassa,
                    onCheckedChange = { usaImpostaBassa = it }
                )
                Text("Usa imposta al 5%", style = MaterialTheme.typography.bodyMedium)
            }

            // Checkbox Contributi
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = applicaContributi,
                    onCheckedChange = { applicaContributi = it }
                )
                Text("Applica contributi INPS", style = MaterialTheme.typography.bodyMedium)
            }

            // Risultati
            Text(
                "Risultati del calcolo",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            ResultCard(
                title = "Reddito imponibile",
                value = risultati.redditoImponibile,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            ResultCard(
                title = "Imposta sostitutiva",
                value = risultati.impostaSostitutiva,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            if (applicaContributi) {
                ResultCard(
                    title = "Contributi INPS",
                    value = risultati.contributi,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            ResultCard(
                title = "Utile netto finale",
                value = risultati.utileNetto,
                isHighlight = true,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
    }
}
