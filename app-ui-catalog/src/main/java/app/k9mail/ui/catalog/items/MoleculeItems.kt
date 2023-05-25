package app.k9mail.ui.catalog.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.k9mail.core.ui.compose.designsystem.atom.text.TextSubtitle1
import app.k9mail.core.ui.compose.designsystem.molecule.ErrorView
import app.k9mail.core.ui.compose.designsystem.molecule.LoadingView
import app.k9mail.core.ui.compose.designsystem.molecule.input.CheckboxInput
import app.k9mail.core.ui.compose.designsystem.molecule.input.EmailAddressInput
import app.k9mail.core.ui.compose.designsystem.molecule.input.PasswordInput
import app.k9mail.core.ui.compose.designsystem.molecule.input.SelectInput
import app.k9mail.core.ui.compose.designsystem.molecule.input.SwitchInput
import app.k9mail.core.ui.compose.theme.MainTheme
import app.k9mail.ui.catalog.ui.common.helper.WithRememberedState
import app.k9mail.ui.catalog.ui.common.list.itemDefaultPadding
import app.k9mail.ui.catalog.ui.common.list.sectionHeaderItem
import kotlinx.collections.immutable.persistentListOf

@Suppress("LongMethod")
fun LazyGridScope.moleculeItems() {
    sectionHeaderItem(text = "Molecules")
    item {
        MoleculeWrapper(title = "ErrorView") {
            ErrorView(
                title = "Error",
                message = "Something went wrong",
            )
        }
    }

    item {
        MoleculeWrapper(title = "LoadingView") {
            LoadingView()
        }
    }
    item {
        MoleculeWrapper(title = "LoadingView with message") {
            LoadingView(
                message = "Loading...",
            )
        }
    }

    item {
        MoleculeWrapper(title = "EmailAddressInput") {
            WithRememberedState(input = "") { state ->
                EmailAddressInput(
                    emailAddress = state.value,
                    onEmailAddressChange = { state.value = it },
                )
            }
        }
    }
    item {
        MoleculeWrapper(title = "EmailAddressInput with error") {
            WithRememberedState(input = "wrong email address") { state ->
                EmailAddressInput(
                    emailAddress = state.value,
                    onEmailAddressChange = { state.value = it },
                    errorMessage = "Invalid email address",
                )
            }
        }
    }

    item {
        MoleculeWrapper(title = "PasswordInput") {
            WithRememberedState(input = "") { state ->
                PasswordInput(
                    password = state.value,
                    onPasswordChange = { state.value = it },
                )
            }
        }
    }
    item {
        MoleculeWrapper(title = "PasswordInput with error") {
            WithRememberedState(input = "wrong password") { state ->
                PasswordInput(
                    password = state.value,
                    onPasswordChange = { state.value = it },
                    errorMessage = "Invalid password",
                )
            }
        }
    }
    item {
        MoleculeWrapper(title = "CheckboxInput") {
            WithRememberedState(input = false) { state ->
                CheckboxInput(
                    text = "Check the box",
                    checked = state.value,
                    onCheckedChange = { state.value = it },
                )
            }
        }
    }
    item {
        MoleculeWrapper(title = "SwitchInput") {
            WithRememberedState(input = false) { state ->
                SwitchInput(
                    text = "Switch the toggle",
                    checked = state.value,
                    onCheckedChange = { state.value = it },
                )
            }
        }
    }
    item {
        val options = persistentListOf("Option 1", "Option 2", "Option 3")
        MoleculeWrapper(title = "SelectInput") {
            WithRememberedState(input = options.first()) { state ->
                SelectInput(
                    options = options,
                    selectedOption = state.value,
                    onOptionChange = { state.value = it },
                )
            }
        }
    }
}

@Composable
private fun MoleculeWrapper(
    title: String,
    content: @Composable () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MainTheme.spacings.default),
        modifier = Modifier.itemDefaultPadding(),
    ) {
        TextSubtitle1(text = title)
        Box(
            modifier = Modifier
                .border(1.dp, Color.Gray)
                .fillMaxWidth(),
        ) {
            content()
        }
    }
}