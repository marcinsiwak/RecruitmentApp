package pl.msiwak.recruitmentapp.ui.main.list

import io.reactivex.rxjava3.core.Single
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import pl.msiwak.recruitmentapp.data.ListItem
import pl.msiwak.recruitmentapp.domain.GetDataFromLocalDbUseCase
import pl.msiwak.recruitmentapp.domain.GetDataUseCase
import pl.msiwak.recruitmentapp.util.config.ResourceProvider
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import org.junit.Rule

class ListViewModelTest : BaseTest() {

    @Rule @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getDataFromLocalDbUseCase: GetDataFromLocalDbUseCase

    @Mock
    private lateinit var getDataUseCase: GetDataUseCase

    @Mock
    private lateinit var resProvider: ResourceProvider

    private lateinit var viewModel: ListViewModel

    private val mockListItem = ListItem(1, "title", "description", "10.10.2021", "url", "")
    private val mockList = listOf(mockListItem)

    override fun setup() {
        super.setup()

        viewModel = ListViewModel(
            getDataUseCase,
            getDataFromLocalDbUseCase,
            resProvider
        )

        verify(resProvider, times(1)).getString(anyInt())
        verify(resProvider, times(1)).getDrawable(anyInt())
    }

    override fun tearDown() {
        super.tearDown()

        verifyNoMoreInteractions(
            getDataUseCase,
            getDataFromLocalDbUseCase,
            resProvider
        )
    }

    @Test
    fun onInit_getDataFromLocalDb_success() {
        `when`(getDataFromLocalDbUseCase.execute()).thenReturn(Single.just(mockList))

        viewModel.onInit()

        assertEquals(mockList, viewModel.listData.value)
        assertEquals(false, viewModel.progressVisibility.value)

        verify(getDataFromLocalDbUseCase, times(1)).execute()
    }

    @Test
    fun onInit_getDataFromLocalDb_error() {
        `when`(getDataFromLocalDbUseCase.execute()).thenReturn(Single.error(Throwable()))

        viewModel.onInit()

        assertEquals(false, viewModel.progressVisibility.value)

        verify(getDataFromLocalDbUseCase, times(1)).execute()
    }

    @Test
    fun onBackClicked(){
        viewModel.onBackClicked()

        assertEquals(ListEvents.CloseApp, viewModel.event.value?.peekContent())
    }

    @Test
    fun onItemClicked_large_screen(){
        `when`(resProvider.getBoolean(anyInt())).thenReturn(true)
        viewModel.listData.value = mockList

        viewModel.onItemClicked(0)

        assertEquals("url", (viewModel.event.value?.peekContent() as ListEvents.OpenBrowserForLargeScreen).url)
        verify(resProvider, times(1)).getBoolean(anyInt())
    }

    @Test
    fun onItemClicked(){
        `when`(resProvider.getBoolean(anyInt())).thenReturn(false)
        viewModel.listData.value = mockList

        viewModel.onItemClicked(0)

        assertEquals("url", (viewModel.event.value?.peekContent() as ListEvents.OpenBrowser).url)
        verify(resProvider, times(1)).getBoolean(anyInt())
    }

}