## Technogies in that project
- Clean Architecture (data, domain, presentation layers)
- Android Architecture Components (ViewModel, LiveData)
- Koin as DI container
- RxJava for asynchronous operations
- Room as DAO and for save data on storage
- Mockk for Unit Testing

# Что нужно исправлять
- Продумать Mapper структуру
- lazy для koin модулей - зачем? Ещё и вкупе с object'ом.
1. Ошибки clean arch:
Пакет network отдельно от data, есть пакет ui и есть presentation
Мапперы в слое domain или внутри самих классов как companion object
и тд.
2. DI:
Часть зависимостей резолвится через koin, часть просто объявлена object.
Непонятно, чем помогут by lazy у модулей.
3. Codestyle:
Нет единообразия в нейминге, н-р: id/title_todo, id/vCaptions
4. RX:
Строго говоря, Single - не observable, поэтому называть методы, возвращающие Single - observe - неверно.
5. Неоптимально реализованы:
5.1. DiffUtil.Callback для TodoState - дважды проверяется равенство одного и того же поля. С точки зрения идеалогии вряд ли в методе areContentsTheSame достаточно проверить только id.
Что приводит нас к вопросу корректно ли в общем реализовыван interface DiffItem.
5.2. Для проверки есть ли в таблице записи оптимальнее проверить есть ли хотя бы одна, а не получать их общее количество, чтобы просто проверить на равенство с нулем.
