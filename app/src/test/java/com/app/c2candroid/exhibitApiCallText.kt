package com.app.c2candroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.c2candroid.application.MainStateEvent
import com.app.c2candroid.application.MainViewModel
import com.app.c2candroid.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class ExhibitApiCallText {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test_exhibit_api_call() = testCoroutineRule.runBlockingTest {

        val state = MainStateEvent.GetExhibitEvents
        viewModel.getStateEvent(state){dataState ->
            when(dataState){
                is DataState.success -> {
                    val responseData = dataState.data
                    Assert.assertNotNull("there is response from api", responseData)
                }
                is DataState.Loading -> Assert.assertEquals("Loading", "Loading")
                is DataState.Error -> Assert.fail(dataState.exception.message)
                is DataState.otherError -> Assert.fail(dataState.error)
            }
        }

    }

}