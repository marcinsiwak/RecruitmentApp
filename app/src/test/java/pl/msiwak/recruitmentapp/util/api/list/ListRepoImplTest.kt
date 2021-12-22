package pl.msiwak.recruitmentapp.util.api.list

import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.data.ServerResponseItem
import pl.msiwak.recruitmentapp.ui.main.list.BaseTest
import pl.msiwak.recruitmentapp.util.db.DataDao

class ListRepoImplTest : BaseTest() {

    private lateinit var repo: ListRepo

    @Mock
    private lateinit var dataDao: DataDao

    @Mock
    private lateinit var listService: ListService

    private val mockListItem = ListItem(1, "", "", "", "", "")
    private val mockServerResponseItem = ServerResponseItem("", "", "", 1, "")

    override fun setup() {
        super.setup()

        trampolineRxPlugin()
        repo = ListRepoImpl(listService, dataDao)

    }

    override fun tearDown() {
        super.tearDown()

        verifyNoMoreInteractions(
            listService, dataDao
        )
    }

    @Test
    fun getDataFromLocalDb() {
        `when`(dataDao.getData()).thenReturn(Single.just(listOf(mockListItem)))

        repo.getDataFromLocalDb()

        verify(dataDao, times(1)).getData()
    }

    @Test
    fun getDataFromLocalDb_empty_localDb() {
        `when`(dataDao.getData()).thenReturn(Single.just(emptyList()))
        `when`(listService.getData()).thenReturn(Single.just(listOf(mockServerResponseItem)))

        repo.getDataFromLocalDb()

        verify(dataDao, times(1)).getData()

    }

    @Test
    fun getData() {
        `when`(listService.getData()).thenReturn(Single.just(listOf(mockServerResponseItem)))

        repo.getData()

        verify(listService, times(1)).getData()
    }

}