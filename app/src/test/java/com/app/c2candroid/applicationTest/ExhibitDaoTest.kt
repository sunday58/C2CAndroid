package com.app.c2candroid.applicationTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.c2candroid.TestCoroutineRule
import com.app.c2candroid.model.Exhibit
import com.app.c2candroid.restExhibitLoader.localStorage.ExhibitDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import kotlin.test.assertNotNull


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ExhibitDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var database: ExhibitDatabase

    @Mock
    private lateinit var exhibit: List<Exhibit>

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears after test
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ExhibitDatabase::class.java)
            // allowing main thread queries, just for testing
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDb() {
        database.close()
    }


    @Test
    fun `get exhibit when nothing is inserted`() = testCoroutineRule.runBlockingTest  {
       val result = database.exhibitDao().get()
           Assert.assertNull(result)
    }

    @Test
    fun `insert user into exhibit database`() = testCoroutineRule.runBlockingTest {
        val result = database.exhibitDao().insert(exhibit)
        assertNotNull(result)
    }

}