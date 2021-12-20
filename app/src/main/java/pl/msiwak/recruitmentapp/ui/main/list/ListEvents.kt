package pl.msiwak.recruitmentapp.ui.main.list

import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.ui.base.BaseEvent

sealed class ListEvents: BaseEvent {
    class OpenBrowser(val url: String): ListEvents()
    object CloseApp: ListEvents()
}