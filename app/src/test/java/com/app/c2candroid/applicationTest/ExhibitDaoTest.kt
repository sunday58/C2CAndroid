package com.app.c2candroid.applicationTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.app.c2candroid.TestCoroutineRule
import com.app.c2candroid.model.Exhibit
import com.app.c2candroid.restExhibitLoader.localStorage.ExhibitDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertNotNull


@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class ExhibitDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var database: ExhibitDatabase

    @Mock
    private lateinit var exhibit: Exhibit

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears after test
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ExhibitDatabase::class.java)
            // allowing main thread queries, just for testing
            .allowMainThreadQueries()
            .build()

        MockitoAnnotations.initMocks(this)
    }

    @After
    fun closeDb() {
        database.close()
    }


    @Test
    fun `get exhibit when nothing is inserted`() = testCoroutineRule.runBlockingTest  {
        exhibit = Exhibit(1, arrayListOf(""), "test")
        val result = database.exhibitDao().get()
        Assert.assertNotSame(exhibit, result)
    }

    @Test
    fun `insert user into exhibit database`() = testCoroutineRule.runBlockingTest {
        exhibit = Exhibit(1 )
        val result = database.exhibitDao().insert(exhibit)
        assertNotNull(result)
    }

}