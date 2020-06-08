# magnitTEST
          
          ***тестовое задание Магнит***
          
Описание выполнения задания ниже после самого задания
          
Дано: таблица TEST в произвольной БД содержащая один столбец целочисленного типа (FIELD).
Необходимо написать консольное приложение на Java, использующее стандартную библиотеку JDK7
(желательно) либо JDK8 и реализующее следующий функционал:

1 Основной класс приложения должен следовать правилам JavaBean, то есть инициализироваться
через setter'ы. Параметры инициализации - данные для подключения к БД и число N.

2 После запуска, приложение вставляет в TEST N записей со значениями 1..N. Если в таблице TEST
находились записи, то они удаляются перед вставкой.

3 Затем приложение запрашивает эти данные из TEST.FIELD и формирует корректный XML-
документ вида
<entries>
<entry>
<field>значение поля field</field>
</entry>
...
<entry>
<field>значение поля field</field>
</entry>
</entries>
(с N вложенных элементов <entry>)
Документ сохраняется в файловую систему как "1.xml".

4 Посредством XSLT, приложение преобразует содержимое "1.xml" к следующему виду:
<entries>
<entry field="значение поля field">
...
<entry field="значение поля field">
</entries>
(с N вложенных элементов <entry>)
Новый документ сохраняется в файловую систему как "2.xml".

5 Приложение парсит "2.xml" и выводит арифметическую сумму значений всех атрибутов field в
консоль.

6 При больших N (~1000000) время работы приложения не должно быть более пяти минут.

7 Программу нужно реализовать с учетом возможности переиспользования/встраивания в других
задачах и проектах.

    ОПИСАНИЕ

При выполнении задания, структуру пиложения построил на возможное дальнейшее использование кода в других проектах или модулях.
Для этого применялся фабричный паттерн. 
Конфигурационные настройки вынесены в отдельный пакет и для доступной и гибкой замены в готовом приложении в файл config.property.
В ходе выполнения задания сложности составили в 4 пункте, так как раньше с таким не сталкивался, XSLT изучался по ходу выполнения.

Что касается работы приложения: со 100 т.записей приложение на моей машине справляется за 12-16 секунд. 
Однако чтобы протестировать на 1 млн., мне не хватает мощности, у меня слабый компьютер.

Чтобы ускорить процесс выполнения алгоритма и работы с файловой системой, вижу решение в подключении многопоточности.
Как это вижу я: в первом потоке происходит работа с базой, после того как добавиться 50-100т.записей, создаем второй поток, 
который начнет заниматься с xml, извлекает с базы 50-100т.записей и ждет следующую партию. Только думаю, 
что чтобы это получилось, в таблицу необходимо добавить столбец-идентификатор с PRIMARY KEY.
Сейчас, в виду ограниченности по времени, выкладываю данную версию приложения, без использования многопоточности, 
постараюсь реализовать задуманное на ближайшей неделе
