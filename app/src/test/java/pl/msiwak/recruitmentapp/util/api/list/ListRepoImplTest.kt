package pl.msiwak.recruitmentapp.util.api.list

import io.reactivex.rxjava3.core.Single
import junit.framework.TestCase
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.data.ServerResponseItem
import pl.msiwak.recruitmentapp.ui.main.list.BaseTest
import pl.msiwak.recruitmentapp.ui.main.list.ListViewModel
import pl.msiwak.recruitmentapp.util.db.DataDao

class ListRepoImplTest : BaseTest(){

    private lateinit var repo: ListRepo

    @Mock
    private lateinit var dataDao: DataDao

    @Mock
    private lateinit var listService: ListService

    private val mockListItem = ListItem(1,"", "", "",  "", "")

    override fun setup() {
        super.setup()

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
        dataDao.getData().subscribe()

        verify(dataDao, times(2)).getData()
    }

}