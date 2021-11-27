package com.bugtsa.todo.domain

import com.bugtsa.todo.domain.model.TodoDto
import com.bugtsa.todo.domain.repos.NetworkRepository
import com.bugtsa.todo.domain.repos.StorageRepository
import com.bugtsa.todo.global.rx.SchedulersProvider
import io.mockk.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class TodoInteractorImplTest {

    private val networkRepository: NetworkRepository = mockk()
    private val storageRepository: StorageRepository = mockk()
    private val todoInteractor = TodoInteractorImpl(networkRepository, storageRepository)

    private val todoFirst = TodoDto(1, true, "Perform breakfast", 1)
    private val todoSecond = TodoDto(2, false, "Go to gym", 2)
    private val todoList = mutableListOf(todoFirst, todoSecond)
    private val idsList = mutableListOf(1L, 2L)

    @Before
    fun prepareToTests() {
        clearAllMocks()

        mockkObject(SchedulersProvider)
        every { SchedulersProvider.io() } returns Schedulers.trampoline()
        every { SchedulersProvider.ui() } returns Schedulers.trampoline()
    }

    @Test
    fun networkTodosListTest() {
        every { networkRepository.isAvailableNetwork() } returns Single.just(true)
        every { networkRepository.observeTodosList() } returns Single.just(todoList)
        every { storageRepository.isEmptyTodoList() } returns Single.just(true)
        every { storageRepository.saveTodoList(todoList) } returns Single.just(idsList)

        todoInteractor.observeTodosList()
            .test()
            .assertSubscribed()
            .assertComplete()

        verify(exactly = 1) {
            networkRepository.isAvailableNetwork()
            networkRepository.observeTodosList()
            storageRepository.isEmptyTodoList()
        }
    }

    @Test
    fun offlineTodosListTest() {
        every { networkRepository.isAvailableNetwork() } returns Single.just(false)
        every { storageRepository.observeTodoList() } returns Single.just(todoList)

        todoInteractor.observeTodosList()
            .test()
            .assertSubscribed()
            .assertComplete()

        verify(exactly = 1) {
            networkRepository.isAvailableNetwork()
            storageRepository.observeTodoList()
        }
    }
}