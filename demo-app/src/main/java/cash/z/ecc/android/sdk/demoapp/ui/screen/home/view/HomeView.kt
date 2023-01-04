package cash.z.ecc.android.sdk.demoapp.ui.screen.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cash.z.ecc.android.sdk.demoapp.R

@Preview
@Composable
fun ComposablePreviewHome() {
    MaterialTheme {
        Home(
            // WalletSnapshotFixture.new(),
            goSend = {},
            goAddressDetails = {},
            resetSdk = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("LongParameterList")
@Composable
fun Home(
    goSend: () -> Unit,
    goAddressDetails: () -> Unit,
    resetSdk: () -> Unit
) {
    Scaffold(topBar = {
        HomeTopAppBar(resetSdk)
    }) { paddingValues ->
        HomeMainContent(
            paddingValues = paddingValues,
            goSend = goSend,
            goAddressDetails = goAddressDetails
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun HomeTopAppBar(
    resetSdk: () -> Unit
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            DebugMenu(resetSdk = resetSdk)
        }
    )
}

@Composable
private fun DebugMenu(resetSdk: () -> Unit) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(Icons.Default.MoreVert, contentDescription = null)
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("Reset SDK") },
            onClick = {
                resetSdk()
                expanded = false
            }
        )
    }
}

@Composable
private fun HomeMainContent(
    paddingValues: PaddingValues,
    goSend: () -> Unit,
    goAddressDetails: () -> Unit
) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        Button(goSend) {
            Text(text = stringResource(id = R.string.menu_send))
        }

        Button(goAddressDetails) {
            Text(text = stringResource(id = R.string.menu_address))
        }
    }
}