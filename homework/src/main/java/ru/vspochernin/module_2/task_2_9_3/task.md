Есть склад, в котором хранятся товары разных типов, каждый — в ограниченном количестве. Реализуйте потокобезопасный
класс Warehouse для управления складом.

Формат ввода

Необходимо реализовать методы:

addItem(String itemType, int count) — добавление товаров.

removeItem(String itemType, int count) — удаление товаров (возвращает false, если недостаточно товаров).

getItemCount(String itemType) — получение количества товаров.

transfer(Warehouse other, String itemType, int count) — перемещение товаров между складами.

Формат вывода

Функция getItemCount должна возвращать одно целое число — количество товаров.

Функции addItem и removeItem должны возвращать одно булевое значение — true или false в случае успеха или неудачи
соответственно.

Типы данных

n: целое число (1 ≤ n ≤ 1000).

itemType: строка (1 ≤ длина itemType ≤ 20).

count: целое число (1 ≤ count ≤ 1000).

warehouseId: целое число (идентификатор склада, предполагается в диапазоне существующих складов).

Примеры

Ввод Вывод
5
ADD apple 100
GET apple
REMOVE apple 50
TRANSFER apple 30 2
GET apple 100
SUCCESS
SUCCESS
20
4
4
ADD orange 50
REMOVE orange 70
ADD orange 30
GET orange FAIL
80
6 SUCCESS
ADD book 100
ADD pen 50
TRANSFER book 30 2
GET book
TRANSFER pen 60 2
GET pen 70
FAIL
50