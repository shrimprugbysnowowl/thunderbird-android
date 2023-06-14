package app.k9mail.feature.account.setup.ui.autoconfig.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import app.k9mail.autodiscovery.api.ConnectionSecurity
import app.k9mail.core.ui.compose.designsystem.atom.Icon
import app.k9mail.core.ui.compose.designsystem.atom.text.TextBody1
import app.k9mail.core.ui.compose.designsystem.atom.text.TextBody2
import app.k9mail.core.ui.compose.theme.Icons
import app.k9mail.core.ui.compose.theme.MainTheme
import app.k9mail.core.ui.compose.theme.PreviewWithThemes
import app.k9mail.feature.account.setup.ui.autoconfig.toResourceString
import app.k9mail.feature.account.setup.ui.common.toResourceString

@Composable
internal fun AutoDiscoveryServerSettingsView(
    protocolName: String,
    serverHostname: String,
    serverPort: Int,
    connectionSecurity: ConnectionSecurity,
    modifier: Modifier = Modifier,
    username: String? = null,
    isIncoming: Boolean = true,
) {
    val resources = LocalContext.current.resources
    Column(
        verticalArrangement = Arrangement.spacedBy(MainTheme.spacings.default),
        modifier = modifier,
    ) {
        TextBody1(
            text = buildAnnotatedString {
                append(if (isIncoming) "Incoming" else "Outgoing")
                append(" ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(protocolName.uppercase())
                }
                append(" ")
                append("configuration")
            },
        )

        ServerSettingRow(
            icon = if (isIncoming) Icons.Filled.inbox else Icons.Filled.outbox,
            text = buildAnnotatedString {
                append("Server")
                append(": ")
                append(serverHostname.substringBefore(".") + ".")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(serverHostname.substringAfter("."))
                }
                append(":$serverPort")
            },
        )

        ServerSettingRow(
            icon = Icons.Filled.security,
            text = buildAnnotatedString {
                append("Security: ")
                append(connectionSecurity.toResourceString(resources))
            },
        )

        if (username != null) {
            ServerSettingRow(
                icon = Icons.Filled.user,
                text = buildAnnotatedString {
                    append("Username: ")
                    append(username)
                },
            )
        }
    }
}

@Composable
internal fun ServerSettingRow(
    icon: ImageVector,
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    showIcon: Boolean = false,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showIcon) {
            Icon(
                imageVector = icon,
                modifier = Modifier.padding(end = MainTheme.spacings.default),
            )
        }
        TextBody2(
            text = text,
        )
    }
}

@Preview
@Composable
internal fun AutoDiscoveryServerSettingsViewPreview() {
    PreviewWithThemes {
        AutoDiscoveryServerSettingsView(
            protocolName = "IMAP",
            serverHostname = "imap.example.com",
            serverPort = 993,
            connectionSecurity = ConnectionSecurity.TLS,
        )
    }
}

@Preview
@Composable
internal fun AutoDiscoveryServerSettingsViewOutgoingPreview() {
    PreviewWithThemes {
        AutoDiscoveryServerSettingsView(
            protocolName = "IMAP",
            serverHostname = "imap.example.com",
            serverPort = 993,
            connectionSecurity = ConnectionSecurity.TLS,
            isIncoming = false,
        )
    }
}

@Preview
@Composable
internal fun AutoDiscoveryServerSettingsViewWithUserPreview() {
    PreviewWithThemes {
        AutoDiscoveryServerSettingsView(
            protocolName = "IMAP",
            serverHostname = "imap.example.com",
            serverPort = 993,
            connectionSecurity = ConnectionSecurity.TLS,
            username = "username",
        )
    }
}
