package pl.msiwak.recruitmentapp.domain

import io.reactivex.rxjava3.core.Single
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.ui.main.list.BaseTest
import pl.msiwak.recruitmentapp.util.api.list.ListRepo

class GetDataFromLocalDbUseCaseTest : BaseTest() {

    private lateinit var useCase: GetDataFromLocalDbUseCase

    @Mock
    private lateinit var listRepo: ListRepo

    private val mockListItem = ListItem(1, "", "", "", "", "")
    private val mockList = listOf(mockListItem)

    override fun setup() {
        super.setup()

        trampolineRxPlugin()
        useCase = GetDataFromLocalDbUseCase(listRepo)

    }

    override fun tearDown() {
        super.tearDown()

        verifyNoMoreInteractions(
            listRepo
        )
    }

    @Test
    fun execute() {
        `when`(listRepo.getDataFromLocalDb()).thenReturn(Single.just(mockList))

        val output = useCase.execute().blockingGet()

        assertEquals(mockList, output)
        verify(listRepo, times(1)).getDataFromLocalDb()
    }

}