## Technogies in that project
- Clean Architecture (data, domain, presentation layers)
- Android Architecture Components (ViewModel, LiveData)
- Koin as DI container
- RxJava for asynchronous operations
- Room as DAO and for save data on storage
- Mockk for Unit Testing

Что нужно исправлять
-Написано CLEAN архитектура, а порядок зависимостей не соблюдается. TodoDTO должно быть в домене и не зависить от TodoEntity/Res.
-lazy для koin модулей - зачем? Ещё и вкупе с object'ом.
mockito в перемешку с mockk в тестах.
-Использование deprecated API
-Мёртвый код в репозитарии.
Дальше не копал.
